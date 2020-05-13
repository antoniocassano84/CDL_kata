package cdlKata;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

  private static char readCharFromKeyboard() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Please enter an Item: ");
    while (!sc.hasNext("(?i)[abcdq]")) {
      System.out.print("Please enter a valid item: 'A', 'B', 'C' or 'D' - or press 'Q' to exit! ");
      sc.next();
    }
    return sc.next().toUpperCase().charAt(0);
  }

  private static List<String> parseRules(String filename) {
    List<String> rules = new ArrayList<>();
    try (Scanner input = new Scanner(new File(filename))) {
      input.nextLine(); // skip header
      while(input.hasNext())
        rules.add(input.nextLine());
      return rules;
    } catch(IOException e) {
      System.out.println("Error : " + e.getMessage());
      return null;
    }
  }

  public static void main(String[] args) {

    Basket basket = new Basket();
    char ch;
    double total = 0.0;

    System.out.println("Your basket is empty");

    do {
      ch = readCharFromKeyboard();
      if(ch != 'q' && ch != 'Q') {
        if (!basket.addToBasket(ch)) throw new IllegalArgumentException("Enter a char between A and D!");
        System.out.print(basket);
        total = basket.checkOut(parseRules("src\\main\\resources\\deals.txt"));
        System.out.println("Running Total = " + String.format("%.2f", total));
        }
      } while (ch != 'q' && ch != 'Q');
    System.out.println("Final Bill = " + String.format("%.2f", total));
  }
}
