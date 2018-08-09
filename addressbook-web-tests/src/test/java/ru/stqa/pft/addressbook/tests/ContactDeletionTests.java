package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactDeletionTests extends TestBase{

  @BeforeMethod

  public void ensurePreconditions () {
    app.goTo().groupPage();
    if ( app.group().list().size()==0){
      app.group().create(new GroupData("FirstTestGroup", null, null));
    }
    app.goTo().contactPage();
    if ( app.contact().list().size()==0){
      app.contact().create(new ContactData("NeStas", "Petrov", "Vodkin", "Stas", "Stas", "BSS", "LibertyCity", "70001112233", "81112223334", "SS", "45678912300", "exarcist12@yandex.ru", "yandex.ru", "test", "test", "test", "FirstTestGroup"));
    }
  }


  @Test

  public void testContactDeletion() {

    List<ContactData> before = app.contact().list();
    int index = before.size()-1;
    app.contact().delete(index);
    app.goTo().contactPage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size()-1);

    before.remove(index);

    Assert.assertEquals(before, after);

  }




}
