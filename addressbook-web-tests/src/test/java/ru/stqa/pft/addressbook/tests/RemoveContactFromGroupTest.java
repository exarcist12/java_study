package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

public class RemoveContactFromGroupTest extends TestBase {


  @Test

  public void testRemoveContactFromGroup(){
    Contacts allContacts = app.db().contacts();
    ContactData removeContact =  allContacts.iterator().next();
    Groups allGroups = app.db().groups();
  //  app.contact().removeContactFromGroup(addContact, group);




  }
}
