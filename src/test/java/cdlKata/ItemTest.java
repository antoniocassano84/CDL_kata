package cdlKata;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ItemTest {

  private Item itemA;
  private Item itemB;
  private Item itemC;
  private Item itemD;


  @BeforeEach
  void setUp() {
    itemA = new Item("A,0.50,3,1.30");
    itemB = new Item("B,0.30,2,0.45");
    itemC = new Item("C,0.20,,");
    itemD = new Item("D,0.15,,");
  }

  @Test
  @Order(1)
  void getNameA() {
    assertEquals("A", itemA.getName());
  }

  @Test
  @Order(2)
  void getNameB() {
    assertEquals("B", itemB.getName());
  }

  @Test
  @Order(3)
  void getNameC() {
    assertEquals("C", itemC.getName());
  }

  @Test
  @Order(4)
  void getNameD() {
    assertEquals("D", itemD.getName());
  }

  @Test
  @Order(5)
  void getUnitPriceA() {
    assertEquals(0.50, itemA.getUnitPrice());
  }

  @Test
  @Order(6)
  void getUnitPriceB() {
    assertEquals(0.30, itemB.getUnitPrice());
  }

  @Test
  @Order(7)
  void getUnitPriceC() {
    assertEquals(0.20, itemC.getUnitPrice());
  }

  @Test
  @Order(8)
  void getUnitPriceD() {
    assertEquals(0.15, itemD.getUnitPrice());
  }

  @Test
  @Order(9)
  void getMinAmountA() {
    assertEquals(3, itemA.getMinAmount());
  }

  @Test
  @Order(10)
  void getMinAmountB() {
    assertEquals(2, itemB.getMinAmount());
  }

  @Test
  @Order(11)
  void getMinAmountC() {
    assertEquals(0, itemC.getMinAmount());
  }

  @Test
  @Order(12)
  void getMinAmountD() {
    assertEquals(0, itemD.getMinAmount());
  }

  @Test
  @Order(13)
  void getSpecialPriceA() {
    assertEquals(1.30, itemA.getSpecialPrice());
  }

  @Test
  @Order(14)
  void getSpecialPriceB() {
    assertEquals(0.45, itemB.getSpecialPrice());
  }

  @Test
  @Order(15)
  void getSpecialPriceC() {
    assertEquals(0.00, itemC.getSpecialPrice());
  }

  @Test
  @Order(16)
  void getSpecialPriceD() {
    assertEquals(0.00, itemD.getSpecialPrice());
  }

  @Test
  @Order(17)
  void toStringA() {
    assertEquals("A[unitP=0.50,minA=3,specialP=1.30]", itemA.toString());
  }

  @Test
  @Order(18)
  void toStringB() {
    assertEquals("B[unitP=0.30,minA=2,specialP=0.45]", itemB.toString());
  }
  @Test
  @Order(19)
  void toStringC() {
    assertEquals("C[unitP=0.20]", itemC.toString());
  }
  @Test
  @Order(20)
  void toStringD() {
    assertEquals("D[unitP=0.15]", itemD.toString());
  }

}