package cdlKata;

import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BasketTest {

  private Basket basket;

  @BeforeEach
  void setUp() {
    basket = new Basket();
  }

  @Test
  @Order(1)
  void printEmptyBasket() {
    assertEquals("Basket[ ] : ", basket.toString());
  }

  @Test
  @Order(2)
  void addToBasketTrue() {
    assertTrue(basket.addToBasket('A'));
  }

  @Test
  @Order(2)
  void addToBasketFalse() {
    assertFalse(basket.addToBasket('Z'));
  }

  @Test
  @Order(3)
  void getDealFound() {
    assertEquals("A,3,1.30", basket.getDeal(Item.valueOf("A"), Arrays.asList("A,3,1.30", "B,2,0.45")));
  }

  @Test
  @Order(3)
  void getDealNotFound() {
    assertNull(basket.getDeal(Item.valueOf("C"), Arrays.asList("A,3,1.30", "B,2,0.45")));
  }

  @Test
  void checkOut() {
    assertEquals(0.0, basket.checkOut(Arrays.asList("A,3,1.30", "B,2,0.45")));
  }
}