package cdlKata;

import java.util.*;

public class Basket {

  private List<Item> items;

  public Basket() {
    items = new ArrayList<>();
  }

  public void printItems() {
    for(Item i : this.items) System.out.print(i + "; ");
  }

  private int getItemIndex(Item item, List<Item> itemList) {
    int index = -1;
    for(int i = 0; i<itemList.size(); i++)
      if(itemList.get(i).equals(item))
        index = i;
    return index;
  }



  public void addToBasket(char ch) {
    Item item = Item.valueOf(Character.toString(ch));
    int index = getItemIndex(item, this.items);
    if(index == -1) {
      item.incrementAmountBYOne();
      this.items.add(item);
    } else {
        int currentAmount = this.items.get(index).getAmount();
        item.setAmount(++currentAmount);
        this.items.set(index, item);
    }
  }

  public String getDeal(Item item, List<String> deals) {
    for(String s : deals)
      if(s.startsWith(item.name()))
        return s;
    return null;
  }

  public double checkOut(List<String> rules) {
    double total = 0.0;
    for(Item item : this.items) {
      String deal = getDeal(item, rules);
      if (deal == null) {
        total += item.getSubTotalItem();
      } else {
        String[] values = deal.split(",");
        int quantity = Integer.parseInt(values[1]);
        double reduced = Double.parseDouble(values[2]);
        if (item.getAmount() < quantity)
          total += item.getSubTotalItem();
        else {
          int group = item.getAmount() / quantity;
          int spare = item.getAmount() % quantity;
          total += group * reduced;
          total += spare * item.getPrice();
        }
      }
    }
    return total;
  }

}