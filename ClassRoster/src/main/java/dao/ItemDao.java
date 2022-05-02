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
public interface ItemDao {
    
    Item editItem(Item someItem) throws ItemPersistenceException;
    
    List<Item> getAllItem()throws ItemPersistenceException;
    
    Item findItem(int adress) throws ItemPersistenceException;
    
    void loadItem() throws ItemPersistenceException, FileNotFoundException;
    
    public List<Item> updateAllItem() throws ItemPersistenceException;
    
    
   
}
