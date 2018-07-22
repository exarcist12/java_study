package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{


  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void returnContactPage() {
    click(By.linkText("home page"));
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactForm) {
    type(By.name("firstname"),contactForm.getFirstName());
    type(By.name("middlename"),contactForm.getMiddleName());
    type(By.name("lastname"),contactForm.getLastName());
    type(By.name("nickname"),contactForm.getNickname());
    type(By.name("title"),contactForm.getTitle());
    type(By.name("company"),contactForm.getCompany());
    type(By.name("address"),contactForm.getAddress());
    type(By.name("home"),contactForm.getHome());
    type(By.name("mobile"),contactForm.getMobile());
    type(By.name("work"),contactForm.getWork());
    type(By.name("fax"),contactForm.getFax());
    type(By.name("email"),contactForm.getEmail());
    type(By.name("homepage"),contactForm.getHomepage());
    type(By.name("address2"),contactForm.getAddress2());
    type(By.name("phone2"),contactForm.getPhone2());
    type(By.name("notes"),contactForm.getNotes());

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

  public void clickSelectedContact() {
    if (!wd.findElement(By.name("selected[]")).isSelected()) {
      click(By.name("selected[]"));
    }
  }

  public void initContactModification() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }
}
