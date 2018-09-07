package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

public class AddContactToGroupTest extends TestBase {


  @Test

  public void testAddContactToGroup() {

    Contacts beforeAllContacts = app.db().contacts();
    ContactData addContact =  beforeAllContacts.iterator().next();
    Groups beforeAllGroups = app.db().groups();
    Groups resultBefore = addContact.getGroups();
    int idContact = addContact.getId();
    ContactData currentContact = new ContactData();
    GroupData currentGroup = new GroupData();


    for (GroupData group: beforeAllGroups) {
      if (addContact.getGroups().contains(group)) {

        }
        else {
          app.goTo().contactPage();
          app.contact().addToContact(addContact, group);
          currentGroup = group;
          break;
        }
    }
    app.goTo().contactPage();
    Contacts afterAllContacts = app.db().contacts();
    for (ContactData contact: afterAllContacts){
      if (contact.getId()==idContact){
        currentContact = contact;
        break;
      }
    }
    Groups resultAfter = currentContact.getGroups();

  if (resultAfter.size()==resultBefore.size()) {
      app.goTo().groupPage();
      GroupData newGroup = new GroupData().withName("test");
      app.group().create(newGroup);
      app.goTo().contactPage();
      app.contact().addToContact(addContact, newGroup);
      currentGroup = newGroup;
    }

  assertThat(resultAfter, equalTo(resultBefore.withAdded(currentGroup)));




  }

}
