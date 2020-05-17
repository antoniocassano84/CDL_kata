package cdlKata;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutTest {

  private Checkout checkout;
  private Basket basket;
  private Item itemA;
  private Item itemB;
  private Item itemC;

  @BeforeEach
  void setUp() {
    basket = new Basket();
    checkout = new Checkout(basket);
    itemA = new Item("A,0.50,3,1.30");
    itemB = new Item("B,0.30,2,0.45");
    itemC = new Item("C,0.20,,");
  }

  @Test
  void getBasketEmpty() {
    assertEquals(0, checkout.getBasket().getItems().size());
  }

  @Test
  void getBasketTwoItems() {
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemC);
    checkout = new Checkout(basket);
    assertEquals(2, checkout.getBasket().getItems().size());
  }

  @Test
  void toPayWhenEmptyBasket() {
    assertEquals(0.00, checkout.toPay(), 0.01);
  }

  @Test
  void toPayWhenNoItemWithSpecialPriceInBasket() {
    basket.addItemToBasket(itemC);
    basket.addItemToBasket(itemC);
    basket.addItemToBasket(itemC);
    checkout = new Checkout(basket);
    assertEquals(0.60, checkout.toPay(), 0.01);
  }

  @Test
  void toPayWhenNoDealInBasket() {
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemB);
    checkout = new Checkout(basket);
    assertEquals(1.30, checkout.toPay(), 0.01);
  }

  @Test
  void toPayWhenDealInBasket() {
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemB);
    basket.addItemToBasket(itemB);
    checkout = new Checkout(basket);
    assertEquals(1.75, checkout.toPay(), 0.01);
  }

  @Test
  void toPayWhenEightItemsInBasket() {
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemB);
    basket.addItemToBasket(itemB);
    basket.addItemToBasket(itemB);
    basket.addItemToBasket(itemC);
    checkout = new Checkout(basket);
    assertEquals(2.75, checkout.toPay(), 0.01);
  }

  @Test
  void printCheckoutTopayWithEmptyBasket() {
    String sb = "----------------\n" +
            "BASKET CONTENT\n" +
            "----------------\n" +
            "ITEM\tQUANTITY\n" +
            "----------------\n" +
            "TOTAL\t0.00 " +
            (char) 163 +
            "\n----------------\n" +
            "TO PAY\t0.00 " +
            (char) 163;
    assertEquals(sb, checkout.toString());
  }

}