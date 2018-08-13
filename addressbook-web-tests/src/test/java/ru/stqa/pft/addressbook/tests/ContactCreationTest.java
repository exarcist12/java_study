package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTest extends TestBase{

  @Test
  public void testContactCreation() {
    app.goTo().groupPage();
    if (! app.group().isThereAGroup()){
      app.group().create(new GroupData().withName("FirstGroup"));
    }

    app.goTo().contactPage();

    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withFirstName("Stas").withLastName("Markin").withGroup("FirstTestGroup");
    app.contact().create(contact);
    app.goTo().contactPage();

    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size()+1);

    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before,after);
  }

}
