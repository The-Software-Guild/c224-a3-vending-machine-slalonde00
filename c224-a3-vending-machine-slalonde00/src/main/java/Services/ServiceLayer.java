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
    
    public ServiceLayer(ClassRosterDao dao) {
        this.dao = dao;
       
    }

    @Override
    public void createStudent(Item student) throws
            ClassRosterDuplicateIdException,
            ClassRosterDataValidationException,
            ClassRosterPersistenceException {

        // First check to see if there is alreay a student 
        // associated with the given student's id
        // If so, we're all done here - 
        // throw a ClassRosterDuplicateIdException
        if (dao.findStudent(Integer.parseInt(student.getName())) != null) {
            throw new ClassRosterDuplicateIdException(
                    "ERROR: Could not create student.  Student Id "
                    + student.getName()
                    + " already exists");
        }

        // Now validate all the fields on the given Item object.  
        // This method will throw an
        // exception if any of the validation rules are violated.
        validateStudentData(student);

        // We passed all our business rules checks so go ahead 
        // and persist the Item object
        dao.addStudent(student, student.getName());

        // The student was successfully created, now write to the audit log
        auditDao.writeAuditEntry(
                "Student " + student.getName() + " CREATED.");

    }

    @Override
    public List<Item> getAllStudents() throws ClassRosterPersistenceException {
        return dao.getAllStudent();
    }

    @Override
    public Item getStudent(int studentId) throws ClassRosterPersistenceException {
        return dao.findStudent((studentId));
    }

    @Override
    public Item removeStudent(String studentId) throws ClassRosterPersistenceException {
        Item removedStudent = dao.removeStudent(studentId);
        // Write to audit log
        auditDao.writeAuditEntry("Student " + studentId + " REMOVED.");
        return removedStudent;
    }

    public Item editStudent(int studentId) throws ClassRosterPersistenceException {
        Item someStudent = dao.findStudent(((studentId)));
        dao.editStudent(someStudent, studentId);
        return someStudent;
    }

    private void validateStudentData(Item student) throws
            ClassRosterDataValidationException {

        if (student.getName() == null
                || student.getName().trim().length() == 0
                || student.getCost() == null
                || student.getInStock() == 0) {
            throw new ClassRosterDataValidationException(
                    "ERROR: All fields [First Name, Last Name, Cohort] are required.");
        }
    }
}
