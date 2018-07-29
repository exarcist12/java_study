package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main (String[] args) {
    String[] langs = {"java", "C#","python", "php"};

    List<String> languages = Arrays.asList("java", "C#","python", "php");



   for (String l : languages) {
     System.out.println("Я хочу выучить "+ l);
   }

  }
}
