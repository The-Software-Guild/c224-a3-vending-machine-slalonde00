/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Item;
import java.util.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author slalo
 */
public class ItemDaoFileImpl implements ItemDao {

    private final String itemFile;
    public static final String delemiter = "::";
    private List<Item> someItem = new ArrayList<Item>();

    public ItemDaoFileImpl() {
        itemFile = "items.txt";
    }

    public ItemDaoFileImpl(String itemFile) {
        this.itemFile = itemFile;
    }

    @Override
    public Item editItem(Item editedItem) throws ItemPersistenceException {

        someItem.remove(editedItem);
        someItem.add(editedItem);
        writeItem();
        return editedItem;
    }

    @Override
    public List<Item> getAllItem() throws ItemPersistenceException {

        return someItem;
    }

    public List<Item> updateAllItem() throws ItemPersistenceException {
        
        return someItem;
    }

    @Override
    public Item findItem(int address) throws ItemPersistenceException {
        return someItem.get(address);
    }

    private void writeItem()
            throws ItemPersistenceException {

        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(itemFile));
            System.out.println("Writing items infos to file : ");

            List<Item> itemList = updateAllItem();

            System.out.println(updateAllItem());

            String itemAsText;

            System.out.println(itemList);

            for (Item item : itemList) {

                itemAsText = marshallItems(item);

                out.println(itemAsText);

                out.flush();

            }

            out.close();

        } catch (IOException e) {
            throw new ItemPersistenceException("Could not save data to file", e);
        }

    }

    public void loadItem() throws ItemPersistenceException, FileNotFoundException {
        Scanner s;
        try {
            s = new Scanner(new BufferedReader(new FileReader(itemFile)));
        } catch (FileNotFoundException e) {
            throw new ItemPersistenceException("Could not load the item into the file", e);
        }

        while (s.hasNextLine()) {
            String currentLine = s.nextLine();
            System.out.println(currentLine);
            Item currentItem = unMarshallItems(currentLine);
            someItem.add(currentItem);
        }
        s.close();

    }

    private String marshallItems(Item someItem) {
        String itemAsString = someItem.getName() + delemiter;
        itemAsString += someItem.getPrice() + delemiter;
        itemAsString += someItem.getInStock() + delemiter;
        itemAsString += someItem.getId() + delemiter;
        return itemAsString;
    }

    private Item unMarshallItems(String itemAsText) throws FileNotFoundException, ItemPersistenceException {

        String[] itemInfo = itemAsText.split(delemiter);
        String name = itemInfo[0];
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(itemInfo[1]));
        String inStock = (itemInfo[2]);
        String Id = (itemInfo[3]);
        Item newItem = new Item(name, price, Integer.parseInt(inStock), Integer.parseInt(Id));
        return newItem;

    }

    public List<Item> getSomeItem() {
        return someItem;
    }

    public void setSomeItem(List<Item> someItem) {
        this.someItem = someItem;
    }

}
