package cdlKata.datasource;

import cdlKata.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ItemDaoTest {

  private Item itemA;
  private Item itemC;
  private Item itemE;
  private Item itemF;

  @BeforeEach
  void setUp() {
    itemA = new Item("A", 0.50, 3, 1.30);
    itemC = new Item("C", 0.20, 0, 0.00);
    itemE = new Item("E", 0.90, 4, 3.00);
    itemF = new Item("F", 0.99, 5, 4.00);
  }

  static Stream<Arguments> sourceMethod() {
    final ItemDaoDB itemDaoDB = new ItemDaoDB();
    final ItemDaoFile itemDaoFile = new ItemDaoFile();
    return Stream.of(
            Arguments.of(itemDaoDB),
            Arguments.of(itemDaoFile)
    );
  }

  @ParameterizedTest
  @MethodSource("sourceMethod")
  @Order(1)
  void getItemA(Dao<Item> dao) {
    Optional<Item> opItem = dao.get("A");
    opItem.ifPresent(item -> assertEquals(itemA, opItem.get()));

  }

  @ParameterizedTest
  @MethodSource("sourceMethod")
  @Order(2)
  void getItemC(Dao<Item> dao) {
    Optional<Item> opItem = dao.get("C");
    opItem.ifPresent(item -> assertEquals(itemC, opItem.get()));
  }

  @ParameterizedTest
  @MethodSource("sourceMethod")
  @Order(3)
  void getMissingItem(Dao<Item> dao) {
    Optional<Item> opItem = dao.get("X");
    assertFalse(opItem.isPresent());
  }


  @ParameterizedTest
  @MethodSource("sourceMethod")
  @Order(4)
  void getAllSize(Dao<Item> dao) {
    assertEquals(4, dao.getAll().size());
  }

  @ParameterizedTest
  @MethodSource("sourceMethod")
  @Order(5)
  void saveAddNewItemWithSpecialPrice(Dao<Item> dao) {
    dao.save(itemE);
    assertEquals(5, dao.getAll().size());
  }

  @ParameterizedTest
  @MethodSource("sourceMethod")
  @Order(6)
  void saveAddNewItemWithOutSpecialPrice(Dao<Item> dao) {
    dao.save(itemF);
    assertEquals(6, dao.getAll().size());
  }

  @ParameterizedTest
  @MethodSource("sourceMethod")
  @Order(7)
  void saveExistingItemE(Dao<Item> dao) {
    dao.save(itemE);
    assertEquals(6, dao.getAll().size());
  }

  @ParameterizedTest
  @MethodSource("sourceMethod")
  @Order(8)
  void updateExistingItem(Dao<Item> dao) {
    dao.update(itemF, new String[] {"3.00", "4", "10.00"});
    Optional<Item> updatedItemF = dao.get("F");
    updatedItemF.ifPresent(item -> assertEquals(3.00, item.getUnitPrice()));
  }

  @ParameterizedTest
  @MethodSource("sourceMethod")
  @Order(9)
  void deleteF(Dao<Item> dao) {
    dao.delete(itemF);
    assertEquals(5, dao.getAll().size());
  }

  @ParameterizedTest
  @MethodSource("sourceMethod")
  @Order(10)
  void deleteE(Dao<Item> dao) {
    dao.delete(itemE);
    assertEquals(4, dao.getAll().size());
  }
}