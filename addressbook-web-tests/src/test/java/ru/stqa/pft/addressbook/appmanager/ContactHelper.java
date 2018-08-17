package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {


  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnContactPage() {
    click(By.linkText("home page"));
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactForm, boolean creation) {
    type(By.name("firstname"), contactForm.getFirstName());
    type(By.name("middlename"), contactForm.getMiddleName());
    type(By.name("lastname"), contactForm.getLastName());
    type(By.name("nickname"), contactForm.getNickname());
    type(By.name("title"), contactForm.getTitle());
    type(By.name("company"), contactForm.getCompany());
    type(By.name("address"), contactForm.getAddress());
    type(By.name("home"), contactForm.getHome());
    type(By.name("mobile"), contactForm.getMobile());
    type(By.name("work"), contactForm.getWork());
    type(By.name("fax"), contactForm.getFax());
    type(By.name("email"), contactForm.getEmail());
    type(By.name("homepage"), contactForm.getHomepage());
    type(By.name("address2"), contactForm.getAddress2());
    type(By.name("phone2"), contactForm.getPhone2());
    type(By.name("notes"), contactForm.getNotes());


    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactForm.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void addNewContact() {
    click(By.id("container"));
    click(By.linkText("add new"));
  }

  public void clickAcceptDeleteContact() {
    wd.switchTo().alert().accept();
  }

  public void clickDeleteContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }


  public void clickSelectedContactById(int id) {
    if (!wd.findElement(By.name("selected[]")).isSelected()) {
      wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
    }
  }

  public void initContactModification(int index) {
    wd.findElements(By.xpath("//img[@title='Edit']")).get(index).click();

  }

  public void initContactModificationById(int id) {
    wd.findElement(By.xpath("//tr/td[input[@value='"+id+"']]/../td[8]/a/img[@title='Edit']")).click();

  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void create(ContactData contact) {

    addNewContact();
    fillContactForm(contact, true);
    submitContactCreation();
    contactCache = null;
    returnContactPage();
  }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
  }



  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();

  }
 private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts (contactCache);
    }
    contactCache  = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));

    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String firstname = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      String allPhones = cells.get(5).getText();
      contactCache.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname)
              .withAllPhones(allPhones));


    }
    return new Contacts (contactCache);
  }


  public void delete(ContactData contact) {
    clickSelectedContactById(contact.getId());
    clickDeleteContact();
    clickAcceptDeleteContact();
    contactCache = null;
  }

  public ContactData infoFromEditForm (ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement (By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement (By.name("lastname")).getAttribute("value");
    String home = wd.findElement (By.name("home")).getAttribute("value");
    String mobile = wd.findElement (By.name("mobile")).getAttribute("value");
    String work = wd.findElement (By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId())
            .withFirstName(firstname).withLastName(lastname).withHome(home).withMobile(mobile).withWork(work);
  }

}