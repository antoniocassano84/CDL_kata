package cdlKata;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class BasketTest {

  private Basket basket;
  private Item itemA;
  private Item itemB;
  private Item itemC;

  @BeforeEach
  void setUp() {
    basket = new Basket();
    itemA = new Item("A,0.50,3,1.30");
    itemB = new Item("B,0.30,2,0.45");
    itemC = new Item("C,0.20,,");
  }

  @Test
  void basketEmptyWhenCreated() {
    assertTrue(basket.getItems().isEmpty());
  }

  @Test
  void isItemInBasket() {
    basket.addItemToBasket(itemA);
    assertTrue(basket.getItems().containsKey(itemA));
  }

  @Test
  void addItemToBasketCheckSize() {
    basket.addItemToBasket(itemA);
    assertEquals(1, basket.getItems().size());
  }

  @Test
  void addItemToBasketValue() {
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemA);
    assertEquals(2, basket.getItems().get(itemA));
  }

  @Test
  void addItemToBasketSizeTwo() {
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemA);
    assertEquals(1, basket.getItems().size());
  }

  @Test
  void addItemToBasketGetNewObjValue() {
    basket.addItemToBasket(new Item("A,0.50,3,1.30"));
    basket.addItemToBasket(new Item("A,0.50,3,1.30"));
    assertEquals(2, basket.getItems().get(new Item("A,0.50,3,1.30")));
  }

  @Test
  void addItemToBasketTwoDifferentObjectsSize() {
    basket.addItemToBasket(new Item("A,0.50,3,1.30"));
    basket.addItemToBasket(new Item("A,0.50,3,1.30"));
    assertEquals(1, basket.getItems().size());
  }

  @Test
  void addTwoDifferentItemsToBasketA() {
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemC);
    assertEquals(1, basket.getItems().get(itemA));
  }

  @Test
  void addTwoDifferentItemsToBasketC() {
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemC);
    assertEquals(1, basket.getItems().get(itemC));
  }

  @Test
  void addTwoDifferentItemsToBasketSizeAC() {
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemC);
    assertEquals(2, basket.getItems().size());
  }

  @Test
  void removeItemFromBasketNotFound() {
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemC);
    assertEquals(-1, basket.removeItemFromBasket(itemB));
    assertEquals(1, basket.getItems().get(itemA));
    assertEquals(1, basket.getItems().get(itemC));
    assertEquals(2, basket.getItems().size());
  }

  @Test
  void removeItemFromBasketFirstTime() {
    for (int i = 0; i < 3; i++)
      basket.addItemToBasket(itemA);
    basket.removeItemFromBasket(itemA);
    assertEquals(2, basket.getItems().get(itemA));
  }

  @Test
  void removeItemFromBasketSecondTimeContainsA() {
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemA);
    basket.removeItemFromBasket(itemA);
    basket.removeItemFromBasket(itemA);
    assertFalse(basket.getItems().containsKey(itemA));
  }

  @Test
  void removeItemFromBasketNullA() {
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemA);
    basket.removeItemFromBasket(itemA);
    basket.removeItemFromBasket(itemA);
    assertNull(basket.getItems().get(itemA));
  }

  @Test
  void removeItemFromBasketthirdTimeGet() {
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemA);
    basket.removeItemFromBasket(itemA);
    basket.removeItemFromBasket(itemA);
    basket.removeItemFromBasket(itemA);
    assertNull(basket.getItems().get(itemA));
  }

  @Test
  void removeItemFromBasketthirdTimeContains() {
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemA);
    basket.removeItemFromBasket(itemA);
    basket.removeItemFromBasket(itemA);
    basket.removeItemFromBasket(itemA);
    assertFalse(basket.getItems().containsKey(itemA));
  }

  @Test
  void calculateFullPriceEmptyBasket() {
    assertEquals(0.00, basket.calculateFullPrice());
  }

  @Test
  void calculateFullPriceAandCInBasket() {
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemC);
    assertEquals(1.70, basket.calculateFullPrice(), 0.01);
  }

  @Test
  void printBasket() {
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemC);
    String sb = "----------------\n" +
            "BASKET CONTENT\n" +
            "----------------\n" +
            "ITEM\tQUANTITY\n" +
            "A\t\t1\n" +
            "C\t\t1\n" +
            "----------------\n" +
            "TOTAL\t0.70 " +
            (char) 163;
    assertEquals(sb, basket.toString());
  }

  @Test
  void printBasketOrder() {
    basket.addItemToBasket(itemC);
    basket.addItemToBasket(itemA);
    String sb = "----------------\n" +
            "BASKET CONTENT\n" +
            "----------------\n" +
            "ITEM\tQUANTITY\n" +
            "C\t\t1\n" +
            "A\t\t1\n" +
            "----------------\n" +
            "TOTAL\t0.70 " +
            (char) 163;
    assertEquals(sb, basket.toString());
  }

  @Test
  void printBasketAandCTwice() {
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemC);
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemC);
    String sb = "----------------\n" +
            "BASKET CONTENT\n" +
            "----------------\n" +
            "ITEM\tQUANTITY\n" +
            "A\t\t2\n" +
            "C\t\t2\n" +
            "----------------\n" +
            "TOTAL\t1.40 " +
            (char) 163;
    assertEquals(sb, basket.toString());
  }

  @Test
  void printBasketAandCTwiceNewObj() {
    basket.addItemToBasket(new Item("A,0.50,3,1.30"));
    basket.addItemToBasket(new Item("C,0.20,,"));
    basket.addItemToBasket(new Item("A,0.50,3,1.30"));
    basket.addItemToBasket(new Item("C,0.20,,"));
    String sb = "----------------\n" +
            "BASKET CONTENT\n" +
            "----------------\n" +
            "ITEM\tQUANTITY\n" +
            "A\t\t2\n" +
            "C\t\t2\n" +
            "----------------\n" +
            "TOTAL\t1.40 " +
            (char) 163;
    assertEquals(sb, basket.toString());
  }

  @Test
  void printEmptyBasket() {
    String sb = "----------------\n" +
            "BASKET CONTENT\n" +
            "----------------\n" +
            "ITEM\tQUANTITY\n" +
            "----------------\n" +
            "TOTAL\t0.00 " +
            (char) 163;
    assertEquals(sb, basket.toString());
  }

}