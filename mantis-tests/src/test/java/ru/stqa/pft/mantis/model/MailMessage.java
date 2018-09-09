package ru.stqa.pft.mantis.model;


import java.util.Objects;

public class MailMessage {

  public String to;
  public String text;


  public MailMessage(String to, String text) {
    this.to = to;
    this.text = text;

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MailMessage that = (MailMessage) o;
    return Objects.equals(to, that.to) &&
            Objects.equals(text, that.text);
  }

  @Override
  public int hashCode() {

    return Objects.hash(to, text);
  }
}