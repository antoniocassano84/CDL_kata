package cdlKata;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

  private static final String DATA_SOURCE = "src\\main\\resources\\itemsDB.csv";


  private static Item getRecordFromFileSource(String str) {
    try (Scanner input = new Scanner(new File(DATA_SOURCE))) {
      input.nextLine(); // skip header
      while(input.hasNext()) {
        String line = input.nextLine();
        if(line.split(",")[0].equalsIgnoreCase(str)) {
            return Item.parseItem(line);
        }
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

  public static Checkout produceCheckout(String line, Basket basket) {
    boolean remove = line.startsWith("-");
    if(remove) line = line.substring(1);
    Item item = getRecordFromFileSource(line);
    if(item == null) {
      System.out.println("No item named '" + line + "' in the system");
    }
    else {
      if(remove) { // remove item from the basket
        if(basket.removeItemFromBasket(item) == -1)
          System.out.println("Not possible to remove " + line);
      } else { // add item to basket
        basket.addItemToBasket(item);
      }
    }
    return new Checkout(basket);
  }

  public static void main(String[] args) {
    Basket basket = new Basket();
    String line;
    Checkout checkout = new Checkout(basket);

    do {
      // Scan an item from command line:
      line = scanItemFromCmdLine();
      if(line == null) break;
      checkout = produceCheckout(line, basket);
      System.out.println(checkout);
    } while(!line.trim().equals(""));

    System.out.println(checkout);
  }
}
