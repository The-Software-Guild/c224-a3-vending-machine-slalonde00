/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Services.ClassRosterDataValidationException;
import Services.ClassRosterDuplicateIdException;
import Services.ServiceLayer;
import dao.ClassRosterDao;
import dao.ClassRosterPersistenceException;
import views.ClassRosterView;
import dto.Item;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author slalo
 */
public class ClassRosterController {

    private ServiceLayer services;
    ClassRosterView view;

    ;

    public ClassRosterController(ClassRosterView view, ServiceLayer services) {

        this.view = view;
        this.services = services;
    }

    public void run() throws ClassRosterPersistenceException, FileNotFoundException {
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

    private void createItem() throws ClassRosterPersistenceException {

        boolean hasErrors = false;
        do {
            Item currentItem = view.getNewItemInfo();
            try {
                services.createItem(currentItem);

                hasErrors = false;
            } catch (ClassRosterDuplicateIdException | ClassRosterDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }

    private void listItems() throws ClassRosterPersistenceException {
        List<Item> studentList = services.getMap();
        view.displayItemList(studentList);
    }

    private void viewItem() throws ClassRosterPersistenceException {

        String studentId = view.getItemMenu();
        Item student = services.getItem(studentId);
        view.listItem(student);
    }

    private void removeItem() throws ClassRosterPersistenceException {

        String studentId = view.getItemMenu();
        services.removeItem(studentId);
    }

    private int getMenuSelect(List<Item> studentList, int i) throws ClassRosterPersistenceException {

        return view.initialMenu(studentList);
    }

    private void listItem() throws ClassRosterPersistenceException {
        List<Item> someItem = services.updateAllItems();
        view.listAllItem(someItem);

    }

    private void getItem() throws ClassRosterPersistenceException {
        String name = view.getItemMenu();
        Item foundItem = services.getItem(name);
        view.listItem(foundItem);
    }

    private void editItem() throws ClassRosterPersistenceException, FileNotFoundException {
        String name = view.editItemMenu();
        Item studentToEdit = services.getItem(name);
        Item updatedItem = view.editItem(studentToEdit);
        services.editItem(name);
        view.listItem(updatedItem);
    }

}
