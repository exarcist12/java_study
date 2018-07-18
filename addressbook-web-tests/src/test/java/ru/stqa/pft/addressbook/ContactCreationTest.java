package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase{

  @Test
  public void testContactCreation() {

    addNewContact();
    fillContactForm(new ContactData("Stas", "Petrov", "Vodkin", "Stas", "Stas", "BSS", "LibertyCity", "70001112233", "81112223334", "SS", "45678912300", "exarcist12@yandex.ru", "yandex.ru", "test", "test", "test"));
    submitContactCreation();
    returnContactPage();
  }

}
