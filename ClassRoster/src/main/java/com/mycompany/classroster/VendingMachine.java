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
import views.ClassRosterView;
import views.UserIO;
import views.UserIOConsoleImpl;
import dao.ItemAuditDao;
import dao.ItemDao;

/**
 *
 * @author slalo
 */
public class VendingMachine {

   public static void main(String[] args) throws ItemPersistenceException, FileNotFoundException {
    // Instantiate the UserIO implementation
    UserIO myIo = new UserIOConsoleImpl();
    // Instantiate the View and wire the UserIO implementation into it
    ClassRosterView myView = new ClassRosterView(myIo);
    // Instantiate the DAO
    ItemDao myDao = new ItemDaoFileImpl();
    // Instantiate the Audit DAO
    ItemAuditDao myAuditDao = new ItemAuditDaoFileImpl();
    // Instantiate the Service Layer and wire the DAO and Audit DAO into it
    ServiceLayer myService = new ServiceLayer(myDao, myAuditDao);
    // Instantiate the Controller and wire the Service Layer into it
    VendingMachineController controller = new VendingMachineController(myView, myService);
    // Kick off the Controller
    controller.run();
}
}
