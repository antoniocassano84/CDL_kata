package cdlKata;

import java.util.*;

/**
 * The Basket class models a shopping basket
 * It holds a LinkedHashMap whose elements are the items to buy.
 *
 * "items" is a LinkedHashMap from Item to Integer:
 * - Item is the key of the LinkedHashMap and represents the item to buy
 * - Integer is the amount of the specific item to buy
 *
 * @author  Antonio Cassano
 * @version 1.0
 * @since   16-05-2020
 */
public class Basket {

  private Map<Item, Integer> items;

  /**
   * This is the Basket's constructor.
   * It initialises items with an empty LinkedHashMap.
   */
  public Basket() {
    items = new LinkedHashMap<>();
  }

  /**
   * This is the getter for items.
   * @return Returns a map from Item to Integer.
   */
  public Map<Item, Integer> getItems() {
    return items;
  }

  /**
   * This method uses the getOrDefault() method to add
   * an item to a basket. If the item already exists in the basket
   * its value in the LinkedHashMap is just incremented by 1 otherwise
   * a new item with default value of zero is added then incremented.
   * @param item This is the item to add to the basket.
   */
  public void addItemToBasket(Item item) {
    items.put(item, items.getOrDefault(item,0) + 1);
  }

  /**
   * This method removes an item from the basket.
   * @param item This is the item to remove from the basket
   * @return it returns -1 if the operation was not executed
   */
    public int removeItemFromBasket(Item item) {
    if(this.items.containsKey(item)) {
      int newAmount = this.items.get(item) - 1;
      this.items.put(item, newAmount);
      if(newAmount <= 0) this.items.remove(item);
      return newAmount;
    } else
      return -1;
  }

  /**
   * This method calculates thethe total price of the basket in pound.
   * It loops through all the items in the basket and it calculates the summation
   * of the product of each unit price with the corresponding amount in the basket
   * @return The full price of the basket before special price rules applied
   */
  public double calculateFullPrice() {
    double basketFullPrice = 0.0;
    for (Map.Entry<Item, Integer> e : this.items.entrySet())
      basketFullPrice += (e.getKey().getUnitPrice() * e.getValue());
    return basketFullPrice;
  }

  /**
   * This method calculates the amount to pay at the till which
   * includes the special prices for each item (if there is a deal on that specific item)
   * @return it returns the amount a customer has to pay for its shopping basket.
   */
  public double toPay() {
    double topay = 0.0;
    for (Map.Entry<Item, Integer> e : this.getItems().entrySet()) {
      Item item = e.getKey();
      int amount = e.getValue();
      int groupOfDiscountedItems = item.getMinAmount() == 0 ? 0 : amount / item.getMinAmount();
      int nonDiscountedItems = item.getMinAmount() == 0 ? amount : amount % item.getMinAmount();
      topay += groupOfDiscountedItems * item.getSpecialPrice() +
              nonDiscountedItems * item.getUnitPrice();
    }
    return topay;
  }


  /**
   * It prints out a basket. See an example here below:
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
   *
   * @return it returns a String representation of the Basket object
   */
  @Override
  public String toString() {
    StringBuilder bStr = new StringBuilder("" +
            "----------------\n" +
            "BASKET CONTENT\n" +
            "----------------\n" +
            "ITEM\tQUANTITY\n");
    for (Map.Entry<Item, Integer> e : this.items.entrySet()) {
      bStr.append(e.getKey().getName()).append("\t\t").append(e.getValue()).append("\n");
    }
    bStr.append("----------------\n" + "TOTAL\t")
            .append(String.format("%.2f %c", calculateFullPrice(), (char) 163));
    bStr.append("\n----------------\n" +
                "TO PAY\t" +
                String.format("%.2f %c", this.toPay(), (char) 163));
    return bStr.toString();
  }

}