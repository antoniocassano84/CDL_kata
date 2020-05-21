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
    for(Item i : this.getAll())
      if(i.equals(item))
        return;
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
    try {
      // input the (modified) file content to the StringBuffer "input"
      BufferedReader file = new BufferedReader(new FileReader(FILE_DATA_SOURCE));
      StringBuffer inputBuffer = new StringBuffer();
      String line;

      while ((line = file.readLine()) != null) {
        //line = ... // replace the line here
        inputBuffer.append(line);
        inputBuffer.append('\n');
      }
      file.close();

      // write the new string with the replaced line OVER the same file
      FileOutputStream fileOut = new FileOutputStream(FILE_DATA_SOURCE);
      fileOut.write(inputBuffer.toString().getBytes());
      fileOut.close();

    } catch (Exception e) {
      System.out.println("Problem reading file.");
    }
  }

  @Override
  public void delete(Item item) {

  }
}
