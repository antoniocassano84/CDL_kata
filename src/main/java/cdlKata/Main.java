package cdlKata;

public class Main {

  public static void main(String[] args) {

    Till till = new Till(Till.readFromDBOrFile());  // Select File or DB
    Basket basket = new Basket();
    String line;
    Checkout checkout = new Checkout(basket);

    // Print the catalog
    till.printCatalog();

    do {
      // Scan an item from command line:
      line = till.scanItemFromCmdLine();
      if(line == null) break;
      checkout = till.produceCheckout(line, basket);
      System.out.println(checkout);
    } while(!line.trim().equals(""));

    System.out.println(checkout);
  }
}
