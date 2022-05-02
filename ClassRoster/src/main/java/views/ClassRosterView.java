/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import dao.ClassRosterPersistenceException;
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

    public int initialMenu(List<Item> studentList) throws ClassRosterPersistenceException {

        io.print("Initial Menu");
        io.print("please select the operation you wish to perform");
        displayItemListMenu(studentList);
        io.print("0. Buy an item");
        io.print("1. List all item");
        io.print("2. List a item info");
        io.print("3. Exit");
        return io.readInt("Please Select one of the above option", 0, 3);
    }

    public Item addItemMenu() throws ClassRosterPersistenceException {
        io.print("Add student");
        String grade = io.readString("Please enter grade");
        BigDecimal price = io.readBigDecimal("Please enter name");
        int id = io.readInt("Please enter id");
        Item newItem = new Item(grade, price, id);
        return newItem;
    }

    public String deleteItemMenu() {
        io.print("Delete student");
        String name = io.readString("Please enter the name of the student you wish to delete");
        return name;
    }

    public String editItemMenu() {
        io.print("Buy an item");
        
        return io.readString("Please enter the name of the item you wish to buy");
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
        io.print("Grade : " + someItem.getPrice());
        io.print("id : " + someItem.getInStock());

    }

    public void listAllItem(List<Item> someItem) {
        for (Item newItem : someItem) {
            io.print(newItem.getName());
            io.print("");
        }

    }

    public String getItemMenu() {
        io.print("Find student");
        return io.readString("Enter student's name to find the student");

    }

    public void displayErrorMessage(String errorMsg) {
        io.print("===ERROR ===");
        io.print(errorMsg);
    }

    public Item getNewItemInfo() throws ClassRosterPersistenceException {
        String Name = io.readString("Please enter Item Name");
        BigDecimal Grade = io.readBigDecimal("Please enter Item Grade");
        int Id = io.readInt("Please enter Item Id");

        Item currentItem = new Item(Name);
        currentItem.setName(Name);
        currentItem.setPrice(Grade);
        currentItem.setInStock(Id);
        return currentItem;
    }

    public UserIO getIo() {
        return io;
    }

    public void displayItemList(List<Item> studentList) {
        for (Item currentItem : studentList) {
            String studentInfo = String.format("#%s : %s %s",
                    currentItem.getName(),
                    currentItem.getPrice(),
                    currentItem.getInStock());
            io.print(studentInfo);
        }
        io.readString("Please hit enter to continue.");
    }
    
    
    public void displayItemListMenu(List<Item> studentList) {
       
        for (int i =0; i<studentList.size(); i++) {
            String studentInfo = "Item " + i + "\n" + "Name : ";
             studentInfo+=studentList.get(i).getName()+"\n";
             studentInfo+="Price : ";
             studentInfo+=studentList.get(i).getPrice() +"\n";
             studentInfo+= "In Stock : ";
             studentInfo+= studentList.get(i).getInStock() + "\n";
            io.print(studentInfo);
        }
        io.readString("Please hit enter to continue.");
    }
}
