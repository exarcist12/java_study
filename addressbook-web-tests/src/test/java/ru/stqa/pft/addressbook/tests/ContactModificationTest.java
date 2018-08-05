package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

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
    app.getNavigationHelper().goToHomeContacts();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().clickSelectedContact(before.size() - 1);
    app.getContactHelper().initContactModification();
    ContactData contact = new ContactData (before.get(before.size()-1).getId(),"NeStas", "NoPetrov", "NoVodkin", "Stas", "Stas", "BSS", "LibertyCity", "70001112233", "81112223334", "SS", "45678912300", "exarcist12@yandex.ru", "yandex.ru", "test", "test", "test", null);
    app.getContactHelper().fillContactForm((contact), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().goToHomeContacts();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals( after.size(), before.size() );


    before.remove(before.size()-1);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
