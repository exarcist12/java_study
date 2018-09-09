package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class ChangePasswordHelper extends HelperBase{
  public ChangePasswordHelper(ApplicationManager applicationManager) {
    super(applicationManager);
  }

  public void login(String username, String password) {

    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type((By.name("username")),username);
    type((By.name("password")),password);
    click(By.cssSelector("input[value='Login']"));
  }

  public void clickUsersManage(){
    click(By.xpath("//a[text()='Manage Users']"));


  }

  public void clickUser(String user) {
    String xpath = "//a[text()='"+user+"']";
    click(By.xpath(xpath));
  }

  public void resetPassword() {
    click(By.cssSelector("input[value='Reset Password']"));
  }

  public void finishChange(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("input[value='Update User']"));
  }
}
