package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactCreationTest extends TestBase{

  @Test
  public void testContactCreation() {


    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("FirstTestGroup", null, null));
    }
    app.getNavigationHelper().goToHomeContacts();
    List<ContactData> before = app.getContactHelper().getContactList();

    app.getContactHelper().createContact((new ContactData("Stas", "Petrov", "Vodkin", "Stas", "Stas", "BSS", "LibertyCity", "70001112233", "81112223334", "SS", "45678912300", "exarcist12@yandex.ru", "yandex.ru", "test", "test", "test", "FirstTestGroup")));
    app.getNavigationHelper().goToHomeContacts();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals( after.size(), before.size() + 1);
  }

}
