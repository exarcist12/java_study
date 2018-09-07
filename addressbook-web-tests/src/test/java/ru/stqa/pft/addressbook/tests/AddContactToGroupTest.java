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

    Contacts beforeContacts = app.db().contacts();
    ContactData addContact =  beforeContacts.iterator().next();
    Groups beforeGroups = app.db().groups();

    System.out.println(addContact);
    System.out.println(addContact.getGroups());
    Groups resultBefore = addContact.getGroups();



   // GroupData group = new GroupData();
    for (GroupData group: beforeGroups) {
      if (addContact.getGroups().contains(group)) {

        }
        else {
          app.goTo().contactPage();
          app.contact().addToContact(addContact, group);
          //Groups testListGroup = addContact.getGroups();
          break;
        }
    }

    Contacts afterContacts = app.db().contacts();
    Groups afterGroups = app.db().groups();
     Groups resultAfter = addContact.getGroups();
  //  if (resultAfter.size()==resultBefore.size()) {
   //   app.goTo().groupPage();
    //  GroupData newGroup = new GroupData().withName("test");
  //    app.group().create(newGroup);
  //    app.goTo().contactPage();
  //    app.contact().addToContact(addContact, newGroup);
  //  }
 //   Groups resultLastAfter = addContact.getGroups();
    //assertThat(resultLastAfter, equalTo(resultBefore.withAdded(group)));

     // if (group != currentGroup){
      //  app.contact().addToContact(addContact, group);
    //    break;
  //    }




   // System.out.println(addContact.getGroups());
    //app.goTo().contactPage();
  //  app.contact().addToContact(addContact, currentGroup);




  }

}
