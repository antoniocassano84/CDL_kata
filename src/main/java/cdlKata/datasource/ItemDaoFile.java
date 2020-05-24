package cdlKata.datasource;

import cdlKata.Item;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * This class implements a File-specific version of the Dao<Item> interface.
 * it simply implements the methods declared in Dao<T>.
 */
public class ItemDaoFile implements Dao<Item> {

  private static final String FILE_DATA_SOURCE = "src\\main\\resources\\CdlItems.csv";

  /** It retrieves an optional Item from its unique name.
   * @param s This is the object's unique name (i.e. primary key).
   * @return Returns an optional object of type Item from the csv file.
   */
  @Override
  public Optional<Item> get(String s) {
    try (Scanner input = new Scanner(new File(FILE_DATA_SOURCE))) {
      input.nextLine(); // skip header
      while(input.hasNext()) {
        String line = input.nextLine();
        if(line.split(",")[0].equalsIgnoreCase(s)) {
          return Optional.of(Item.parseItem(line));
        }
      }
    } catch (IOException e) {
      return Optional.empty();
    }
    return Optional.empty();
  }

  /** This method implements the "Read" part of the CRUD operations.
   * @return All the objects in the csv file.
   */
  @Override
  public List<Item> getAll() {
    List<Item> items = new LinkedList<>();
    try (Scanner input = new Scanner(new File(FILE_DATA_SOURCE))) {
      input.nextLine(); // skip header
      while(input.hasNext())
        items.add(Item.parseItem(input.nextLine()));
    } catch (IOException e) {
      return null;
    }
    return items;
  }

  /** This method implements the "Create" part of the CRUD operations. it allows to add
   *  object to the csv file to be read after.
   * @param item This is the Item to be stored in the csv file for future reads.
   */
  @Override
  public void save(Item item) {

    // if item is in the system then return
    if(this.get(item.getName()).isPresent()) {
      System.out.println("Item '" + item.getName() + "' already in the system");
      return;
    }

    String itemS = "\n" + item.getName() + "," + item.getUnitPrice() + "," +
                   (item.getMinAmount()==0? "" : item.getMinAmount()) + "," +
                   (item.getSpecialPrice()==0? "" : item.getSpecialPrice());
    try (FileWriter pw = new FileWriter(FILE_DATA_SOURCE,true)) {
      pw.write(itemS);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /** This method implements the "Update" part of the CRUD operations.
   * @param item This is the Item to be updated in the csv file for future reads.
   * @param params This represents the field to be changed for the specific Item.
   */
  @Override
  public void update(Item item, String[] params) {

    if(params.length!=1 && params.length!=3) {
      System.out.println("Wrong number of parameters");
      return;
    }

    if(!this.get(item.getName()).isPresent()) {
      System.out.println("Item '" + item.getName() + "' NOT in the system");
      return;
    }

    String fileContents = null;

    try(Scanner sc = new Scanner(new File(FILE_DATA_SOURCE))) {
      StringBuilder buffer = new StringBuilder();
      while (sc.hasNextLine())
        buffer.append(sc.nextLine()).append(System.lineSeparator());
      fileContents = buffer.toString();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }

    if(fileContents != null)
      fileContents = fileContents.replaceAll(item.getName() + "," + item.getUnitPrice() + "," +
                                                    item.getMinAmount() + "," + item.getSpecialPrice(),
                                        item.getName() + "," + params[0] + "," +
                                                    params[1] + "," + params[2]);
    else
      return;

    try (FileWriter writer = new FileWriter(FILE_DATA_SOURCE)) {
      writer.append(fileContents);
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /** This method implements the "Delete" part of the CRUD operations.
   * @param item This is the item to be removed from the csv file.
   */
  @Override
  public void delete(Item item) {
    if(!this.get(item.getName()).isPresent()) {
      System.out.println("Item '" + item.getName() + "' NOT in the system");
      return;
    }

    String fileContents = null;
    try(Scanner sc = new Scanner(new File(FILE_DATA_SOURCE))) {
      StringBuilder buffer = new StringBuilder();
      while (sc.hasNextLine()) {
        String itLine = sc.nextLine();
        if(!itLine.split(",")[0].equalsIgnoreCase(item.getName()))
          buffer.append(itLine).append(System.lineSeparator());
      }
      fileContents = buffer.toString();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }

    try (FileWriter writer = new FileWriter(FILE_DATA_SOURCE)) {
      if(fileContents != null) {
        writer.append(fileContents.trim());
        writer.flush();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
