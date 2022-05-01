/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Services.ServiceLayerException;
import dto.Item;
import dao.ClassRosterPersistenceException;
import java.util.List;

/**
 *
 * @author slalo
 */
public interface ServiceLayerInterface {

    void createStudent(Item student) throws
            ClassRosterDuplicateIdException,
            ClassRosterDataValidationException,
            ClassRosterPersistenceException;

    List<Item> getAllStudents() throws ClassRosterPersistenceException;

    Item getStudent(int studentId) throws
            ClassRosterPersistenceException;

    Item removeStudent(String studentId) throws
            ClassRosterPersistenceException;

}
