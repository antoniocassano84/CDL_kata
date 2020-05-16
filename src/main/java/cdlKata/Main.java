package cdlKata;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

  private static final String DATA_SOURCE = "src\\main\\resources\\itemsDB.csv";

  private static String getRecordFromFileSource(String str) {
    try (Scanner input = new Scanner(new File(DATA_SOURCE))) {
      input.nextLine(); // skip header
      while(input.hasNext()) {
        String line = input.nextLine();
        if(line.split(",")[0].equalsIgnoreCase(str))
          return line;
      }
      return null;
    } catch (IOException e) {
      return null;
    }
  }

  public static void main(String[] args) {
    Basket basket = new Basket();
    String line;

    do {
      Scanner sc = new Scanner(System.in);
      System.out.print("Please enter an Item name (or whitespace to exit): ");
      line = sc.nextLine();
      if(line.trim().equals("")) break;
      // here goes the code for the remove method -A
      if(line.startsWith("-")) {
        //remove
        String itemToRemove = getRecordFromFileSource(line.substring(1));
        if(itemToRemove == null) {
          System.out.println("No item named '" + line.substring(1) + "' in the system");
        } else {
          Item item = new Item(itemToRemove);
          if(basket.removeItemFromBasket(item) == null)
            System.out.println("Not possible to remove " + item.getName());
          System.out.println(basket);
        } // end remove
      } else {  // Add Item to Basket
        String itemToAdd = getRecordFromFileSource(line);
        if(itemToAdd == null)
          System.out.println("No item named '" + line + "' in the system");
        else {
          Item item = new Item(itemToAdd);
          basket.addItemToBasket(item);
          System.out.println(basket);
        }
      }  // end else Add
    } while(!line.trim().equals(""));

    Checkout checkout = new Checkout(basket);
    System.out.println(checkout);
  }
}
