package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("World");
    hello("Stas");
    System.out.println();

    Square s = new Square(5);

    System.out.println("площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4,5);

    System.out.println("Площадь прямоугольника равна " + r.area ());


    Point p1 = new Point(1,1);
    Point p2 = new Point(4,5);
    System.out.println("расстояние между точками p1 и p2 равно "+ p1.distance(p2) );

  }

  public static void hello(String somebody) {

    System.out.println("Hello," + somebody + "!");
  }




}