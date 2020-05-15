package cdlKata;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CheckoutTest {

  private Checkout checkout;
  private Basket basket;
  private Item itemA;
  private Item itemB;
  private Item itemC;;

  @BeforeEach
  void setUp() {
    basket = new Basket();
    checkout = new Checkout(basket);
    itemA = new Item("A,0.50,3,1.30");
    itemB = new Item("B,0.30,2,0.45");
    itemC = new Item("C,0.20,,");
  }

  @Test
  @Order(1)
  void getBasketEmpty() {
    assertEquals(0, checkout.getBasket().getItems().size());
  }

  @Test
  @Order(2)
  void getBasketTwoItems() {
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemC);
    checkout = new Checkout(basket);
    assertEquals(2, checkout.getBasket().getItems().size());
  }

  @Test
  @Order(3)
  void toPayWhenEmptyBasket() {
    assertEquals(0.00, checkout.toPay(), 0.01);
  }

  @Test
  @Order(4)
  void toPayWhenNoItemWithSpecialPriceInBasket() {
    basket.addItemToBasket(itemC);
    basket.addItemToBasket(itemC);
    basket.addItemToBasket(itemC);
    checkout = new Checkout(basket);
    assertEquals(0.60, checkout.toPay(), 0.01);
  }

  @Test
  @Order(5)
  void toPayWhenNoDealInBasket() {
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemB);
    checkout = new Checkout(basket);
    assertEquals(1.30, checkout.toPay(), 0.01);
  }

  @Test
  @Order(6)
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
  @Order(7)
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
  @Order(8)
  void printCheckoutTopayWithEmptyBasket() {
    StringBuilder sb = new StringBuilder();
    sb.append("----------------\n");
    sb.append("BASKET CONTENT\n");
    sb.append("----------------\n");
    sb.append("ITEM\tQUANTITY\n");
    sb.append("----------------\n");
    sb.append("TOTAL\t0.00 ");
    sb.append((char) 163);
    sb.append("\n----------------\n");
    sb.append("TO PAY\t0.00 ");
    sb.append((char) 163);
    assertEquals(sb.toString(), checkout.toString());

  }

}