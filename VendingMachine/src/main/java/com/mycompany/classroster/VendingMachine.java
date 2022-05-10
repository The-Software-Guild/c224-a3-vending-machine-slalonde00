/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.classroster;

import Services.ServiceLayer;
import controller.VendingMachineController;
import dao.ItemAuditDaoFileImpl;
import dao.ItemPersistenceException;
import dao.ItemDaoFileImpl;
import java.io.FileNotFoundException;
import views.ItemView;
import views.UserIO;
import views.UserIOConsoleImpl;
import dao.ItemAuditDao;
import dao.ItemDao;
import dto.Customer;

/**
 *
 * @author slalo
 */
public class VendingMachine {

    public static void main(String[] args) throws ItemPersistenceException, FileNotFoundException {
        // Instantiate the UserIO implementation
        UserIO myIo = new UserIOConsoleImpl();
        // Instantiate the View and wire the UserIO implementation into it
        ItemView myView = new ItemView(myIo);
        // Instantiate the DAO
        ItemDao myDao = new ItemDaoFileImpl();
        // Instantiate the Audit DAO
        ItemAuditDao myAuditDao = new ItemAuditDaoFileImpl();
        // Instantiate the Service Layer and wire the DAO and Audit DAO into it
        ServiceLayer myService = new ServiceLayer(myDao, myAuditDao);
        // Instantiate the Controller and wire the Service Layer into it
        Customer customer = new Customer(100, 100);
        VendingMachineController controller = new VendingMachineController(myView, myService, customer);
        // Kick off the Controller
        controller.run();
    }
}
