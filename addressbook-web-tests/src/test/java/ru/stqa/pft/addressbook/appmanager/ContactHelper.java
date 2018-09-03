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

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("middlename"), contactData.getMiddleName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHome());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("work"), contactData.getWork());
    type(By.name("fax"), contactData.getFax());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("homepage"), contactData.getHomepage());
    type(By.name("address2"), contactData.getAddress2());
    type(By.name("phone2"), contactData.getPhone2());
    type(By.name("notes"), contactData.getNotes());
    //attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
      if (contactData.getGroups().size()>0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }

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

  public void clickSubmitAddContactToGroup() {
          wd.findElement(By.xpath("//input[@type='submit']")).click();

  }

  public void selectGroupForAdd(GroupData group){
    Select selectgroup = new Select(wd.findElement(By.name("to_group")));
    selectgroup.selectByVisibleText(group.getName());
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

  public void addToContact(ContactData contact, GroupData group){
    clickSelectedContactById(contact.getId());
    selectGroupForAdd(group);
    clickSubmitAddContactToGroup();



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

  public void delete(ContactData contact) {
    clickSelectedContactById(contact.getId());
    clickDeleteContact();
    clickAcceptDeleteContact();
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
      String allEmails = cells.get(4).getText();
      contactCache.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname)
              .withAllEmails(allEmails).withAllPhones(allPhones));


    }
    return new Contacts (contactCache);
  }




  public ContactData infoFromEditForm (ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement (By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement (By.name("lastname")).getAttribute("value");
    String home = wd.findElement (By.name("home")).getAttribute("value");
    String mobile = wd.findElement (By.name("mobile")).getAttribute("value");
    String work = wd.findElement (By.name("work")).getAttribute("value");
    String phone2 = wd.findElement (By.name("phone2")).getAttribute("value");
    String email = wd.findElement (By.name("email")).getAttribute("value");
    String email2 = wd.findElement (By.name("email2")).getAttribute("value");
    String email3 = wd.findElement (By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId())
            .withFirstName(firstname).withLastName(lastname).withHome(home).withMobile(mobile).withWork(work).withPhone2(phone2)
            .withEmail(email).withEmail2(email2).withEmail3(email3);
  }

}