package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  FirefoxDriver wd;

  private final GroupHelper groupHelper = new GroupHelper();

  public static boolean isAlertPresent(FirefoxDriver wd) {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public void init() {
    wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/");
    groupHelper = new GroupHelper(wd);
    login("admin", "secret");
  }

  private void login(String username, String password) {
    groupHelper.wd.findElement(By.name("user")).click();
    groupHelper.wd.findElement(By.name("user")).clear();
    groupHelper.wd.findElement(By.name("user")).sendKeys(username);
    groupHelper.wd.findElement(By.name("pass")).click();
    groupHelper.wd.findElement(By.name("pass")).clear();
    groupHelper.wd.findElement(By.name("pass")).sendKeys(password);
    groupHelper.wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
  }

  public void gotoGroupPage() {
    groupHelper.wd.findElement(By.linkText("groups")).click();
  }

  public void returnContactPage() {
    groupHelper.wd.findElement(By.linkText("home page")).click();
  }

  public void submitContactCreation() {
    groupHelper.wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  public void fillContactForm(ContactData contactForm) {
    groupHelper.wd.findElement(By.name("firstname")).click();
    groupHelper.wd.findElement(By.name("firstname")).clear();
    groupHelper.wd.findElement(By.name("firstname")).sendKeys(contactForm.getFirstName());
    groupHelper.wd.findElement(By.name("middlename")).click();
    groupHelper.wd.findElement(By.name("middlename")).clear();
    groupHelper.wd.findElement(By.name("middlename")).sendKeys(contactForm.getMiddleName());
    groupHelper.wd.findElement(By.name("lastname")).click();
    groupHelper.wd.findElement(By.name("lastname")).clear();
    groupHelper.wd.findElement(By.name("lastname")).sendKeys(contactForm.getLastName());
    groupHelper.wd.findElement(By.name("nickname")).click();
    groupHelper.wd.findElement(By.name("nickname")).clear();
    groupHelper.wd.findElement(By.name("nickname")).sendKeys(contactForm.getNickname());
    groupHelper.wd.findElement(By.name("title")).click();
    groupHelper.wd.findElement(By.name("title")).clear();
    groupHelper.wd.findElement(By.name("title")).sendKeys(contactForm.getTitle());
    groupHelper.wd.findElement(By.name("company")).click();
    groupHelper.wd.findElement(By.name("company")).clear();
    groupHelper.wd.findElement(By.name("company")).sendKeys(contactForm.getCompany());
    groupHelper.wd.findElement(By.name("address")).click();
    groupHelper.wd.findElement(By.name("address")).clear();
    groupHelper.wd.findElement(By.name("address")).sendKeys(contactForm.getAddress());
    groupHelper.wd.findElement(By.name("home")).click();
    groupHelper.wd.findElement(By.name("home")).clear();
    groupHelper.wd.findElement(By.name("home")).sendKeys(contactForm.getHome());
    groupHelper.wd.findElement(By.name("mobile")).click();
    groupHelper.wd.findElement(By.name("mobile")).clear();
    groupHelper.wd.findElement(By.name("mobile")).sendKeys(contactForm.getMobile());
    groupHelper.wd.findElement(By.name("work")).click();
    groupHelper.wd.findElement(By.name("work")).clear();
    groupHelper.wd.findElement(By.name("work")).sendKeys(contactForm.getWork());
    groupHelper.wd.findElement(By.name("fax")).click();
    groupHelper.wd.findElement(By.name("fax")).clear();
    groupHelper.wd.findElement(By.name("fax")).sendKeys(contactForm.getFax());
    groupHelper.wd.findElement(By.name("email")).click();
    groupHelper.wd.findElement(By.name("email")).clear();
    groupHelper.wd.findElement(By.name("email")).sendKeys(contactForm.getEmail());
    groupHelper.wd.findElement(By.name("homepage")).click();
    groupHelper.wd.findElement(By.name("homepage")).clear();
    groupHelper.wd.findElement(By.name("homepage")).sendKeys(contactForm.getHomepage());
    groupHelper.wd.findElement(By.name("address2")).click();
    groupHelper.wd.findElement(By.name("address2")).clear();
    groupHelper.wd.findElement(By.name("address2")).sendKeys(contactForm.getAddress2());
    groupHelper.wd.findElement(By.name("phone2")).click();
    groupHelper.wd.findElement(By.name("phone2")).clear();
    groupHelper.wd.findElement(By.name("phone2")).sendKeys(contactForm.getPhone2());
    groupHelper.wd.findElement(By.name("notes")).click();
    groupHelper.wd.findElement(By.name("notes")).clear();
    groupHelper.wd.findElement(By.name("notes")).sendKeys(contactForm.getNotes());
  }

  public void addNewContact() {
    groupHelper.wd.findElement(By.id("container")).click();
    groupHelper.wd.findElement(By.linkText("add new")).click();
  }

  public void stop() {
    groupHelper.wd.quit();
  }

  public void goToHomeContacts() {
    groupHelper.wd.findElement(By.linkText("home")).click();
  }

  public void clickAcceptDeleteContact() {
    groupHelper.wd.switchTo().alert().accept();
  }

  public void clickDeleteContact() {
    groupHelper.wd.findElement(By.xpath("//div[@id='content']/form[2]/div[2]/input")).click();
  }

  public void clickSelectedContact() {
    if (!groupHelper.wd.findElement(By.name("selected[]")).isSelected()) {
      groupHelper.selectGroup();
    }
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }
}
