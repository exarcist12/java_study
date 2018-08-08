package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
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
    ContactData contact = new ContactData("Stas", null, "Markin", null, null, null,null,null,null,null,null,null,null,null,null,null,"FirstTestGroup");
    app.getContactHelper().createContact(contact);
    app.getNavigationHelper().goToHomeContacts();

    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size()+1);


    contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
  }

}
