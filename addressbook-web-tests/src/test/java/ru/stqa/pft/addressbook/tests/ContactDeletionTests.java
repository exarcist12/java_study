package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion() {


    app.getNavigationHelper().goToHomeContacts();
    app.getContactHelper().clickSelectedContact();
    app.getContactHelper().clickDeleteContact();
    app.getContactHelper().clickAcceptDeleteContact();
  }


}
