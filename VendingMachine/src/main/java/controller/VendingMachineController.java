/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Services.ServiceLayer;
import dao.ItemPersistenceException;
import dto.Customer;
import views.ItemView;
import dto.Item;
import java.io.FileNotFoundException;
import java.util.List;

/**
 *
 * @author slalo
 */
public class VendingMachineController {

    private ServiceLayer services;
    ItemView view;
    Customer customer;

    ;

    public VendingMachineController(ItemView view, ServiceLayer services, Customer customer) {

        this.view = view;
        this.services = services;
        this.customer = customer;
    }

    public void run() throws ItemPersistenceException, FileNotFoundException {

        boolean keepgoing = true;
        int menuSelect = 0;
        int i = 0;
        services.loadItem();
        while (keepgoing) {

            menuSelect = getMenuSelect(updateAllItems(), i);
            switch (menuSelect) {
                case 0:
                    editItem();
                    break;
                case 1:
                    listItems();
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

    public List<Item> updateAllItems() throws ItemPersistenceException {
        return services.getAllItems();
    }

    public void setServices(ServiceLayer services) {
        this.services = services;
    }

    public ItemView getView() {
        return view;
    }

    public void setView(ItemView view) {
        this.view = view;
    }

    private void listItems() throws ItemPersistenceException, FileNotFoundException {
        List<Item> itemList = services.getAllItems();
        view.displayItemList(itemList);
    }

    private void viewItem() throws ItemPersistenceException, FileNotFoundException {

        int itemId = view.getItemMenu();
        Item item = services.getItem(itemId);
        view.listItem(item);
    }

    private int getMenuSelect(List<Item> itemList, int i) throws ItemPersistenceException {

        return view.initialMenu(itemList);
    }

    private void getItem() throws ItemPersistenceException, FileNotFoundException {
        int name = view.getItemMenu();
        Item foundItem = services.getItem(name);
        view.listItem(foundItem);
    }

    public void editItem() throws ItemPersistenceException, FileNotFoundException {
        
        int fund = view.getFund();
        int selectedItem = view.editItemSelectedMenu();
        Item itemToEdit = services.getItem((selectedItem));
        System.out.println(itemToEdit);
        int howMany = (view.editItemMenuHowMany());
        services.editItem(itemToEdit, selectedItem, howMany);     
        services.editCustomer(customer, selectedItem);
        view.listItem(itemToEdit);
    }

}
