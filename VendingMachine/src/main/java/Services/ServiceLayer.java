/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import dao.ItemPersistenceException;
import dto.Item;
import java.util.List;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import dao.ItemAuditDao;
import dao.ItemDao;
import dto.Customer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author slalo
 */
public class ServiceLayer implements ServiceLayerInterface {

    private ItemAuditDao auditDao;

    ItemDao dao;

    public ServiceLayer(ItemDao dao) {
        this.dao = dao;
    }

    public ServiceLayer(ItemDao dao, ItemAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    public List<Item> addItem(Item newItem) {
        return dao.addItem(newItem);
    }

    @Override
    public List<Item> getAllItems() throws ItemPersistenceException {
        return dao.getAllItem();
    }

    public List<Item> updateAllItems() throws ItemPersistenceException {

        return dao.updateAllItem();
    }

    @Override
    public Item getItem(int itemId) throws ItemPersistenceException {

        return dao.findItem(itemId);
    }

    public void loadItem() throws ItemPersistenceException, FileNotFoundException {
        dao.loadItem();
    }

    public Customer getCustomer() {
        return dao.getCustomer();
    }

    public Customer editCustomer(Customer customer, int itemId) throws ItemPersistenceException {
        return dao.editCustomer(dao.findItem(itemId));
    }

    @Override
    public Item editItem(Item someItem, int itemIndex, int howMany) throws ItemPersistenceException, FileNotFoundException {
        Item itemToFind = dao.findItem(itemIndex);
        dao.editItem(itemToFind, howMany);

        return someItem;
    }

    private void validateItemData(Item item) throws
            ItemDataValidationException {

        if (item.getName() == null
                || item.getName().trim().length() == 0
                || item.getPrice() == 0
                || item.getInStock() == 0) {
            throw new ItemDataValidationException(
                    "ERROR: All fields [First Name, Last Name, Cohort] are required.");
        }
    }

}
