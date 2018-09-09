package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;


public class ChangePasswordTests extends TestBase{

  @Test

  public void testChangePassword() throws IOException, MessagingException {

    String email = "user12@localhost";
    String username = "user12";
    String password = "password";
    String usernameAdmin = "administrator";
    String passwordAdmin = "root";
    String to = "";
    String text = "";

    List<MailMessage> mailMessagesBefore = app.james().waitForMail(username, password, 60000);

    app.changepass().login(usernameAdmin, passwordAdmin);
    app.changepass().clickUsersManage();
    app.changepass().clickUser(username);
    app.changepass().resetPassword();

    List<MailMessage> mailMessagesAfter;
    List<MailMessage> mailMessagesLast = null;
    MailMessage mailBefore = new MailMessage(to, text);
    MailMessage mailAfter = new MailMessage(to, text);
    do {
      mailMessagesAfter = app.james().waitForMail(username, password, 60000);
    } while (mailMessagesBefore.size() == mailMessagesAfter.size());
    //String confirmationLink2 = findConfirmationLink(mailMessagesAfter, email);
    // mail = mailMessagesBefore.get(1);

    MailMessage mail = new MailMessage(to, text);
    for (int i = 0; i < mailMessagesAfter.size(); i++) {
      mailAfter = mailMessagesAfter.get(i);

//      mailBefore = mailMessagesBefore.get(i);
      if (mailMessagesBefore.contains(mailAfter)) {

      } else {

        mail = mailAfter;
      }


    }
   String password2 = "password2";
   // mailMessagesLast.add(mail);
    String confirmationLink = findConfirmationLink2(mail, email);
  app.changepass().finishChange(confirmationLink, password2);
  assertTrue(app.newSession().login(username, password2));
  }


  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }
  private String findConfirmationLink2(MailMessage mail, String email) {
   // MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mail.text);
  }


}
