package cdlKata;

import java.util.Map;

/**
 * The Checkout class models a checkout transaction
 * It holds a Basket object whose elements are the items to buy.
 *
 * "basket" is the Basket object which hold Items as LinkedHashMap
 *
 * @author  Antonio Cassano
 * @version 1.0
 * @since   16-05-2020
 */
public class Checkout {

  private Basket basket;

  /**
   * The Checkout constructor just initialises its private Basket field with
   * a basket which is provided as parameter.
   * @param basket This is the basket instance which holds the items' list.
   */
  public Checkout(Basket basket) {
    this.basket = basket;
  }

  /**
   * This is a getter for the private Basket object
   * @return It returns the basket object
   */
  public Basket getBasket() {
    return this.basket;
  }


  /**
   * This method calculates the amount to pay at the till which
   * includes the special prices for each item (if there is a deal on that specific item)
   * @return it returns the amount a customer has to pay for its shopping basket.
   */
  public double toPay() {
    double topay = 0.0;
    Item item;
    int amount;
    for (Map.Entry<Item, Integer> e : basket.getItems().entrySet()) {
      item = e.getKey();
      amount = e.getValue();
      if(item.getMinAmount() == 0)  // no deals
        topay += (item.getUnitPrice() * amount);
      else {  // with deals
        int group = amount / item.getMinAmount();
        topay += group * item.getSpecialPrice();
        int spare = amount % item.getMinAmount();
        topay += spare * item.getUnitPrice();
      }
    }  // end for
    return topay;
  }

  /**
   *It prints out a checkout information as well as the basket.
   *  See an example here below:
   * ----------------
   * BASKET CONTENT
   * ----------------
   * ITEM	QUANTITY
   * A		1
   * B		1
   * C		1
   * D		1
   * ----------------
   * TOTAL	1.15 £
   * ----------------
   * TO PAY	1.15 £
   *
   * @return it returns a String representation of the Checkout object
   */
  @Override
  public String toString() {
    return this.basket.toString() +
            "\n----------------\n" +
            "TO PAY\t" +
            String.format("%.2f %c", this.toPay(), (char) 163);
  }
}
