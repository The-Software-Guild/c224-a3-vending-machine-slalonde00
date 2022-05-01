/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Item;
import java.util.List;

/**
 *
 * @author slalo
 */
public interface ClassRosterDao {
    Item addStudent(Item someStudent, String name) throws ClassRosterPersistenceException;
    
    Item removeStudent(String id) throws ClassRosterPersistenceException;
    
    Item editStudent(Item someStudent, int id) throws ClassRosterPersistenceException;
    
    List<Item> getAllStudent()throws ClassRosterPersistenceException;
    
    Item findStudent(int address) throws ClassRosterPersistenceException;
}
