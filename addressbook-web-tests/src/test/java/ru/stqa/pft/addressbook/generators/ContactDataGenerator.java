package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  protected int count;
  @Parameter(names = "-f", description = "Target file")
  protected String  file;
  @Parameter(names = "-d", description = "Data format")
  protected String  format;

  public static void main (String[] args) throws IOException {
    ContactDataGenerator generator  = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();



  }


  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    } else  if (format.equals("xml")){
      saveAsXml(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format "+format);
    }
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    try ( Writer writer = new FileWriter(file);){
      writer.write(xml);
    }

  }


  private  void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    try (Writer writer = new FileWriter(file)) {
      for (ContactData contact : contacts) {
        writer.write(String.format("%s;%s;%s\n", contact.getFirstName(), contact.getLastName()));
      }
    }

  }


  private  List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<>();
    for (int i =0; i<count; i++) {
      contacts.add(new ContactData().withFirstName(String.format("Stas %s", i)).withLastName(String.format("Petrov %s", i)));
    }
    return contacts;
  }

}
