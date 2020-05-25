package cdlKata.datasource;

import cdlKata.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class implements a DB-specific version of the Dao<Item> interface.
 * it simply implements the methods declared in Dao<T>.
 */
public class ItemDaoDB implements Dao<Item> {

  private static final String CONNECTION_STRING = "jdbc:sqlite:src\\main\\resources\\CdlItems.db";
  private static final String QUERY_GET_ALL_ITEMS = "SELECT * FROM Items";
  private static final String QUERY_ITEM_BY_NAME = QUERY_GET_ALL_ITEMS + " WHERE Items.name='";


  /** It retrieves an optional Item from its unique name.
   * @param s This is the object's unique name (i.e. primary key).
   * @return Returns an optional object of type Item from the DB.
   */
  @Override
  public Optional<Item> get(String s) {
    Item item;
    String query = QUERY_ITEM_BY_NAME + s.toUpperCase() + "'";
    try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
         Statement statement = conn.createStatement();
         ResultSet results = statement.executeQuery(query)) {
      item = new Item(results.getString("name"),
                      results.getDouble("unitPrice"),
                      results.getInt("minAmount"),
                      results.getDouble("specialPrice")
                      );
      return Optional.of(item);
    } catch (SQLException e) {
      return Optional.empty();
    }
  }

  /** This method implements the "Read" part of the CRUD operations.
   * @return All the objects in the DB.
   */
  @Override
  public List<Item> getAll() {
    List<Item> items = new ArrayList<>();
    try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
         Statement statement = conn.createStatement();
         ResultSet results = statement.executeQuery(QUERY_GET_ALL_ITEMS)) {
      while(results.next())
        items.add(new Item(results.getString("name"),
                           results.getDouble("unitPrice"),
                           results.getInt("minAmount"),
                           results.getDouble("specialPrice")));
      return items;
    } catch (SQLException e) {
      return null;
    }
  }

  /** This method implements the "Create" part of the CRUD operations. it allows to add
   *  object to the DB to be read after.
   * @param item This is the Item to be stored in the DB for future reads.
   */
  @Override
  public void save(Item item) {
    Optional<Item> optItem = this.get(item.getName());
    if(!optItem.isPresent()) {
      StringBuilder sb = new StringBuilder();
      sb.append("INSERT INTO Items(name, unitPrice");
      if(item.getMinAmount()!=0)
        sb.append(", minAmount, specialPrice");
      sb.append(")VALUES('");
      sb.append(item.getName());
      sb.append("','");
      sb.append(item.getUnitPrice());
      sb.append("'");
      if(item.getMinAmount()!=0 && item.getSpecialPrice()!=0.00) {
        sb.append(",'");
        sb.append(item.getMinAmount());
        sb.append("','");
        sb.append(item.getSpecialPrice());
        sb.append("'");
      }
      sb.append(")");
      try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
           Statement statement = conn.createStatement()) {
        statement.executeUpdate(sb.toString());
      } catch (SQLException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  /** This method implements the "Update" part of the CRUD operations.
   * @param item This is the Item to be updated in the DB for future reads.
   * @param params This represents the field to be changed for the specific Item.
   */
  @Override
  public void update(Item item, String[] params) {

    if(params.length!=1 && params.length!=3) {
      System.out.println("Wrong number of parameters");
      return;
    }

    Optional<Item> opIt = this.get(item.getName());
    if(!opIt.isPresent()) {
      System.out.println("Item '" + item.getName() + "' not found");
      return;
    }

    String query = "REPLACE INTO Items(name, unitPrice, minAmount, specialPrice)\n" +
                   "VALUES('" + item.getName() + "', " + params[0] + ", " + params[1] + ", "+ params[2] +")";
    try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
         Statement statement = conn.createStatement()) {
      statement.execute(query);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  /** This method implements the "Delete" part of the CRUD operations.
   * @param item This is the item to be removed from the DB.
   */
  @Override
  public void delete(Item item) {
    String query = "DELETE FROM Items WHERE name='" + item.getName() + "'";
    try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
         Statement statement = conn.createStatement()) {
      statement.execute(query);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

}
