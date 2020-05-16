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
      if(line.startsWith("-")) {  // want to remove an Item
        String iName = line.substring(1);
        String itemToRemove = getRecordFromFileSource(iName);
        if(itemToRemove == null) {
          System.out.println("No item named '" + iName+ "' in the system");
        } else {
          if(basket.removeItemFromBasket(new Item(itemToRemove)) == null)
            System.out.println("Not possible to remove " + iName);
          System.out.println(basket);
        } // end remove
      } else {  // Add Item to Basket
        String itemToAdd = getRecordFromFileSource(line);
        if(itemToAdd == null)
          System.out.println("No item named '" + line + "' in the system");
        else {
          basket.addItemToBasket(new Item(itemToAdd));
          System.out.println(basket);
        }
      }  // end Add
    } while(!line.trim().equals(""));

    System.out.println(new Checkout(basket));
  }
}
