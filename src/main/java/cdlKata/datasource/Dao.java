package cdlKata.datasource;

import java.util.List;
import java.util.Optional;

/**
 * @param <T>
 */
public interface Dao<T> {

  Optional<T> get(String s);

  List<T> getAll();

  void save(T t);

  void update(T t, String[] params);

  void delete(T t);
}
