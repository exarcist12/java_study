package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

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

  public void clickSelectedContact(int index) {
    if (!wd.findElement(By.name("selected[]")).isSelected()) {
      wd.findElements(By.name("selected[]")).get(index).click();
    }
  }

  public void initContactModification(int index) {
    wd.findElements(By.xpath("//img[@title='Edit']")).get(index).click();

  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void createContact(ContactData contact) {

    addNewContact();
    fillContactForm(contact, true);
    submitContactCreation();
    returnContactPage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();

  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String firstname = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      String id = element.findElement(By.tagName("input")).getAttribute("value");
      ContactData contact = new ContactData(id, firstname, null, lastname, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}