package cdlKata;

import java.util.*;

public class Basket {

  private Map<Item, Integer> items;

  public Basket() {
    //items = new HashMap<>();
    items = new LinkedHashMap<>();
  }

  public Map<Item, Integer> getItems() {
    return items;
  }

  public void addItemToBasket(Item item) {
    items.put(item, items.getOrDefault(item,0) + 1);
  }

  public Integer removeItemFromBasket(Item item) {
    if(this.items.containsKey(item)) {
      int newAmount = this.items.get(item) - 1;
      this.items.put(item, newAmount);
      if(newAmount <= 0) this.items.remove(item);
      return newAmount;
    } else
      return null;
  }

  public double calculateFullPrice() {
    double basketFullPrice = 0.0;
    for (Map.Entry<Item, Integer> e : this.items.entrySet())
      basketFullPrice += (e.getKey().getUnitPrice() * e.getValue());
    return basketFullPrice;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("----------------\n");
    sb.append("BASKET CONTENT\n");
    sb.append("----------------\n");
    sb.append("ITEM\tQUANTITY\n");
    for (Map.Entry<Item, Integer> e : this.items.entrySet())
      sb.append(e.getKey().getName() + "\t\t" + e.getValue() + "\n");
    sb.append("----------------\n");
    sb.append("TOTAL\t");
    sb.append(String.format("%.2f %c", calculateFullPrice(), (char) 163));
    return sb.toString();
  }

}