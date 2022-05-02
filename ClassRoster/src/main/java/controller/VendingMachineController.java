/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Services.ItemDataValidationException;
import Services.ItemDuplicateIdException;
import Services.ServiceLayer;
import dao.ItemPersistenceException;
import views.ClassRosterView;
import dto.Item;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.List;
import dao.ItemDao;

/**
 *
 * @author slalo
 */
public class VendingMachineController {

    private ServiceLayer services;
    ClassRosterView view;

    ;

    public VendingMachineController(ClassRosterView view, ServiceLayer services) {

        this.view = view;
        this.services = services;
    }

    public void run() throws ItemPersistenceException, FileNotFoundException {
        boolean keepgoing = true;
        int menuSelect = 0;
        int i = 0;
        while (keepgoing) {

            menuSelect = getMenuSelect(services.getAllItems(), i);
            switch (menuSelect) {
                case 0:
                    editItem();
                    break;
                case 1:
                    listItem();
                    break;
                case 2:
                    getItem();
                    break;
                case 3:
                    keepgoing = false;
                    break;
            }

        }
    }

    public ServiceLayer getServices() {
        return services;
    }

    public void setServices(ServiceLayer services) {
        this.services = services;
    }

    public ClassRosterView getView() {
        return view;
    }

    public void setView(ClassRosterView view) {
        this.view = view;
    }


    private void listItems() throws ItemPersistenceException {
        List<Item> itemList = services.getAllItems();
        view.displayItemList(itemList);
    }

    private void viewItem() throws ItemPersistenceException {

        String itemId = view.getItemMenu();
        Item item = services.getItem(Integer.parseInt(itemId));
        view.listItem(item);
    }

    private int getMenuSelect(List<Item> itemList, int i) throws ItemPersistenceException {

        return view.initialMenu(itemList);
    }

    private void listItem() throws ItemPersistenceException {
        List<Item> someItem = services.updateAllItems();
        view.listAllItem(someItem);

    }

    private void getItem() throws ItemPersistenceException {
        String name = view.getItemMenu();
        Item foundItem = services.getItem(Integer.parseInt(name));
        view.listItem(foundItem);
    }

    private void editItem() throws ItemPersistenceException, FileNotFoundException {
        String name = view.editItemMenu();
        Item itemToEdit = services.getItem(Integer.parseInt(name));
        Item updatedItem = view.editItem(itemToEdit);
        services.editItem(Integer.parseInt(name));
        view.listItem(updatedItem);
    }

}
