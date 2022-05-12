/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Customer;
import dto.Item;
import java.util.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author slalo
 */
public class ItemDaoFileImpl implements ItemDao {

    private final String itemFile;
    public static final String delemiter = "::";
    private List<Item> someItem = new ArrayList<Item>();
    private Customer token = new Customer(100.00);

    public ItemDaoFileImpl() {
        itemFile = "items.txt";
    }

    public ItemDaoFileImpl(String itemFile) {
        this.itemFile = itemFile;
    }

    public List<Item> addItem(Item newItem) {
        someItem.add(newItem);
        return someItem;
    }

    @Override
    public Item editItem(Item editedItem, int howMany) throws ItemPersistenceException {
        editedItem.setInStock(editedItem.getInStock() - howMany);
        writeItem();
        return editedItem;
    }

    @Override
    public Customer editCustomer(Item boughtItem) {
        double money = token.getTotal();
        double total = (money - boughtItem.getPrice());
        token.setTotal(total);
        return token;
    }

    @Override
    public Customer getCustomer() {
        return token;
    }

    @Override
    public List<Item> getAllItem() throws ItemPersistenceException {
        IntStream.range(0, someItem.size())
                .filter(i -> someItem.get(i).getId() <= i)
                .mapToObj(i -> someItem.get(i))
                .collect(Collectors.toList());

        return someItem;
    }

    @Override
    public List<Item> updateAllItem() throws ItemPersistenceException {
        IntStream.range(0, someItem.size())
                .filter(i -> someItem.get(i).getId() <= i)
                .mapToObj(i -> someItem.get(i))
                .collect(Collectors.toList());

        return someItem;
    }

    @Override
    public Item findItem(int address) throws ItemPersistenceException {

        return someItem.get(address);
    }

    @Override
    public int findAndReturnItemId(int address) {
        for (int i = 0; i < someItem.size(); i++) {
            someItem.get(i).setId(i);
        }
        return someItem.get(address).getId();
    }

    private void writeItem()
            throws ItemPersistenceException {

        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(itemFile));
            System.out.println("Writing items infos to file : ");

            List<Item> itemList = updateAllItem();
            String itemAsText;
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

    @Override
    public List<Item> loadItem() throws ItemPersistenceException, FileNotFoundException {
        Scanner s;
        try {
            s = new Scanner(new BufferedReader(new FileReader(itemFile)));
        } catch (FileNotFoundException e) {
            throw new ItemPersistenceException("Could not load the item into the file", e);
        }
        while (s.hasNextLine()) {
            String currentLine = s.nextLine();

            Item currentItem = unMarshallItems(currentLine);
            someItem.add(currentItem);
        }
        s.close();
        return someItem;
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
        double price = (Double.parseDouble(itemInfo[1]));
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
