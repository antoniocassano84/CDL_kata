package cdlKata;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

  private static Basket basket;
  private static Item itemA;
  private static Item itemB;
  private static Item itemC;
  private static Checkout checkout;


  public static void main(String[] args) {
    basket = new Basket();
    itemA = new Item("A,0.50,3,1.30");
    itemB = new Item("B,0.30,2,0.45");
    itemC = new Item("C,0.20,,");

    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemC);
    System.out.println("full: " + basket.calculateFullPrice());

    checkout = new Checkout(basket);

  }
}
