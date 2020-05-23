package cdlKata.datasource;

import java.util.List;
import java.util.Optional;

/** This interface uses the Data Access Object (DAO) pattern as a structural pattern that
 *  allows to isolate the application/business layer from the persistence layer using an abstract API.
 *  In this project, we will use the Dao interface for the Item class with two different
 *  persistence mechanisms: (1) a relational database and (2) a csv text file.
 *  The functionality of this API is to hide from the application all the complexities involved
 *  in performing CRUD (i.e. Create, Read, Update, Delete) operations in the underlying storage mechanism.
 *  This permits both layers (i.e. application/business and persistence layers) to evolve
 *  separately without knowing anything about each other.
 * @param <T> This is the domain model. As this interface is generic, it could be applied to any abject.
 */
public interface Dao<T> {

  /**
   * This method implements the "Read" part of the CRUD operations.
   * @param s This is the object's unique name (i.e. primary key).
   * @return Returns an optional type to make it easy to check for null values returned.
   */
  Optional<T> get(String s);

  /** This method implements the "Read" part of the CRUD operations.
   * @return All the objects in the system.
   */
  List<T> getAll();

  /** This method implements the "Create" part of the CRUD operations. it allows to add
   *  object to the persistence system to be read after.
   * @param t This is the object to be stored in the system for future reads.
   */
  void save(T t);

  /** This method implements the "Update" part of the CRUD operations.
   * @param t This is the object to be updated in the system for future reads.
   * @param params This represents the field to be changed for the specific object t.
   */
  void update(T t, String[] params);

  /** This method implements the "Delete" part of the CRUD operations.
   * @param t This is the object to be removed from the system.
   */
  void delete(T t);
}
