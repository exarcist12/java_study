package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTest extends TestBase {

  @Test
  public void testContactPhones () {

    app.goTo().contactPage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);


    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAddress(), equalTo(mergeAddress(contactInfoFromEditForm)));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
   }



  private String mergeAddress (ContactData contact) {
 // String result  = null;
  //    if(contact.getAddress() != null) {
  //      result = result+ contact.getAddress();
  //      }
 //  return result;

    return   contact.getAddress();



  }

  private String mergeEmails (ContactData contact) {
    return   Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) ->  ! s.equals(""))
            .collect(Collectors.joining("\n"));
  }


  private String mergePhones (ContactData contact) {
 //   String result  = "";
 //   if(contact.getHome() != null) {
 //     result = result+ contact.getHome();
 //     }
//return result

 return   Arrays.asList(contact.getHome(), contact.getMobile(), contact.getWork(), contact.getPhone2())
            .stream().filter((s) ->  ! s.equals(""))
         .map(ContactPhoneTest::cleaned)
         .collect(Collectors.joining("\n"));
  }

  public  static String cleaned (String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]","");
  }
}
