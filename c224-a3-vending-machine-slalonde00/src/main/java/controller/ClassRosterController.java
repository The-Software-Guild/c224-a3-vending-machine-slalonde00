/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Services.ClassRosterDataValidationException;
import Services.ClassRosterDuplicateIdException;
import Services.ServiceLayer;
import dao.ClassRosterPersistenceException;
import views.ClassRosterView;
import dto.Item;
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
        this.services=services;
    }

    public void run() throws ClassRosterPersistenceException, Exception {
        boolean keepgoing = true;
        int menuSelect = 0;
        while (keepgoing) {
            menuSelect = getMenuSelect();
            switch (menuSelect) {
                case 1:
                    editStudent();
                    break;
                case 2:
                    editStudent();
                    break;
                case 3:
                    editStudent();
                    break;
                case 6:
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
    
private void createStudent() throws ClassRosterPersistenceException {
   
    boolean hasErrors = false;
    do {
        Item currentStudent = view.getNewStudentInfo();
        try {
            services.createStudent(currentStudent);
      
            hasErrors = false;
        } catch (ClassRosterDuplicateIdException | ClassRosterDataValidationException e) {
            hasErrors = true;
            view.displayErrorMessage(e.getMessage());
        }
    } while (hasErrors);
}

private void listStudents() throws ClassRosterPersistenceException {
    List<Item> studentList = services.getAllStudents();
    view.displayStudentList(studentList);
}

private void viewStudent() throws ClassRosterPersistenceException {
    
    String studentId = view.getStudentMenu();
    Item student = services.getStudent((studentId));
    view.listStudent(student);
}

private void removeStudent() throws ClassRosterPersistenceException {
    
    String studentId = view.getStudentMenu();
    services.removeStudent(studentId);
}

    private int getMenuSelect() throws ClassRosterPersistenceException, Exception {
    return view.initialMenu();
    }

    private void listStudent() throws ClassRosterPersistenceException {
        List<Item> someStudent = services.getAllStudents();
        view.listAllStudent(someStudent);
       
    }

    private void getStudent() throws ClassRosterPersistenceException {
        String name = view.getStudentMenu();
        Item foundStudent = services.getStudent((name));
        view.listStudent(foundStudent);
      }

    private void editStudent() throws ClassRosterPersistenceException {
        int name = view.editStudentMenu();
        Item studentToEdit = services.getStudent(name);
        Item updatedStudent = view.editStudent(studentToEdit);
        services.editStudent(name);
        view.listStudent(updatedStudent);
    }

    
    
}
