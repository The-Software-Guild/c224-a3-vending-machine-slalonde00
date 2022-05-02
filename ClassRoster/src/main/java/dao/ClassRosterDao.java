/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Item;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author slalo
 */
public interface ClassRosterDao {
    Item addItem(Item someItem, String name) throws ClassRosterPersistenceException;
    
    Item removeItem(String name) throws ClassRosterPersistenceException;
    
    Item editItem(Item someItem, String name) throws ClassRosterPersistenceException;
    
    List<Item> getAllItem()throws ClassRosterPersistenceException;
    
    Item findItem(String adress) throws ClassRosterPersistenceException;
    
    void loadItem() throws ClassRosterPersistenceException, FileNotFoundException;
    
    public List<Item> updateAllItem() throws ClassRosterPersistenceException;
    
    List<Item> getMap();
    
   
}
