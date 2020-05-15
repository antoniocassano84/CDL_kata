package cdlKata;

import org.junit.jupiter.api.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
  @Order(1)
  void basketEmptyWhenCreated() {
    assertTrue(basket.getItems().isEmpty());
  }

  @Test
  @Order(2)
  void isItemInBasketFalse() {
    assertFalse(basket.isItemInBasket(itemA));
  }

  @Test
  @Order(3)
  void isItemInBasketTrue() {
    basket.addItemToBasket(itemA);
    assertTrue(basket.isItemInBasket(itemA));
  }


  @Test
  @Order(4)
  void addItemToBasketFirstTime() {
    basket.addItemToBasket(itemA);
    assertTrue(basket.getItems().containsKey(itemA));
    assertEquals(1, basket.getItems().size());
  }

  @Test
  @Order(5)
  void addItemToBasketSecondTime() {
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemA);
    assertEquals(2, basket.getItems().get(itemA));
    assertEquals(1, basket.getItems().size());
  }

  @Test
  @Order(6)
  void addItemToBasketTwoDifferentObjects() {
    basket.addItemToBasket(new Item("A,0.50,3,1.30"));
    basket.addItemToBasket(new Item("A,0.50,3,1.30"));
    assertEquals(2, basket.getItems().get(itemA));
    assertEquals(1, basket.getItems().size());
  }

  @Test
  @Order(7)
  void addTwoDifferentItemsToBasket() {
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemC);
    assertEquals(1, basket.getItems().get(itemA));
    assertEquals(1, basket.getItems().get(itemC));
    assertEquals(2, basket.getItems().size());
  }

  @Test
  @Order(8)
  void removeItemFromBasketNotFound() {
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemC);
    assertNull(basket.removeItemFromBasket(itemB));
    assertEquals(1, basket.getItems().get(itemA));
    assertEquals(1, basket.getItems().get(itemC));
    assertEquals(2, basket.getItems().size());
  }

  @Test
  @Order(9)
  void removeItemFromBasketFirstTime() {
    for (int i = 0; i < 3; i++)
      basket.addItemToBasket(itemA);
    basket.removeItemFromBasket(itemA);
    assertEquals(2, basket.getItems().get(itemA));
  }

  @Test
  @Order(10)
  void removeItemFromBasketSecondTime() {
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemA);
    basket.removeItemFromBasket(itemA);
    basket.removeItemFromBasket(itemA);
    assertFalse(basket.getItems().containsKey(itemA));
    assertNull(basket.getItems().get(itemA));
    assertFalse(basket.getItems().containsKey(itemA));
  }

  @Test
  @Order(11)
  void removeItemFromBasketthirdTime() {
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemA);
    basket.removeItemFromBasket(itemA);
    basket.removeItemFromBasket(itemA);
    basket.removeItemFromBasket(itemA);
    assertNull(basket.getItems().get(itemA));
    assertFalse(basket.getItems().containsKey(itemA));
  }

  @Test
  @Order(12)
  void calculateFullPriceEmptyBasket() {
    assertEquals(0.00, basket.calculateFullPrice());
  }

  @Test
  @Order(13)
  void calculateFullPriceAandCInBasket() {
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemC);
    assertEquals(1.70, basket.calculateFullPrice(), 0.01);
  }

  @Test
  @Order(14)
  void printBasket() {
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemC);
    StringBuilder sb = new StringBuilder();
    sb.append("----------------\n");
    sb.append("BASKET CONTENT\n");
    sb.append("----------------\n");
    sb.append("ITEM\tQUANTITY\n");
    sb.append("C\t\t1\n");
    sb.append("A\t\t1\n");
    sb.append("----------------\n");
    sb.append("TOTAL\t0.70 ");
    sb.append((char) 163);
    assertEquals(sb.toString(), basket.toString());
  }

  @Test
  @Order(15)
  void printBasketAandCTwice() {
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemC);
    basket.addItemToBasket(itemA);
    basket.addItemToBasket(itemC);
    StringBuilder sb = new StringBuilder();
    sb.append("----------------\n");
    sb.append("BASKET CONTENT\n");
    sb.append("----------------\n");
    sb.append("ITEM\tQUANTITY\n");
    sb.append("C\t\t2\n");
    sb.append("A\t\t2\n");
    sb.append("----------------\n");
    sb.append("TOTAL\t0.70 ");
    sb.append((char) 163);
    assertEquals(sb.toString(), basket.toString());
  }

  @Test
  @Order(16)
  void printEmptyBasket() {
    StringBuilder sb = new StringBuilder();
    sb.append("----------------\n");
    sb.append("BASKET CONTENT\n");
    sb.append("----------------\n");
    sb.append("ITEM\tQUANTITY\n");
    sb.append("----------------\n");
    sb.append("TOTAL\t0.00 ");
    sb.append((char) 163);
    assertEquals(sb.toString(), basket.toString());
  }


}