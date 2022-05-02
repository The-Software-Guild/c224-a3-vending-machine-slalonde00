/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import dao.ClassRosterPersistenceException;
import dto.Student;
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

    public int initialMenu(List<Student> studentList) throws ClassRosterPersistenceException {

        io.print("Initial Menu");
        io.print("please select the operation you wish to perform");
        displayStudentListMenu(studentList);
        io.print("0. Buy an item");
        io.print("1. List all item");
        io.print("2. List a item info");
        io.print("3. Exit");
        return io.readInt("Please Select one of the above option", 0, 3);
    }

    public Student addStudentMenu() throws ClassRosterPersistenceException {
        io.print("Add student");
        String grade = io.readString("Please enter grade");
        String name = io.readString("Please enter name");
        int id = io.readInt("Please enter id");
        Student newStudent = new Student(grade, name, id);
        return newStudent;
    }

    public String deleteStudentMenu() {
        io.print("Delete student");
        String name = io.readString("Please enter the name of the student you wish to delete");
        return name;
    }

    public String editStudentMenu() {
        io.print("Buy an item");
        
        return io.readString("Please enter the name of the item you wish to buy");
    }

    public Student editStudent(Student someStudent) {
        String name = (someStudent.getName());
        if (!name.equals("")) {
            someStudent.setName(name);
        }

        String grade = (someStudent.getGrade());
        if (!grade.equals("")) {
            someStudent.setGrade(grade);
        }

        String id = io.readString("How many do you wanna Buy ?");
        if (!id.equals("")) {
            someStudent.setId((someStudent.getId()) - Integer.parseInt(id));
        }

        return someStudent;
    }

    public void listStudent(Student someStudent) {
        io.print("Name : " + someStudent.getName());
        io.print("Grade : " + someStudent.getGrade());
        io.print("id : " + someStudent.getId());

    }

    public void listAllStudent(List<Student> someStudent) {
        for (Student newStudent : someStudent) {
            io.print(newStudent.getName());
            io.print("");
        }

    }

    public String getStudentMenu() {
        io.print("Find student");
        return io.readString("Enter student's name to find the student");

    }

    public void displayErrorMessage(String errorMsg) {
        io.print("===ERROR ===");
        io.print(errorMsg);
    }

    public Student getNewStudentInfo() throws ClassRosterPersistenceException {
        String Name = io.readString("Please enter Student Name");
        String Grade = io.readString("Please enter Student Grade");
        int Id = io.readInt("Please enter Student Id");

        Student currentStudent = new Student(Name);
        currentStudent.setName(Name);
        currentStudent.setGrade(Grade);
        currentStudent.setId(Id);
        return currentStudent;
    }

    public UserIO getIo() {
        return io;
    }

    public void displayStudentList(List<Student> studentList) {
        for (Student currentStudent : studentList) {
            String studentInfo = String.format("#%s : %s %s",
                    currentStudent.getName(),
                    currentStudent.getGrade(),
                    currentStudent.getId());
            io.print(studentInfo);
        }
        io.readString("Please hit enter to continue.");
    }
    
    
    public void displayStudentListMenu(List<Student> studentList) {
        for (int i =0; i<studentList.size(); i++) {
            String studentInfo = "Item " + i + "\n" + "Name : ";
             studentInfo+=studentList.get(i).getName()+"\n";
             studentInfo+="Price : ";
             studentInfo+=studentList.get(i).getGrade() +"\n";
             studentInfo+= "In Stock : ";
             studentInfo+= studentList.get(i).getId() + "\n";
            io.print(studentInfo);
        }
        io.readString("Please hit enter to continue.");
    }
}
