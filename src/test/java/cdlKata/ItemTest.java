package cdlKata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
  void getPrice() {
  }

  @Test
  void getAmount() {
  }

  @Test
  void incrementAmountByOne() {
  }

  @Test
  void getSubTotalItem() {
  }

  @Test
  void testToString() {
    assertEquals("A{p:0.5, q:0}", itemA.toString());
    assertEquals("B{p:0.3, q:0}", itemB.toString());
    assertEquals("C{p:0.2, q:0}", itemC.toString());
    assertEquals("D{p:0.15, q:0}", itemD.toString());

  }
}