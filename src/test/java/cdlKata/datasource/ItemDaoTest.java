package cdlKata.datasource;

import cdlKata.Item;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ItemDaoTest {

  private ItemDaoDB itemDaoDB;
  private Item itemA;
  private Item itemC;
  private Item itemE;
  private Item itemF;

  @BeforeEach
  void setUp() {
    itemDaoDB = new ItemDaoDB();
    itemA = new Item("A", 0.50, 3, 1.30);
    itemC = new Item("C", 0.20, 0, 0.00);
    itemE = new Item("E", 0.90, 4, 3.00);
    itemF = new Item("F", 1.00, 0, 0.00);
  }

  static Stream<Arguments> sourceMethodForGetAIsPresent() {
    final ItemDaoDB itemDaoDB = new ItemDaoDB();
    final ItemDaoDB itemDaoFile = new ItemDaoDB();
    return Stream.of(
            Arguments.of(itemDaoDB),
            Arguments.of(itemDaoFile)
    );
  }

  @ParameterizedTest
  @MethodSource("sourceMethodForGetAIsPresent")
  @Order(1)
  void getItemAIsPresent(Dao<Item> dao) {
    Optional<Item> opItem = dao.get("A");
    assertTrue(opItem.isPresent());
  }

  @Test
  @Order(2)
  void getItemA() {
    Optional<Item> opItem = itemDaoDB.get("A");
    opItem.ifPresent(item -> assertEquals(itemA, item));
  }

  @Test
  @Order(3)
  void getItemC() {
    Optional<Item> opItem = itemDaoDB.get("C");
    opItem.ifPresent(item -> assertEquals(itemC, item));
  }

  @Test
  @Order(4)
  void getItemNonPresent() {
    Optional<Item> opItem = itemDaoDB.get("Z");
    assertFalse(opItem.isPresent());
  }

  @Test
  @Order(5)
  void getAllSize() {
    assertEquals(4, itemDaoDB.getAll().size());
  }

  @Test
  @Order(6)
  void saveAddNewItemWithSpecialPrice() {
    itemDaoDB.save(itemE);
    assertEquals(5, itemDaoDB.getAll().size());
  }

  @Test
  @Order(7)
  void saveAddNewItemWithOutSpecialPrice() {
    itemDaoDB.save(itemF);
    assertEquals(6, itemDaoDB.getAll().size());
  }

  @Test
  @Order(8)
  void saveExistingItemE() {
    itemDaoDB.save(itemE);
    assertEquals(6, itemDaoDB.getAll().size());
  }

  @Test
  @Order(9)
  void update() {
    itemDaoDB.update(itemF, new String[] {"F", "3.00", "4", "10.00"});
    Optional<Item> updatedItemF = itemDaoDB.get("F");
    updatedItemF.ifPresent(item -> assertEquals(3.00, item.getUnitPrice()));
  }

  @Test
  @Order(10)
  void deleteF() {
    itemDaoDB.delete(itemF);
    Optional<Item> opItem = itemDaoDB.get("F");
    assertFalse(opItem.isPresent());
  }

  @Test
  @Order(11)
  void deleteE() {
    itemDaoDB.delete(itemE);
    Optional<Item> opItem = itemDaoDB.get("E");
    assertFalse(opItem.isPresent());
  }
}