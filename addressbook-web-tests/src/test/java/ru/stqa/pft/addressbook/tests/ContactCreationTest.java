package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTest extends TestBase{

  @Test
  public void testContactCreation() {
    app.goTo().groupPage();
    if (! app.group().isThereAGroup()){
      app.group().create(new GroupData().withName("test"));
    }

    app.goTo().contactPage();

    Contacts before = app.contact().all();

    ContactData contact = new ContactData().withFirstName("Stas").withLastName("Markin").withGroup("test");
    app.contact().create(contact);
    app.goTo().contactPage();

    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size()+1));
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}
