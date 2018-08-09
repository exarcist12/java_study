package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTest extends TestBase {

  @BeforeMethod

  public void ensurePreconditions () {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("FirstTestGroup", null, null));
    }
    app.getNavigationHelper().goToHomeContacts();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("NeStas", "Petrov", "Vodkin", "Stas", "Stas", "BSS", "LibertyCity", "70001112233", "81112223334", "SS", "45678912300", "exarcist12@yandex.ru", "yandex.ru", "test", "test", "test", "FirstTestGroup"));
    }
  }


  @Test
  public void testContactModification () {

    List<ContactData> before = app.getContactHelper().getContactList();
    int index = before.size()-1;
    ContactData contact = new ContactData(before.get(index).getId(),"NeStas", "NoPetrov", "NoVodkin", "Stas", "Stas", "BSS", "LibertyCity", "70001112233", "81112223334", "SS", "45678912300", "exarcist12@yandex.ru", "yandex.ru", "test", "test", "test", null);
    app.getContactHelper().modifyContact(index, contact);
    app.getNavigationHelper().goToHomeContacts();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare (g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before,after);
  }


}
