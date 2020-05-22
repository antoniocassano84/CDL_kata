package cdlKata;

import cdlKata.datasource.ItemDaoDB;
import cdlKata.datasource.ItemDaoFile;

import java.util.Optional;
import java.util.Scanner;

public class Till {

  private static final ItemDaoDB iDaoDb = new ItemDaoDB();
  private static final ItemDaoFile iDaoFile = new ItemDaoFile();

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
    Optional<Item> optItem = iDaoDb.get(line);
    if(!optItem.isPresent()) {
      System.out.println("No item named '" + line + "' in the system");
    }
    else {
      Item item = optItem.get();
      if(remove) { // remove item from the basket
        if(basket.removeItemFromBasket(item) == -1)
          System.out.println("Not possible to remove " + line);
      } else { // add item to basket
        basket.addItemToBasket(item);
      }
    }
    return new Checkout(basket);
  }

  public static void printCatalog() {
    System.out.println("-----------");
    System.out.println(" I T E M S ");
    for(Item item : iDaoDb.getAll()) {
      System.out.println(item);
    }
    System.out.println("-----------");
  }
}
