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
  void toPayWhenEmptyBasket() {
    assertEquals(0.00, checkout.toPay(), 0.01);
  }

  @Test
  @Order(2)
  void toPayWhenNoItemWithSpecialPriceInBasket() {
    basket.addItemToBasket(itemC);
    basket.addItemToBasket(itemC);
    basket.addItemToBasket(itemC);
    checkout = new Checkout(basket);
    assertEquals(0.60, checkout.toPay(), 0.01);
  }

  @Test
  @Order(3)
  void toPayWhenNoDealInBasket() {
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemB);
    checkout = new Checkout(basket);
    assertEquals(1.30, checkout.toPay(), 0.01);
  }

  @Test
  @Order(4)
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
  @Order(5)
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

}