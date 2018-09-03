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

public class AddContactToGroupTest extends TestBase {
  @BeforeMethod

  public void ensurePreconditions () {
    app.goTo().groupPage();
    if ( app.db().groups().size()==0){
      app.group().create(new GroupData().withName("test"));
    }
  }

  @Test

  public void testAddContactToGroup() {
    app.goTo().groupPage();
    app.group().create(new GroupData().withName("test"));
    app.goTo().contactPage();
    ContactData addContact = new ContactData().withFirstName("Stas").withLastName("Markin");
    app.contact().create(addContact);
    Contacts beforeContacts = app.db().contacts();
    //ContactData addContact =  beforeContacts.iterator().next();
    Groups beforeGroups = app.db().groups();
    GroupData currentGroup = beforeGroups.iterator().next();
    System.out.println(addContact);
    System.out.println(addContact.getGroups());
    //List<GroupData> resultBefore = addContact.getGroups();
    app.goTo().contactPage();
    app.contact().addToContact(addContact, currentGroup);



  }

  @Test(enabled=false)
  public void testAddContactToGroup2() {
    ContactData addingToGroupContact = new ContactData().withFirstName("Alfa")
            .withLastName("Beta")
            .withAddress("Earth")
            .withMobile("+71234567890")
            .withEmail("alfa@beta.com");
    app.contact().create(addingToGroupContact);

    Groups beforeGroups = app.db().groups();
    GroupData groupForAddingContact = beforeGroups.iterator().next();
    app.goTo().contactPage();

    int id = app.db().getContactLastId(addingToGroupContact);
    app.contact().clickSelectedContactById(id);
    //app.contact().selectGroupForAdd(groupForAddingContact.getName());
    app.contact().clickSubmitAddContactToGroup();
    Contacts afterContacts = app.db().contacts();
    ContactData contactToCompare = new ContactData();
    for(ContactData contact: afterContacts){
      //System.out.println("contact.getId() = " + contact.getId());
      //System.out.println("addingToGroupContact.getId() = " + addingToGroupContact.getId());
      if (contact.getId() == addingToGroupContact.getId()){
        contactToCompare = contact;
        break;
      }
    }
    assertThat(contactToCompare.getGroups(), equalTo(addingToGroupContact.inGroup(groupForAddingContact).getGroups()));
  }
}
