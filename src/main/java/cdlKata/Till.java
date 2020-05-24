package cdlKata;

import cdlKata.datasource.Dao;
import cdlKata.datasource.ItemDaoDB;
import cdlKata.datasource.ItemDaoFile;

import java.util.Optional;
import java.util.Scanner;

/**
 * This class holds some till functionality like scan an item or print catalog.
 * It contains a reference to a DAO<Item> object
 */
public class Till {

  private Dao<Item> dao;

  /** This builds a Till object from a string indicating whether the user wants to read the catalog
   * from the DB or from the csv file.
   * @param fileORdb this string is either "DB" or "FILE". BY default, the program reads from DB
   */
  public Till(String fileORdb) {
    switch (fileORdb) {
      case "FILE":
        this.dao = new ItemDaoFile();
        break;
      case "DB":
        this.dao = new ItemDaoDB();
      default:
        this.dao = new ItemDaoDB();
    }
  }

  /**
   * This function is used in Till's constructor to determine the type of
   * implementation of the DAO class to use.
   * @return A string which contains either "DB" or "FILE".
   */
  public static String readFromDBOrFile() {
    String fileOrDB;
    do {
      Scanner scanner = new Scanner(System.in);
      System.out.print("Select between reading from 'file' or 'DB': ");
      String line = scanner.nextLine().toUpperCase().trim();
      fileOrDB = !line.equals("FILE") && !line.equals("DB") ? null : line;
      if(fileOrDB == null) System.out.println("Not a valid choice. Try again");
    } while(fileOrDB == null);
    System.out.println("Reading from : '" + fileOrDB + "'");
    return fileOrDB;
  }

  /** This function reads the user input for an item to be added or remove from the system.
   * @return the line as entered bu the user or null if the user typed an empty string.
   */
  public String scanItemFromCmdLine() {
    String line;
    Scanner scanner = new Scanner(System.in);
    System.out.print("Please enter an Item name (or whitespace to exit): ");
    line = scanner.nextLine();
    return line.trim().equals("") ? null : line;
  }

  /** This function parses the line as entered by the user in the scanItemFromCmdLine and either
   *  add  or remove the correspoding item from the Basket object provided as parameter.
   * @param line this is the line as returned from scanItemFromCmdLine.
   * @param basket this is the Basket object to manipulate.
   * @return it returns a Checkout object which could be directly printed to know the amount to pay.
   */
  public void produceCheckout(String line, Basket basket) {
    boolean remove = line.startsWith("-");
    if(remove) line = line.substring(1);
    Optional<Item> optItem = this.dao.get(line);
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
  }

//  public Checkout produceCheckout(String line, Basket basket) {
//    boolean remove = line.startsWith("-");
//    if(remove) line = line.substring(1);
//    Optional<Item> optItem = this.dao.get(line);
//    if(!optItem.isPresent()) {
//      System.out.println("No item named '" + line + "' in the system");
//    }
//    else {
//      Item item = optItem.get();
//      if(remove) { // remove item from the basket
//        if(basket.removeItemFromBasket(item) == -1)
//          System.out.println("Not possible to remove " + line);
//      } else { // add item to basket
//        basket.addItemToBasket(item);
//      }
//    }
//    return new Checkout(basket);
//  }

  /**
   * This function simply uses the DAO getALL function to printout
   * the list of all available items in the system.
   */
  public void printCatalog() {
    System.out.println("-----------");
    System.out.println(" I T E M S ");
    for(Item item : this.dao.getAll()) {
      System.out.println(item);
    }
    System.out.println("-----------");
  }
}
