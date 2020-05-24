package cdlKata;

/**
 * The Item class models a single item to buy. This will hold all the information related to
 * a single item which match columns' names in the CdlItems.csv, namely:
 *
 * "name"         : This is the name of the item as String and must be unique since this is the SKU
 *                  (Stock Keeping Units, or SKUs) -> e.g. "A";
 * "unitPrice"    : This is the price of a single unit of item as double -> e.g. 0.50;
 * "minAmount"    : This is the minimum amount of items you have to buy to get a deal as an int.
 *                  This field is set to zero if there are no deals for a specific item -> e.g. 3
 * "specialPrice" : This is the discounted price if you buy @minAmount of items for which deals were created.
 *                  This is a double -> for item "A" this is set to 1.30 for 3 items.
 *
 * @author  Antonio Cassano
 * @version 1.0
 * @since   16-05-2020
 */
public class Item {

  private String name;
  private double unitPrice;
  private int minAmount;
  private double specialPrice;

  public Item(String name, double unitPrice, int minAmount, double specialPrice) {
    this.name = name;
    this.unitPrice = unitPrice;
    this.minAmount = minAmount;
    this.specialPrice = specialPrice;
  }

  public Item(String name, double unitPrice) {
    this(name, unitPrice, 0, 0.0);
  }


  public static Item parseItem(String line) {
    String[] parts = line.split(",");
    Item parseditem;
    switch(parts.length) {
      case 2:
        parseditem = new Item(parts[0], Double.parseDouble(parts[1]));
        break;
      case 4:
        parseditem = new Item(parts[0], Double.parseDouble(parts[1]),
                              Integer.parseInt(parts[2]), Double.parseDouble(parts[3]));
        break;
      default:
        parseditem = null;
    }
    return parseditem;
  }

  /**
   * This is the getter for the 'name' field which is a String.
   * @return The unique SKU id as name of the item
   */
  public String getName() {
    return name;
  }

  /**
   * This is the getter for the 'unitPrice' field which is a double.
   * @return The price (in GBP) of a single item
   */
  public double getUnitPrice() {
    return unitPrice;
  }


  /**
   * This is the getter for the 'minAmount' field which is an int.
   * @return The minimum amount of item to buy if you want to be eligible for a discount
   */
  public int getMinAmount() {
    return minAmount;
  }

  /**
   * This is the getter for the 'specialPrice' field which is a double.
   * @return The discounted price if you buy minAMount of a certain item
   */
  public double getSpecialPrice() {
    return specialPrice;
  }

  /**
   * This overrides the toString() method from Object class.
   * @return A string representation of an item in the form:
   *         itemName[unitP=<unitPrice>,minA=<minAmount>,specialP=<specialPrice>]
   */
  @Override
  public String toString() {
    return this.name + "[" +
            "unitP=" + String.format("%.2f", this.unitPrice) +
            (this.minAmount == 0 ? "" : (",minA=" + this.minAmount)) +
            (this.specialPrice == 0.0 ? "" : (",specialP=" + String.format("%.2f", this.specialPrice)))  +
            ']';
  }


  /**
   * This overrides the equals() method from Object class.
   * @param o This is an Object object which will be cast to Item for checking
   * @return true if we are comparing an item reference with itself or
   *              if all the fields of item are the same.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;  // itemA.equals(itemA) returns true
    if (o == null || getClass() != o.getClass()) return false;

    Item item = (Item) o;

    if (Double.compare(item.getUnitPrice(), getUnitPrice()) != 0) return false;
    if (getMinAmount() != item.getMinAmount()) return false;
    if (Double.compare(item.getSpecialPrice(), getSpecialPrice()) != 0) return false;
    return getName().equals(item.getName());
  }


  /**
   * This overrides the hashCode() method from Object class.
   * @return It returns the hash code for an item.
   */
  @Override
  public int hashCode() {
    int result;
    long temp;
    result = getName().hashCode();
    temp = Double.doubleToLongBits(getUnitPrice());
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    result = 31 * result + getMinAmount();
    temp = Double.doubleToLongBits(getSpecialPrice());
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }
}
