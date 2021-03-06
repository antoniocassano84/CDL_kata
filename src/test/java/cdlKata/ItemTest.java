package cdlKata;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


class ItemTest {

  private Item itemA;
  private Item itemB;
  private Item itemC;
  private Item itemD;


  @BeforeEach
  void setUp() {
    itemA = new Item("A", 0.50, 3, 1.30);
    itemB = new Item("B", 0.30, 2, 0.45);
    itemC = new Item("C", 0.20);
    itemD = new Item("D", 0.15);
  }

  @Test
  void parseItemA() {
    assertEquals(itemA, Item.parseItem("A,0.50,3,1.30"));
  }

  @Test
  void parseItemB() {
    assertEquals(itemB, Item.parseItem("B,0.30,2,0.45"));
  }

  @Test
  void parseItemC() {
    assertEquals(itemC, Item.parseItem("C,0.20"));
  }

  @Test
  void parseItemD() {
    assertEquals(itemD, Item.parseItem("D,0.15"));
  }

  @Test
  void parseItemNull() {
    assertNull(Item.parseItem("G,0.90,0.45"));
  }

  @Test
  void parseItemOnlyName() {
    assertNull(Item.parseItem("Z"));
  }

  @Test
  void parseItemFiveParts() {
    assertNull(Item.parseItem("Y,1.01,4,0.80,444"));
  }

  @Test
  void parseItemEmptyString() {
    assertNull(Item.parseItem(""));
  }

  @Test
  void getNameA() {
    assertEquals("A", itemA.getName());
  }

  @Test
  void getNameB() {
    assertEquals("B", itemB.getName());
  }

  @Test
  void getNameC() {
    assertEquals("C", itemC.getName());
  }

  @Test
  void getNameD() {
    assertEquals("D", itemD.getName());
  }

  @Test
  void getUnitPriceA() {
    assertEquals(0.50, itemA.getUnitPrice());
  }

  @Test
  void getUnitPriceB() {
    assertEquals(0.30, itemB.getUnitPrice());
  }

  @Test
  void getUnitPriceC() {
    assertEquals(0.20, itemC.getUnitPrice());
  }

  @Test
  void getUnitPriceD() {
    assertEquals(0.15, itemD.getUnitPrice());
  }

  @Test
  void getMinAmountA() {
    assertEquals(3, itemA.getMinAmount());
  }

  @Test
  void getMinAmountB() {
    assertEquals(2, itemB.getMinAmount());
  }

  @Test
  void getMinAmountC() {
    assertEquals(0, itemC.getMinAmount());
  }

  @Test
  void getMinAmountD() {
    assertEquals(0, itemD.getMinAmount());
  }

  @Test
  void getSpecialPriceA() {
    assertEquals(1.30, itemA.getSpecialPrice());
  }

  @Test
  void getSpecialPriceB() {
    assertEquals(0.45, itemB.getSpecialPrice());
  }

  @Test
  void getSpecialPriceC() {
    assertEquals(0.00, itemC.getSpecialPrice());
  }

  @Test
  void getSpecialPriceD() {
    assertEquals(0.00, itemD.getSpecialPrice());
  }

  @Test
  void toStringA() {
    assertEquals("A[unitP=0.50,minA=3,specialP=1.30]", itemA.toString());
  }

  @Test
  void toStringB() {
    assertEquals("B[unitP=0.30,minA=2,specialP=0.45]", itemB.toString());
  }

  @Test
  void toStringC() {
    assertEquals("C[unitP=0.20]", itemC.toString());
  }

  @Test
  void toStringD() {
    assertEquals("D[unitP=0.15]", itemD.toString());
  }

  @Test
  void equalsItemsSameRef() {
    assertEquals(itemA, itemA);
  }

  @Test
  void equalsItemsSameObject() {
    Item itemAA = new Item("A", 0.50, 3, 1.30);
    assertEquals(itemAA, itemA);
  }

  @Test
  void equalsItemsTowReferencesToSameObject() {
    Item itemAA = new Item("A", 0.50, 3, 1.30);
    Item itemAA2 = new Item("A", 0.50, 3, 1.30);
    assertEquals(itemAA, itemAA2);
  }

  @Test
  void notEqualsItems() {
    assertNotEquals(itemB, itemA);
  }

}