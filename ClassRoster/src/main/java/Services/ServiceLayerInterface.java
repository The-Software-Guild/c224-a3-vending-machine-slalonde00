/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Services.ServiceLayerException;
import dto.Item;
import dao.ClassRosterPersistenceException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author slalo
 */
public interface ServiceLayerInterface {

    void createItem(Item student) throws
            ClassRosterDuplicateIdException,
            ClassRosterDataValidationException,
            ClassRosterPersistenceException;

    List<Item> getAllItems() throws ClassRosterPersistenceException;

    Item getItem(String studentId) throws
            ClassRosterPersistenceException;

    Item removeItem(String studentId) throws
            ClassRosterPersistenceException;

   List<Item> updateAllItems() throws ClassRosterPersistenceException;

   List<Item> getMap();
   
}
