package cdlKata;

import cdlKata.datasource.ItemDaoDB;
import cdlKata.datasource.ItemDaoFile;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {


  private static final String FILE_DATA_SOURCE = "src\\main\\resources\\itemsDB.csv";
  private static final String DB_DATA_SOURCE = "src\\main\\resources\\CdlItems.db";
  private static final String CONNECTION_STRING = "jdbc:sqlite:src\\main\\resources\\CdlItems.db";

  private static final ItemDaoDB ITEM_DAO_DB = new ItemDaoDB();
  private static final ItemDaoFile ITEM_DAO_FILE = new ItemDaoFile();


  public static void connect() {
    String url = "jdbc:sqlite:" + DB_DATA_SOURCE;
    String allItems = "SELECT * FROM Items WHERE Items.name=";

    try (Connection conn = DriverManager.getConnection(url);
         Statement statement = conn.createStatement();
         ResultSet results = statement.executeQuery(allItems)) {
      while(results.next())
        System.out.println(results.getString("name") + ", " +
                           results.getDouble("unitPrice") + ", " +
                           results.getInt("minAmount") + ", " +
                           results.getDouble("specialPrice"));
      } catch (SQLException e) {
        System.out.println(e.getMessage());
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
    //Item item = getRecordFromFileSource(line);
    Optional<Item> optItem = ITEM_DAO_DB.get(line);
    //Optional<Item> optItem = ITEM_DAO_FILE.get(line);
    //if(item == null) {
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

  public static void main(String[] args) {

    System.out.println("A from file");
    Optional<Item> optItem = ITEM_DAO_FILE.get("A");
    System.out.println(optItem.isPresent());
    System.out.println(optItem.get());
    System.out.println();
    System.out.println("A from db");
    optItem = ITEM_DAO_DB.get("A");
    System.out.println(optItem.isPresent());
    System.out.println(optItem.get());

    System.out.println();
    System.out.println("Items from db");
    List<Item> items = ITEM_DAO_DB.getAll();
    for(Item i : items) System.out.println(i);
    System.out.println();
    System.out.println("Items from file");
    List<Item> items2 = ITEM_DAO_FILE.getAll();
    for(Item i : items2) System.out.println(i);

    Item itemE = new Item("E", 0.99, 5, 4.00);
    Item itemF = new Item("F", 10.00, 0, 0.00);
    System.out.println();
    System.out.println("Save item E from db");
    ITEM_DAO_DB.save(itemE);
    ITEM_DAO_DB.save(itemF);
    ITEM_DAO_DB.save(itemE);
    List<Item> itemsE = ITEM_DAO_DB.getAll();
    for(Item i : itemsE) System.out.println(i);
    System.out.println();
    System.out.println("Save item E from file");
    ITEM_DAO_FILE.save(itemE);
    ITEM_DAO_FILE.save(itemF);
    ITEM_DAO_FILE.save(itemE);
    List<Item> itemsEf = ITEM_DAO_FILE.getAll();
    for(Item i : itemsEf) System.out.println(i);


//    Basket basket = new Basket();
//    String line;
//    Checkout checkout = new Checkout(basket);
//
//    do {
//      // Scan an item from command line:
//      line = scanItemFromCmdLine();
//      if(line == null) break;
//      checkout = produceCheckout(line, basket);
//      System.out.println(checkout);
//    } while(!line.trim().equals(""));

//    System.out.println(checkout);
  }
}
