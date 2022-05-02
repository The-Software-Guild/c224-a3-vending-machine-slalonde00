/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import dao.ItemPersistenceException;
import dto.Item;
import java.util.List;
import dao.ItemDaoFileImpl;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import dao.ItemAuditDao;
import dao.ItemDao;

/**
 *
 * @author slalo
 */
public class ServiceLayer implements ServiceLayerInterface {

    private ItemAuditDao auditDao;

    ItemDao dao;


    public ServiceLayer(ItemDao dao, ItemAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }


    @Override
    public List<Item> getAllItems() throws ItemPersistenceException {
        try {
            dao.loadItem();
        } catch (FileNotFoundException ex) {
            throw new ItemPersistenceException("Could not load the item into the file", ex);
        }
        return dao.getAllItem();
    }

    public List<Item> updateAllItems() throws ItemPersistenceException {
     
        return dao.updateAllItem();
    }

    
    @Override
    public Item getItem(int itemId) throws ItemPersistenceException {
        return dao.findItem(itemId);
    }


    public Item editItem(int itemId) throws ItemPersistenceException, FileNotFoundException {
        Item someItem = dao.findItem(itemId);
        dao.editItem(someItem);
    
        return someItem;
    }

    private void validateItemData(Item item) throws
            ItemDataValidationException {

        if (item.getName() == null
                || item.getName().trim().length() == 0
                || item.getPrice() == BigDecimal.valueOf(0)
                || item.getInStock() == 0) {
            throw new ItemDataValidationException(
                    "ERROR: All fields [First Name, Last Name, Cohort] are required.");
        }
    }


  
}
