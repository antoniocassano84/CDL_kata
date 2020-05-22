package cdlKata.datasource;

import cdlKata.Item;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ItemDaoFile implements Dao<Item> {

  private static final String FILE_DATA_SOURCE = "src\\main\\resources\\itemsDB.csv";

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
