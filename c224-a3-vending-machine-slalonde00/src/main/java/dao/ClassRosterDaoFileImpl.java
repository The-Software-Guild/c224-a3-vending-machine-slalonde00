/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Item;
import java.util.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author slalo
 */
public class ClassRosterDaoFileImpl implements ClassRosterDao {

    private final String studentFile;
    public static final String delemiter = "::";
    private List<Item> someStudent = new ArrayList<Item>();

    public ClassRosterDaoFileImpl() {
        studentFile = "students.txt";
    }

    public ClassRosterDaoFileImpl(String rosterTextFile) {
        this.studentFile = rosterTextFile;
    }

    @Override
    public Item addStudent(Item newStudent, String name) throws ClassRosterPersistenceException {

        try {
            loadStudent();
        } catch (FileNotFoundException ex) {
        } catch (Exception ex) {

        }
        someStudent.add(newStudent);
        writeStudent();
        return newStudent;
    }

    @Override
    public Item removeStudent(String id) throws ClassRosterPersistenceException {
        try {
            loadStudent();
        } catch (FileNotFoundException ex) {
        } catch (Exception ex) {
        }

        Item removedStudent = someStudent.get(Integer.parseInt(id));
        someStudent.remove(id);
        writeStudent();
        return removedStudent;
    }

    @Override
    public Item editStudent(Item oldStudent, int id) throws ClassRosterPersistenceException {
        try {
            loadStudent();
        } catch (FileNotFoundException ex) {
        } catch (Exception ex) {
        }
        someStudent.remove(oldStudent);
        Item newStudent = new Item(someStudent.get(id).getName(), someStudent.get(id).getCost(), someStudent.get(id).getInStock());
        someStudent.add(newStudent);
        writeStudent();
        return newStudent;

    }

    @Override
    public List<Item> getAllStudent() throws ClassRosterPersistenceException {
        try {
            loadStudent();
        } catch (FileNotFoundException ex) {

        } catch (Exception ex) {
        }
        return new ArrayList(someStudent);
    }

    @Override
    public Item findStudent(int address) throws ClassRosterPersistenceException {
        try {
            loadStudent();
        } catch (FileNotFoundException ex) {
        } catch (Exception ex) {

        }
        return someStudent.get(address);
    }

    public void writeStudent()
            throws ClassRosterPersistenceException {

        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter("students.txt", true));
            System.out.println("Writing students infos to file : ");
            List<Item> studentList = this.getAllStudent();
            String studentAsText;

            for (Item student : studentList) {
                studentAsText = marshallStudents(student);

                out.println(studentAsText);
                out.flush();

            }

            out.close();

        } catch (IOException e) {
            throw new ClassRosterPersistenceException("Could not save data to file", e);
        }

    }

    public void loadStudent() throws ClassRosterPersistenceException, FileNotFoundException, Exception {
        Scanner s;
        try {
            s = new Scanner(new BufferedReader(new FileReader(studentFile)));
        } catch (FileNotFoundException e) {
            throw new ClassRosterPersistenceException("Could not load the student into the file", e);
        }

        while (s.hasNextLine()) {
            String currentLine = s.nextLine();
            Item currentStudent = unMarshallStudents(currentLine);
            someStudent.add(currentStudent);
        }
        s.close();

    }

    private String marshallStudents(Item someStudent) {
        String studentAsString = someStudent.getCost() + delemiter;
        studentAsString += someStudent.getName() + "::";
        studentAsString += someStudent.getInStock() + "::";
        return studentAsString;
    }

    private Item unMarshallStudents(String studentAsText) throws FileNotFoundException {
        String[] studentInfo = studentAsText.split(delemiter);
        String name = studentInfo[0];
        String grade = studentInfo[1];
        String id = studentInfo[2];

        Item newStudent = new Item(name, BigDecimal.valueOf(Double.parseDouble(grade)), Integer.parseInt(id));
        return newStudent;

    }

    public List<Item> getSomeStudent() {
        return someStudent;
    }

    public void setSomeStudent(List<Item> someStudent) {
        this.someStudent = someStudent;
    }

}
