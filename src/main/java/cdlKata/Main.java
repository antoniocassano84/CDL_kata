package cdlKata;

public class Main {

  public static void main(String[] args) {

    Checkout checkout = new Checkout(Checkout.readFromDBOrFile());  // Select File or DB
    Basket basket = new Basket();
    String line;  // Here we out user input from Scanner on cmd line

    // Print the catalog
    checkout.printCatalog();

    do {
      // Scan an item from command line
      line = checkout.scanItemFromCmdLine();
      if(line == null) break;

      // Print the total to pay
      checkout.updateBasket(line, basket);
      System.out.println(basket);

    } while(!line.trim().equals(""));  // end if user input empty string

    System.out.println(basket);
  }
}
