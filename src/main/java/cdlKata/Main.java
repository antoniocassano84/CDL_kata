package cdlKata;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

  private static final String DATA_SOURCE = "src\\main\\resources\\itemsDB.csv";
  private static Basket basket;


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
    basket = new Basket();
    String line;
    do{
      Scanner sc = new Scanner(System.in);
      System.out.print("Please enter an Item name (or whitespace to exit): ");
      line = sc.nextLine();
      // here goes the code for the remove method -A
      String record = getRecordFromFileSource(line);
      if(record == null)
        System.out.println("No item named \'" + line + "\' in the system");
      else {
        Item item = new Item(record);
        basket.addItemToBasket(item);
        System.out.println(basket);
      }
    } while(!line.trim().equals(""));
    Checkout checkout = new Checkout(basket);
    System.out.println(checkout);

    Basket b = new Basket();
    b.addItemToBasket(new Item("A,0.50,3,1.30"));
    Item itemA = new Item("A,0.50,3,1.30");
    System.out.println("???" + b.getItems().containsKey(itemA));
    b.addItemToBasket(new Item("A,0.50,3,1.30"));
    System.out.println("new");
    System.out.println(b);
    Checkout check = new Checkout(b);
    System.out.println(check);

  }
}
