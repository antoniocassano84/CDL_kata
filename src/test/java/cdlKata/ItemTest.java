package cdlKata;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

  @AfterEach
  void teardown() {
    itemA = null;
    itemB = null;
    itemC = null;
    itemD = null;
  }

  @Test
  void getPrice() {
    assertEquals(0.50, itemA.getPrice());
    assertEquals(0.30, itemB.getPrice());
    assertEquals(0.20, itemC.getPrice());
    assertEquals(0.15, itemD.getPrice());
  }

  @Test
  void getAmount() {
    assertEquals(0, itemA.getAmount());
    assertEquals(0, itemB.getAmount());
    assertEquals(0, itemC.getAmount());
    assertEquals(0, itemD.getAmount());
  }

  @Test
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
  void setAmountNegative() {
    assertThrows(IllegalArgumentException.class, () -> itemA.setAmount(-200));
  }

  @Test
  void getSubTotalItem() {
    assertEquals(0, itemA.getSubTotalItem());
    assertEquals(0, itemB.getSubTotalItem());
    assertEquals(0, itemC.getSubTotalItem());
    assertEquals(0, itemD.getSubTotalItem());
  }

  @Test
  void testToString() {
    assertEquals("A{p:0.5, q:0}", itemA.toString());
    assertEquals("B{p:0.3, q:0}", itemB.toString());
    assertEquals("C{p:0.2, q:0}", itemC.toString());
    assertEquals("D{p:0.15, q:0}", itemD.toString());
  }

  @Test
  void testToStringAfterIncrement() {
    itemA.incrementAmountBYOne();
    assertEquals("A{p:0.5, q:1}", itemA.toString());
  }

}