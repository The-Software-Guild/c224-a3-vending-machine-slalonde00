/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import dao.ItemPersistenceException;
import dto.Customer;
import dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author slalo
 */
public class ItemView {

    private final UserIO io;

    public ItemView(UserIO io) {
        this.io = io;
    }

    public int initialMenu(List<Item> itemList) throws ItemPersistenceException {

        io.print("Initial Menu");
        io.print("please select the operation you wish to perform");
        displayItemListMenu(itemList);
        io.print("0. Buy an item");
        io.print("1. List all item");
        io.print("2. List a item info");
        io.print("3. Exit");

        return io.readInt("Please Select one of the above option", 0, 3);
    }

    public int editItemSelectedMenu() throws ItemPersistenceException {
        io.print("Buy an item");
       int selectedItemIndex = io.readInt("Please enter the # of the item you wish to buy");
        System.out.println(selectedItemIndex);
        return selectedItemIndex;
    }

    public int editItemMenuHowMany() throws ItemPersistenceException {
        int id = io.readInt("How many do you wanna Buy ?");
        return id;
    }

    public List<Item> addItem(List<Item> someItem, Item newItem) {

        someItem.add(newItem);
        return someItem;
    }

    public int getFund() throws ItemPersistenceException {
        int fund = io.readInt("How much dollars do you got ?");
        if (fund < 0) {
            io.print("You don't have enough money to buy anything");
        } else {
            io.print("Good ! you have " + fund + " token");
        }
        return fund;
    }

    public void listItem(Item someItem) {
        String itemInfo = "Name : ";
        itemInfo += someItem.getName() + "\n";
        itemInfo += "Price : ";
        itemInfo += someItem.getPrice() + "\n";
        itemInfo += "In Stock : ";
        itemInfo += someItem.getInStock() + "\n";
        itemInfo += "Item id : ";
        itemInfo += someItem.getId() + "\n";
        io.print(itemInfo);
    }

    public void listAllItem(List<Item> someItem) {
        for (Item newItem : someItem) {
            io.print(newItem.getName());
            io.print("");
        }

    }

    public int getItemMenu() throws ItemPersistenceException {
        io.print("Find item");
        return io.readInt("Enter item's # to find the item");

    }

    public void displayErrorMessage(String errorMsg) {
        io.print("===ERROR ===");
        io.print(errorMsg);
    }

    public Item getNewItemInfo() throws ItemPersistenceException {
        String Name = io.readString("Please enter Item Name");
        Double Price = io.readDouble("Please enter Item Price");
        int InStock = io.readInt("Please enter Item InStock");

        Item currentItem = new Item(Name);
        currentItem.setName(Name);
        currentItem.setPrice(Price);
        currentItem.setInStock(InStock);
        return currentItem;
    }

    public UserIO getIo() {
        return io;
    }

    public void displayItemList(List<Item> itemList) {
        for (Item currentItem : itemList) {
            String itemInfo = String.format("#%s : %s %s",
                    currentItem.getName(),
                    currentItem.getPrice(),
                    currentItem.getInStock());
            io.print(itemInfo);
        }
    }

    public void displayItemListMenu(List<Item> itemList) {

        for (int i = 0; i < itemList.size(); i++) {
            String itemInfo = "Item " + i + "\n" + "Name : ";
            itemInfo += itemList.get(i).getName() + "\n";
            itemInfo += "Price : ";
            itemInfo += itemList.get(i).getPrice() + "\n";
            itemInfo += "In Stock : ";
            itemInfo += itemList.get(i).getInStock() + "\n";
            itemInfo += "Item id : ";
            itemInfo += itemList.get(i).getId() + "\n";
            io.print(itemInfo);
        }

    }
}
