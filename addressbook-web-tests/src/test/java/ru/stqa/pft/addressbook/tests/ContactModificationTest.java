package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactModificationTest extends TestBase {


  @Test
  public void testContactModification () {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("FirstTestGroup", null, null));
    }
    app.getNavigationHelper().goToHomeContacts();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Dlyamodificacii", "Petrov", "Vodkin", "Stas", "Stas", "BSS", "LibertyCity", "70001112233", "81112223334", "SS", "45678912300", "exarcist12@yandex.ru", "yandex.ru", "test", "test", "test", "FirstTestGroup"));
    }
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().clickSelectedContact(before-1);
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("NeStas", "NoPetrov", "NoVodkin", "Stas", "Stas", "BSS", "LibertyCity", "70001112233", "81112223334", "SS", "45678912300", "exarcist12@yandex.ru", "yandex.ru", "test", "test", "test", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().goToHomeContacts();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);

  }
}
