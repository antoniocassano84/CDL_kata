package cdlKata;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ItemTest {

  private Item itemA;
  private Item itemB;
  private Item itemC;
  private Item itemD;

  @BeforeEach
  void setUp() {
    itemA = Item.valueOf("A");
    itemB = Item.valueOf("B");
    itemC = Item.valueOf("C");
    itemD = Item.valueOf("D");
    System.out.println("Setup: Four Items created!");
  }

  @Test
  @Order(1)
  void getPrice() {
    assertEquals(0.50, itemA.getPrice());
    assertEquals(0.30, itemB.getPrice());
    assertEquals(0.20, itemC.getPrice());
    assertEquals(0.15, itemD.getPrice());
  }

  @Test
  @Order(2)
  void getAmount() {
    assertEquals(0, itemA.getAmount());
    assertEquals(0, itemB.getAmount());
    assertEquals(0, itemC.getAmount());
    assertEquals(0, itemD.getAmount());
  }

  @Test
  @Order(3)
  void getSubTotalItem() {
    assertEquals(0, itemA.getSubTotalItem());
    assertEquals(0, itemB.getSubTotalItem());
    assertEquals(0, itemC.getSubTotalItem());
    assertEquals(0, itemD.getSubTotalItem());
  }

  @Test
  @Order(4)
  void incrementAmountByOne() {
    itemA.incrementAmountBYOne();
    itemB.incrementAmountBYOne();
    itemC.incrementAmountBYOne();
    itemD.incrementAmountBYOne();
    assertEquals(1, itemA.getAmount());
    assertEquals(1, itemB.getAmount());
    assertEquals(1, itemC.getAmount());
    assertEquals(1, itemD.getAmount());
  }

  @Test
  @Order(5)
  void setAmount() {
    itemA.setAmount(100);
    itemB.setAmount(0);
    itemC.setAmount(1000);
    itemD.setAmount(200);
    assertEquals(100, itemA.getAmount());
    assertEquals(0, itemB.getAmount());
    assertEquals(1000, itemC.getAmount());
    assertEquals(200, itemD.getAmount());
  }

  @Test
  @Order(6)
  void setAmountNegative() {
    assertThrows(IllegalArgumentException.class, () -> itemA.setAmount(-200));
  }

  @Test
  @Order(7)
  void testToString() {
    assertEquals("A{p:0.5, q:100}", itemA.toString());
    assertEquals("B{p:0.3, q:0}", itemB.toString());
    assertEquals("C{p:0.2, q:1000}", itemC.toString());
    assertEquals("D{p:0.15, q:200}", itemD.toString());
  }

  @Test
  @Order(8)
  void testToStringAfterIncrement() {
    itemA.incrementAmountBYOne();
    assertEquals("A{p:0.5, q:101}", itemA.toString());
  }

}