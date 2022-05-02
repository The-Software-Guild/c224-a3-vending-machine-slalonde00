/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import dao.ClassRosterAuditDao;
import dao.ClassRosterDao;
import dao.ClassRosterPersistenceException;
import dto.Item;
import java.util.List;
import dao.ClassRosterDaoFileImpl;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author slalo
 */
public class ServiceLayer implements ServiceLayerInterface {

    private ClassRosterAuditDao auditDao;

    ClassRosterDao dao;


    public ServiceLayer(ClassRosterDao dao, ClassRosterAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public void createItem(Item student) throws
            ClassRosterDuplicateIdException,
            ClassRosterDataValidationException,
            ClassRosterPersistenceException {

        // First check to see if there is alreay a student 
        // associated with the given student's id
        // If so, we're all done here - 
        // throw a ClassRosterDuplicateIdException
        if (dao.findItem(student.getName()) != null) {
            throw new ClassRosterDuplicateIdException(
                    "ERROR: Could not create student.  Item Id "
                    + student.getName()
                    + " already exists");
        }

        // Now validate all the fields on the given Item object.  
        // This method will throw an
        // exception if any of the validation rules are violated.
        validateItemData(student);

        // We passed all our business rules checks so go ahead 
        // and persist the Item object
        dao.addItem(student, student.getName());

        // The student was successfully created, now write to the audit log
        auditDao.writeAuditEntry(
                "Item " + student.getName() + " CREATED.");

    }

    @Override
    public List<Item> getAllItems() throws ClassRosterPersistenceException {
        try {
            dao.loadItem();
        } catch (FileNotFoundException ex) {
            throw new ClassRosterPersistenceException("Could not load the student into the file", ex);
        }
        return dao.getAllItem();
    }

    public List<Item> updateAllItems() throws ClassRosterPersistenceException {
     
        return dao.updateAllItem();
    }

    
    @Override
    public Item getItem(String studentId) throws ClassRosterPersistenceException {
        return dao.findItem(studentId);
    }

    @Override
    public Item removeItem(String studentId) throws ClassRosterPersistenceException {
        Item removedItem = dao.removeItem(studentId);
        // Write to audit log
        auditDao.writeAuditEntry("Item " + studentId + " REMOVED.");
        return removedItem;
    }

    public Item editItem(String studentId) throws ClassRosterPersistenceException, FileNotFoundException {
        Item someItem = dao.findItem(studentId);
        dao.editItem(someItem, studentId);
     //   dao.updateAllItem();
        return someItem;
    }

    private void validateItemData(Item student) throws
            ClassRosterDataValidationException {

        if (student.getName() == null
                || student.getName().trim().length() == 0
                || student.getPrice() == BigDecimal.valueOf(0)
                || student.getInStock() == 0) {
            throw new ClassRosterDataValidationException(
                    "ERROR: All fields [First Name, Last Name, Cohort] are required.");
        }
    }

    public List<Item> getMap() {
        return dao.getMap();
    }
}
