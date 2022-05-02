/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.classroster;

import Services.ServiceLayer;
import controller.ClassRosterController;
import dao.ClassRosterAuditDao;
import dao.ClassRosterAuditDaoFileImpl;
import dao.ClassRosterDao;
import dao.ClassRosterPersistenceException;
import dao.ClassRosterDaoFileImpl;
import views.ClassRosterView;
import views.UserIO;
import views.UserIOConsoleImpl;

/**
 *
 * @author slalo
 */
public class ClassRoster {

   public static void main(String[] args) throws ClassRosterPersistenceException {
    // Instantiate the UserIO implementation
    UserIO myIo = new UserIOConsoleImpl();
    // Instantiate the View and wire the UserIO implementation into it
    ClassRosterView myView = new ClassRosterView(myIo);
    // Instantiate the DAO
    ClassRosterDao myDao = new ClassRosterDaoFileImpl();
    // Instantiate the Audit DAO
    ClassRosterAuditDao myAuditDao = new ClassRosterAuditDaoFileImpl();
    // Instantiate the Service Layer and wire the DAO and Audit DAO into it
    ServiceLayer myService = new ServiceLayer(myDao, myAuditDao);
    // Instantiate the Controller and wire the Service Layer into it
    ClassRosterController controller = new ClassRosterController(myView, myService);
    // Kick off the Controller
    controller.run();
}
}
