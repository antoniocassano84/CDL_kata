package cdlKata;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class BasketTest {

  private Basket basket;
  private Scanner sc;

  @BeforeEach
  void setUp() {
    basket = new Basket();
    System.out.println("Setup: New Basket object created");
    sc = new Scanner(System.in);
    System.out.println("Setup: New Scanner object created");
  }

  @AfterEach
  void tearDown() {
    sc.close();
    System.out.println("Scanner closed!");
  }

  @Test
  void BasketEmptyWhenCreated() {
    //assertEquals(0, basket.getSb().length());
  }

  @Test
  void readCharFromKeyboard() {
    System.out.println("Running test ...");
    fail("fail");
  }

  @Test
  void addToBasket() {
    System.out.println("Running test ...");
    fail("fail");
  }

  @Test
  void frequency() {
    System.out.println("Running test ...");
    fail("fail");
  }

  @Test
  void printMap() {
    System.out.println("Running test ...");
    fail("fail");
  }

  @Test
  void checkOut() {
    System.out.println("Running test ...");
    fail("fail");
  }
}