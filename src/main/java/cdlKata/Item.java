package cdlKata;

import javax.print.attribute.standard.DateTimeAtCompleted;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Item {

  private String name;
  private double unitPrice;
  private int minAmount;
  private double specialPrice;

  Item(String line) {
    String[] parts = line.split(",");
    this.name = parts[0];
    this.unitPrice = Double.parseDouble(parts[1]);
    this.minAmount = parts.length == 4 ? Integer.parseInt(parts[2]) : 0;
    this.specialPrice = parts.length == 4 ? Double.parseDouble(parts[3]) : 0.00;
  }

  public String getName() {
    return name;
  }

  public double getUnitPrice() {
    return unitPrice;
  }

  public int getMinAmount() {
    return minAmount;
  }

  public double getSpecialPrice() {
    return specialPrice;
  }

  @Override
  public String toString() {
    return this.name + "[" +
            "unitP=" + String.format("%.2f", this.unitPrice) +
            (this.minAmount == 0 ? "" : (",minA=" + this.minAmount)) +
            (this.specialPrice == 0.0 ? "" : (",specialP=" + String.format("%.2f", this.specialPrice)))  +
            ']';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Item item = (Item) o;

    if (Double.compare(item.getUnitPrice(), getUnitPrice()) != 0) return false;
    if (getMinAmount() != item.getMinAmount()) return false;
    if (Double.compare(item.getSpecialPrice(), getSpecialPrice()) != 0) return false;
    return getName().equals(item.getName());
  }

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
