/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import dao.ClassRosterPersistenceException;
import dto.Item;
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

    public int initialMenu() throws ClassRosterPersistenceException {

        io.print("Initial Menu");
        io.print("please select the operation you wish to perform");
        io.print("1. Add student");
        io.print("2. Delete atudent");
        io.print("3. Edit student");
        io.print("4. List all student");
        io.print("5. List a student info");
        io.print("6. Exit");
        return io.readInt("Please Select one of the above option", 1, 6);
    }

    public Item addStudentMenu() {
        io.print("Add student");
        String grade = io.readString("Please enter grade");
        String name = io.readString("Please enter name");
        String id = io.readString("Please enter id");
        Item newStudent = new Item(grade, name, id);
        return newStudent;
    }

    public String deleteStudentMenu() {
        io.print("Delete student");
        String name = io.readString("Please enter the name of the student you wish to delete");
        return name;
    }

    public String editStudentMenu() {
        io.print("Edit student");
        return io.readString("Please enter the name of the student you wish to edit");
    }

    public Item editStudent(Item someStudent) {
        String name = io.readString("Enter a name");
        if (!name.equals("")) {
            someStudent.setName(name);
        }

        String grade = io.readString("Enter a grade");
        if (!grade.equals("")) {
            someStudent.setGrade(grade);
        }

        String id = io.readString("Enter an id");
        if (!id.equals("")) {
            someStudent.setId(id);
        }

        return someStudent;
    }

    public void listStudent(Item someStudent) {
        io.print("Name : " + someStudent.getName());
        io.print("Grade : " + someStudent.getGrade());
        io.print("id : " + someStudent.getId());

    }

    public void listAllStudent(List<Item> someStudent) {
        for (Item newStudent : someStudent) {
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

    public Item getNewStudentInfo() {
        String Name = io.readString("Please enter Student Name");
        String Grade = io.readString("Please enter Student Grade");
        String Id = io.readString("Please enter Student Id");

        Item currentStudent = new Item(Name);
        currentStudent.setName(Name);
        currentStudent.setGrade(Grade);
        currentStudent.setId(Id);
        return currentStudent;
    }

    public UserIO getIo() {
        return io;
    }

    public void displayStudentList(List<Item> studentList) {
        for (Item currentStudent : studentList) {
            String studentInfo = String.format("#%s : %s %s",
                    currentStudent.getName(),
                    currentStudent.getGrade(),
                    currentStudent.getId());
            io.print(studentInfo);
        }
        io.readString("Please hit enter to continue.");
    }
}
