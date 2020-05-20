package cdlKata;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ItemDaoDB implements Dao<Item> {

  private static final String CONNECTION_STRING = "jdbc:sqlite:src\\main\\resources\\CdlItems.db";
  public static final String QUERY_ITEM_BY_NAME = "SELECT * FROM Items WHERE Items.name='";
  public static final String QUERY_GET_ALL_ITEMS = "SELECT * FROM Items";


  @Override
  public Optional<Item> get(String s) {
    Item item;
    String query = QUERY_ITEM_BY_NAME + s + "'";
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

  @Override
  public List<Item> getAll() {
    List<Item> items = new LinkedList<>();
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

  @Override
  public void save(Item item) {
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

  @Override
  public void update(Item item, String[] params) {
    String query = "REPLACE INTO Items(name, unitPrice, minAmount, specialPrice)\n" +
                   "VALUES('" + params[0] + "', " + params[1] + ", " + params[2] + ", "+ params[3] +")";
    try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
         Statement statement = conn.createStatement()) {
      statement.execute(query);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

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
