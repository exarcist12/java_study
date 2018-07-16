package ru.stqa.pft.addressbook;

public class ContactForm {
  private final String firstName;
  private final String middleName;
  private final String lastName;
  private final String nickname;
  private final String title;
  private final String company;
  private final String address;
  private final String home;
  private final String mobile;
  private final String work;
  private final String fax;
  private final String email;
  private final String homepage;
  private final String address2;
  private final String phone2;
  private final String notes;

  public ContactForm(String FirstName, String MiddleName, String LastName, String Nickname, String Title, String Company, String Address, String home, String mobile, String work, String fax, String email, String homepage, String address2, String phone2, String notes) {
    firstName = FirstName;
    middleName = MiddleName;
    lastName = LastName;
    nickname = Nickname;
    title = Title;
    company = Company;
    address = Address;
    this.home = home;
    this.mobile = mobile;
    this.work = work;
    this.fax = fax;
    this.email = email;
    this.homepage = homepage;
    this.address2 = address2;
    this.phone2 = phone2;
    this.notes = notes;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getNickname() {
    return nickname;
  }

  public String getTitle() {
    return title;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getHome() {
    return home;
  }

  public String getMobile() {
    return mobile;
  }

  public String getWork() {
    return work;
  }

  public String getFax() {
    return fax;
  }

  public String getEmail() {
    return email;
  }

  public String getHomepage() {
    return homepage;
  }

  public String getAddress2() {
    return address2;
  }

  public String getPhone2() {
    return phone2;
  }

  public String getNotes() {
    return notes;
  }
}
