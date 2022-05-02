package views;

import Services.ServiceLayer;
import dao.ClassRosterDaoFileImpl;
import dao.ClassRosterPersistenceException;
import dto.Item;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

/**
 *
 * @author slalo
 */
public class ClassRosterView {

    private final UserIO io;
    private final ClassRosterDaoFileImpl dao = new ClassRosterDaoFileImpl();
    private final ServiceLayer services = new ServiceLayer(dao);
    private final DecimalFormat df = new DecimalFormat("#.00");

    public ClassRosterView(UserIO io) {
        this.io = io;
    }

    public int initialMenu() throws ClassRosterPersistenceException, Exception {
        int i = 1;
        io.print("Initial Menu");
        io.print("please select the produit you wish to buy");
        displayStudentList(services.getAllStudents(), i);
        io.print("6. Exit");

        return io.readInt("Please Select one of the above option", 1, 6);
    }

    public Item addStudentMenu() throws ClassRosterPersistenceException {
        io.print("Add student");
        String grade = io.readString("Please enter grade");
        BigDecimal name = io.readBigDecimal("Please enter name");
        int id = io.readInt("Please enter id");
        Item newStudent = new Item(grade, name, id);
        return newStudent;
    }

    public String deleteStudentMenu() {
        io.print("Delete student");
        String name = io.readString("Please enter the name of the student you wish to delete");
        return name;
    }

    public int editStudentMenu() throws ClassRosterPersistenceException {
        io.print("Buying a product ?");
        return io.readInt("Please enter the 0 to confirm your purchase");
    }

    public Item editStudent(Item someStudent) throws ClassRosterPersistenceException {

        int id = io.readInt("How many do you wanna buy ?");

        someStudent.setInStock(someStudent.getInStock() - id);

        io.readString("Thank you for your purchase");

        return someStudent;
    }

    public void listStudent(Item someStudent) {
        io.print("Name : " + someStudent.getName());
        io.print("Cost : " + someStudent.getCost());
        io.print("in stock : " + someStudent.getInStock());

    }

    public void listAllStudent(List<Item> someStudent) {
        for (Item newStudent : someStudent) {
            io.print(newStudent.getName());
            io.print(df.format(newStudent.getCost()));
            io.print(newStudent.getName());
            io.print("");
        }

    }

    public String getStudentMenu() {
        io.print("Find student");
        return io.readString("Enter student's name to find the student");

    }

    public void displayErrorMessage(String errorMsg) {
        io.print("===ERROR ===");
        io.print(errorMsg);
    }

    public Item getNewStudentInfo() throws ClassRosterPersistenceException {
        String Name = io.readString("Please enter an item Name");
        BigDecimal Grade = io.readBigDecimal("Please enter a price");
        int Id = io.readInt("Please enter how much item to stock up on");

        Item currentStudent = new Item(Name);
        currentStudent.setName(Name);
        currentStudent.setCost(Grade);
        currentStudent.setInStock(Id);
        return currentStudent;
    }

    public UserIO getIo() {
        return io;
    }

    public void displayStudentList(List<Item> studentList, int i) {
        for (Item currentStudent : studentList) {
            String studentInfo = String.format("#%s : %s %s", i + ". ",
                    currentStudent.getName(),
                    currentStudent.getCost() + "$",
                    currentStudent.getInStock());
            io.print(studentInfo);
            i++;
        }

    }
}
