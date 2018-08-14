package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase{

  @BeforeMethod

  public void ensurePreconditions () {
    app.goTo().groupPage();
    if ( app.group().all().size()==0){
      app.group().create(new GroupData().withName("FirstTestGroup"));
    }
    app.goTo().contactPage();
    if ( app.contact().all().size()==0){
      app.contact().create(new ContactData().withFirstName("Stas").withLastName("Markin").withGroup("FirstTestGroup"));
    }
  }


  @Test

  public void testContactDeletion() {

    Contacts before = app.contact().all();
    ContactData deletedContact =  before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().contactPage();
    assertThat(app.contact().count(), equalTo(before.size()-1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(deletedContact)));

  }




}
