/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Student;
import java.util.List;

/**
 *
 * @author slalo
 */
public interface ClassRosterDao {
    Student addStudent(Student someStudent, String name) throws ClassRosterPersistenceException;
    
    Student removeStudent(String name) throws ClassRosterPersistenceException;
    
    Student editStudent(Student someStudent, String name) throws ClassRosterPersistenceException;
    
    List<Student> getAllStudent()throws ClassRosterPersistenceException;
    
    Student findStudent(String adress) throws ClassRosterPersistenceException;
}
