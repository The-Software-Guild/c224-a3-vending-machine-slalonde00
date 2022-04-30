/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Item;
import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author slalo
 */
public class ClassRosterDaoFileImpl implements ClassRosterDao {

    private final String studentFile;
    public static final String delemiter = "::";
    private Map<String, Item> someStudent = new HashMap<String, Item>();

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
        someStudent.put(name, newStudent);
        writeStudent();
        return newStudent;
    }

    @Override
    public Item removeStudent(String name) throws ClassRosterPersistenceException {
        try {
            loadStudent();
        } catch (FileNotFoundException ex) {
        } catch (Exception ex) {
        }
        Item removedStudent = someStudent.remove(name);
        writeStudent();
        return removedStudent;
    }

    @Override
    public Item editStudent(Item editedStudent, String name) throws ClassRosterPersistenceException {
        try {
            loadStudent();
        } catch (FileNotFoundException ex) {
        } catch (Exception ex) {
        }
        someStudent.remove(name);
        Item newStudent = someStudent.put(editedStudent.getName(), editedStudent);
        return newStudent;
    }

    @Override
    public List<Item> getAllStudent() throws ClassRosterPersistenceException {
        try {
            loadStudent();
        } catch (FileNotFoundException ex) {

        } catch (Exception ex) {
        }
        return new ArrayList(someStudent.values());
    }

    @Override
    public Item findStudent(String adress) throws ClassRosterPersistenceException {
        try {
            loadStudent();
        } catch (FileNotFoundException ex) {
        } catch (Exception ex) {

        }
        return someStudent.get(adress);
    }

    private void writeStudent()
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

    private void loadStudent() throws ClassRosterPersistenceException, FileNotFoundException, Exception {
        Scanner s;
        try {
            s = new Scanner(new BufferedReader(new FileReader(studentFile)));
        } catch (FileNotFoundException e) {
            throw new ClassRosterPersistenceException("Could not load the student into the file", e);
        }

        while (s.hasNextLine()) {
            String currentLine = s.nextLine();
            Item currentStudent = unMarshallStudents(currentLine);
            someStudent.put(currentStudent.getName(), currentStudent);
        }
        s.close();

    }

    private String marshallStudents(Item someStudent) {
        String studentAsString = someStudent.getGrade() + delemiter;
        studentAsString += someStudent.getName() + "::";
        studentAsString += someStudent.getId() + "::";
        return studentAsString;
    }

    private Item unMarshallStudents(String studentAsText) throws FileNotFoundException {
        String[] studentInfo = studentAsText.split(delemiter);
        String name = studentInfo[0];
        String grade = studentInfo[1];
        String id = studentInfo[2];

        Item newStudent = new Item(name, grade, id);
        return newStudent;

    }

    public Map<String, Item> getSomeStudent() {
        return someStudent;
    }

    public void setSomeStudent(Map<String, Item> someStudent) {
        this.someStudent = someStudent;
    }

}
