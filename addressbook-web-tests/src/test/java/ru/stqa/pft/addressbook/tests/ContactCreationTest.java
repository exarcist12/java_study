package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase{

  @Test
  public void testContactCreation() {

    app.addNewContact();
    app.fillContactForm(new ContactData("Stas", "Petrov", "Vodkin", "Stas", "Stas", "BSS", "LibertyCity", "70001112233", "81112223334", "SS", "45678912300", "exarcist12@yandex.ru", "yandex.ru", "test", "test", "test"));
    app.submitContactCreation();
    app.returnContactPage();
  }

}
