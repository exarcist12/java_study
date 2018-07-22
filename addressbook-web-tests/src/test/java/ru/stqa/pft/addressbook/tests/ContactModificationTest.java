package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {


  @Test
  public void testContactModification () {

    app.getNavigationHelper().goToHomeContacts();
    app.getContactHelper().clickSelectedContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("NeStas", "NoPetrov", "NoVodkin", "Stas", "Stas", "BSS", "LibertyCity", "70001112233", "81112223334", "SS", "45678912300", "exarcist12@yandex.ru", "yandex.ru", "test", "test", "test"));
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().goToHomeContacts();


  }
}
