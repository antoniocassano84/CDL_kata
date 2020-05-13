package cdlKata;

public enum Item {

  A(0.50), B(0.30), C(0.20), D(0.15);

  private final double price;
  private int amount;

  Item(double price) {
    this.price = price;
    this.amount = 0;
  }

  public double getPrice() {
    return this.price;
  }

  public int getAmount() {
    return this.amount;
  }

  public void setAmount(int amount) {
    if(amount < 0)
      throw new IllegalArgumentException("Amount must be non negative!");
    else
      this.amount = amount;
  }

  public void incrementAmountBYOne() {
    this.amount += 1;
  }

  public double getSubTotalItem() {
    return this.price * this.amount;
  }

  @Override
  public String toString() {
    return this.name() + "{" +
            "p:" + price +
            ", q:" + amount +
            '}';
  }
}
