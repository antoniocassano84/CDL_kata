package cdlKata;

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
}
