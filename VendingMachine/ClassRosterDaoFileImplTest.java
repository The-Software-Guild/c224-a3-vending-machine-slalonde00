/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import dto.Student;
import java.io.FileWriter;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author slalo
 */
public class ClassRosterDaoFileImplTest {

    private ClassRosterDaoFileImpl testDao;

    public ClassRosterDaoFileImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "testroster.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        testDao = new ClassRosterDaoFileImpl(testFile);
    }

    @AfterEach
    public void tearDown() {
    }


    @Test
    public void testAddGetStudent() throws Exception {
        // Create our method test inputs
        String studentId = "0001";
        Student student = new Student(studentId);
        student.setName("Ada");
        student.setGrade("100");
        student.setId("Java-May-1845");

        //  Add the student to the DAO
        testDao.addStudent(student, studentId);
        // Get the student from the DAO
        Student retrievedStudent = testDao.findStudent(studentId);

        // Check the data is equal
        assertEquals(student.getName(),
                retrievedStudent.getName(),
                "Checking student name.");
        assertEquals(student.getGrade(),
                retrievedStudent.getGrade(),
                "Checking student first grade.");
        assertEquals(student.getId(),
                retrievedStudent.getId(),
                "Checking student id.");
    }

    @Test
    public void testAddGetAllStudents() throws Exception {
        // Create our first student
        Student firstStudent = new Student("0001");
        firstStudent.setName("Ada");
        firstStudent.setGrade("Lovelace");
        firstStudent.setId("Java-May-1845");

        // Create our second student
        Student secondStudent = new Student("0002");
        secondStudent.setName("Charles");
        secondStudent.setGrade("Babbage");
        secondStudent.setId(".NET-May-1845");

        // Add both our students to the DAO
        testDao.addStudent(firstStudent, firstStudent.getName());
        testDao.addStudent(secondStudent, secondStudent.getName());

        // Retrieve the list of all students within the DAO
        List<Student> allStudents = testDao.getAllStudent();

        // First check the general contents of the list
        assertNotNull(allStudents, "The list of students must not null");
        assertEquals(2, allStudents.size(), "List of students should have 2 students.");

        // Then the specifics
        assertTrue(testDao.getAllStudent().contains(firstStudent),
                "The list of students should include Ada.");
        assertTrue(testDao.getAllStudent().contains(secondStudent),
                "The list of students should include Charles.");

    }

    @Test
    public void testRemoveStudent() throws Exception {
        // Create two new students
        Student firstStudent = new Student("0001");
        firstStudent.setName("Ada");
        firstStudent.setGrade("Lovelace");
        firstStudent.setId("Java-May-1945");

        Student secondStudent = new Student("0002");
        secondStudent.setName("Charles");
        secondStudent.setGrade("Babbage");
        secondStudent.setId(".NET-May-1945");

        // Add both to the DAO
        testDao.addStudent(firstStudent, firstStudent.getName());
        testDao.addStudent(secondStudent, secondStudent.getName());

        // remove the first student - Ada
        Student removedStudent = testDao.removeStudent(firstStudent.getName());

        // Check that the correct object was removed.
        assertEquals(removedStudent, firstStudent, "The removed student should be Ada.");

        // Get all the students
        List<Student> allStudents = testDao.getAllStudent();

        // First check the general contents of the list
        assertNotNull(allStudents, "All students list should be not null.");
        assertEquals(1, allStudents.size(), "All students should only have 1 student.");

        // Then the specifics
        assertFalse(allStudents.contains(firstStudent), "All students should NOT include Ada.");
        assertTrue(allStudents.contains(secondStudent), "All students should NOT include Charles.");

        // Remove the second student
        removedStudent = testDao.removeStudent(secondStudent.getName());
        // Check that the correct object was removed.
        assertEquals(removedStudent, secondStudent, "The removed student should be Charles.");

        // retrieve all of the students again, and check the list.
        allStudents = testDao.getAllStudent();

        // Check the contents of the list - it should be empty
        assertTrue(allStudents.isEmpty(), "The retrieved list of students should be empty.");

        // Try to 'get' both students by their old id - they should be null!
        Student retrievedStudent = testDao.findStudent(firstStudent.getName());
        assertNull(retrievedStudent, "Ada was removed, should be null.");

        retrievedStudent = testDao.findStudent(secondStudent.getName());
        assertNull(retrievedStudent, "Charles was removed, should be null.");

    }

}
