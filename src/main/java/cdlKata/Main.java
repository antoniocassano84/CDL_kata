package cdlKata;

public class Main {

  public static void main(String[] args) {

    Till till = new Till(Till.readFromDBOrFile());  // Select File or DB
    Basket basket = new Basket();
    String line;  // Here we out user input from Scanner on cmd line

    // Print the catalog
    till.printCatalog();

    do {
      // Scan an item from command line
      line = till.scanItemFromCmdLine();
      if(line == null) break;

      // Print the total to pay
      till.produceCheckout(line, basket);
      System.out.println(basket);

    } while(!line.trim().equals(""));  // end if user input empty string

    System.out.println(basket);
  }
}
