package cdlKata.datasource;

import cdlKata.Item;

import java.util.List;
import java.util.Optional;

public class ItemDaoFile implements Dao<Item> {

  @Override
  public Optional<Item> get(String s) {
    return Optional.empty();
  }

  @Override
  public List<Item> getAll() {
    return null;
  }

  @Override
  public void save(Item item) {

  }

  @Override
  public void update(Item item, String[] params) {

  }

  @Override
  public void delete(Item item) {

  }
}
