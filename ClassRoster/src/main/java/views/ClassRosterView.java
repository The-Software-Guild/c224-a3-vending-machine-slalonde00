/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import dao.ItemPersistenceException;
import dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author slalo
 */
public class ClassRosterView {

    private final UserIO io;

    public ClassRosterView(UserIO io) {
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

    public String editItemMenu() {
        io.print("Buy an item");
        
        return io.readString("Please enter the # of the item you wish to buy");
    }

    public Item editItem(Item someItem) {
        
        String id = io.readString("How many do you wanna Buy ?");
        if (!id.equals("")) {
            someItem.setInStock((someItem.getInStock()) - Integer.parseInt(id));
        }

        return someItem;
    }

    public void listItem(Item someItem) {
        io.print("Name : " + someItem.getName());
        io.print("Price : " + someItem.getPrice());
        io.print("id : " + someItem.getInStock());

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
        BigDecimal Price = io.readBigDecimal("Please enter Item Price");
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
       
        for (int i =0; i<itemList.size(); i++) {
            String itemInfo = "Item " + i + "\n" + "Name : ";
             itemInfo+=itemList.get(i).getName()+"\n";
             itemInfo+="Price : ";
             itemInfo+=itemList.get(i).getPrice() +"\n";
             itemInfo+= "In Stock : ";
             itemInfo+= itemList.get(i).getInStock() + "\n";
            io.print(itemInfo);
        }

    }
}
