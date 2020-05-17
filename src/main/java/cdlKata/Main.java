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

  public static String scanItemFromCmdLine() {
    String line;
    Scanner scanner = new Scanner(System.in);
    System.out.print("Please enter an Item name (or whitespace to exit): ");
    line = scanner.nextLine();
    return line.trim().equals("") ? null : line;
  }

  public static Checkout makeCheckoutAfterAdd(String line, Basket basket) {
    String itemToAdd = getRecordFromFileSource(line);
    if(itemToAdd == null) {
      System.out.println("No item named '" + line + "' in the system");
      return null;
    }
    else {
      basket.addItemToBasket(new Item(itemToAdd));
      return new Checkout(basket);
    }

  }

  public static Checkout makeCheckoutAfterRemove(String line, Basket basket) {
      String iName = line.substring(1);
      String itemToRemove = getRecordFromFileSource(iName);
      if(itemToRemove == null) {
        System.out.println("No item named '" + iName+ "' in the system");
        return null;
      } else {
        if(basket.removeItemFromBasket(new Item(itemToRemove)) == -1)
          System.out.println("Not possible to remove " + iName);
        return new Checkout(basket);
      }
  }

  public static void main(String[] args) {
    Basket basket = new Basket();
    String line;

    do {
      // Scan an item from command line:
      line = scanItemFromCmdLine();
      if(line == null) break;
      // if line starts with '-' then the user wants to remove an item from the basket
      if(line.startsWith("-")) {
        Checkout checkout = makeCheckoutAfterRemove(line, basket);
        if(checkout != null) System.out.println(checkout);
      } else {  // Add Item to Basket
        Checkout checkout = makeCheckoutAfterAdd(line, basket);
        if(checkout != null) System.out.println(checkout);
        }
    } while(!line.trim().equals(""));

    System.out.println(new Checkout(basket));
  }
}
