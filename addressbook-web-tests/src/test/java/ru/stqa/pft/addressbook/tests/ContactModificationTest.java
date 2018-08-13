package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactModificationTest extends TestBase {

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
  public void testContactModification () {

    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact =  before.iterator().next();

    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("NeStas").withMiddleName("NoPetrov").withLastName("NoVodkin").withNickname("Stas").withCompany("BSS");
    app.contact().modify(contact);
    app.goTo().contactPage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contact);


    Assert.assertEquals(before,after);
  }


}
