package cdlKata;

import java.util.Map;

public class Checkout {

  private Basket basket;

  public Checkout(Basket basket) {
    this.basket = basket;
  }


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
}
