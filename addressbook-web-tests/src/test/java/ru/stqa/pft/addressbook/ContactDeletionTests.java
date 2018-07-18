package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

import org.openqa.selenium.*;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion() {


    goToHomeContacts();
    clickSelectedContact();
    clickDeleteContact();
    clickAcceptDeleteContact();
  }


}
