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
import dto.Student;
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

    public void run() throws ClassRosterPersistenceException {
        boolean keepgoing = true;
        int menuSelect = 0;
        while (keepgoing) {
            menuSelect = getMenuSelect();
            switch (menuSelect) {
                case 1:
                    createStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    editStudent();
                    break;
                case 4:
                    listStudent();
                    break;
                case 5:
                    getStudent();
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
        Student currentStudent = view.getNewStudentInfo();
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
    List<Student> studentList = services.getAllStudents();
    view.displayStudentList(studentList);
}

private void viewStudent() throws ClassRosterPersistenceException {
    
    String studentId = view.getStudentMenu();
    Student student = services.getStudent(studentId);
    view.listStudent(student);
}

private void removeStudent() throws ClassRosterPersistenceException {
    
    String studentId = view.getStudentMenu();
    services.removeStudent(studentId);
}

    private int getMenuSelect() throws ClassRosterPersistenceException {
    return view.initialMenu();
    }

    private void listStudent() throws ClassRosterPersistenceException {
        List<Student> someStudent = services.getAllStudents();
        view.listAllStudent(someStudent);
       
    }

    private void getStudent() throws ClassRosterPersistenceException {
        String name = view.getStudentMenu();
        Student foundStudent = services.getStudent(name);
        view.listStudent(foundStudent);
      }

    private void editStudent() throws ClassRosterPersistenceException {
        String name = view.editStudentMenu();
        Student studentToEdit = services.getStudent(name);
        Student updatedStudent = view.editStudent(studentToEdit);
        services.editStudent(name);
        view.listStudent(updatedStudent);
    }

    
    
}
