package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactDeletionTests extends TestBase{

  @Test(enabled = false)
  public void testContactDeletion() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("FirstTestGroup", null, null));
    }

    app.getNavigationHelper().goToHomeContacts();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Dlyaudaleniya", "Petrov", "Vodkin", "Stas", "Stas", "BSS", "LibertyCity", "70001112233", "81112223334", "SS", "45678912300", "exarcist12@yandex.ru", "yandex.ru", "test", "test", "test", "FirstTestGroup"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().clickSelectedContact(before.size()-1);
    app.getContactHelper().clickDeleteContact();
    app.getContactHelper().clickAcceptDeleteContact();
    app.getNavigationHelper().goToHomeContacts();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size()-1);

    before.remove(before.size()-1);

    Assert.assertEquals(before, after);

  }


}
