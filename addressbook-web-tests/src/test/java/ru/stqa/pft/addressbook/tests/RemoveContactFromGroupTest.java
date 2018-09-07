package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroupTest extends TestBase {

  @BeforeMethod

  public void ensurePreconditions () {

    app.goTo().groupPage();

    if (app.db().groups().size()==0){
      app.group().create(new GroupData().withName("FirstTestGroup"));
    }
  }

  @Test

  public void testRemoveContactFromGroup() {
    Contacts beforeAllContacts = app.db().contacts();
    ContactData removeContact = beforeAllContacts.iterator().next();
    Groups beforeAllGroups = app.db().groups();
    Groups resultBefore = removeContact.getGroups();
    GroupData addGroup = beforeAllGroups.iterator().next();
    GroupData currentGroup = new GroupData();// resultBefore.iterator().next();
    ContactData currentContact = new ContactData();
    int idContact = removeContact.getId();
    int idGroup = addGroup.getId();


    if (resultBefore.size() == 0 ) {
      app.goTo().contactPage();
      app.contact().addToContact(removeContact, addGroup);
      currentGroup = addGroup;
      beforeAllContacts = app.db().contacts();
      for (ContactData contact: beforeAllContacts){
        if (contact.getId()==idContact){
          currentContact = contact;
          break;
        }
      }
      resultBefore = currentContact.getGroups();
      app.goTo().contactPage();
      app.contact().removeContactFromGroup(removeContact, currentGroup);
    } else {
      currentGroup = resultBefore.iterator().next();
      app.goTo().contactPage();
      app.contact().removeContactFromGroup(removeContact,currentGroup);
    }
    Contacts afterAllContacts = app.db().contacts();
    for (ContactData contact: afterAllContacts){
      if (contact.getId()==idContact){
        currentContact = contact;
        break;
      }
    }
    Groups resultAfter = currentContact.getGroups();

    assertThat(resultAfter, equalTo(resultBefore.without(currentGroup)));
  }
}
