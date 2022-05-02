    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package dao;

    import dto.Item;
    import java.util.*;
    import java.io.*;
    import java.math.BigDecimal;

    /**
     *
     * @author slalo
     */
            public class ClassRosterDaoFileImpl implements ClassRosterDao {

        private final String studentFile;
        public static final String delemiter = "::";
        private Map<String, Item> someItem = new HashMap<String, Item>();

        public ClassRosterDaoFileImpl() {
            studentFile = "items.txt";
        }

        public ClassRosterDaoFileImpl(String studentFile) {
            this.studentFile = studentFile;
        }

       public List<Item> getMap(){
           return new ArrayList(someItem.values());
       }

        @Override
        public Item addItem(Item newItem, String name) throws ClassRosterPersistenceException {

            try {
                loadItem();
            } catch (FileNotFoundException ex) {
            } catch (Exception ex) {

            }
            someItem.put(name, newItem);
            writeItem();
            return newItem;
        }

        @Override
        public Item removeItem(String name) throws ClassRosterPersistenceException {

            Item removedItem = someItem.remove(name);
            writeItem();
            return removedItem;
        }

        @Override
        public Item editItem(Item editedItem, String name) throws ClassRosterPersistenceException {


            someItem.remove(name);
            Item newItem = someItem.put(editedItem.getName(), editedItem);
            writeItem();
            return newItem;
        }

        @Override
        public List<Item> getAllItem() throws ClassRosterPersistenceException {
            try {
                loadItem();
            } catch (FileNotFoundException ex) {

            } catch (Exception ex) {
            }
            ArrayList newArray = new ArrayList(someItem.values());

            return newArray;
        }

        public List<Item> updateAllItem() throws ClassRosterPersistenceException {

             List<Item> newArray = new ArrayList(someItem.values());

            System.out.println(newArray);

            return newArray;
        }

        @Override
        public Item findItem(String adress) throws ClassRosterPersistenceException {
            try {
               loadItem();
            } catch (FileNotFoundException ex) {
            } catch (Exception ex) {

            }
            return someItem.get(adress);
        }

        private void writeItem()
                throws ClassRosterPersistenceException {

            PrintWriter out;
            try {
                out = new PrintWriter(new FileWriter(studentFile));
                System.out.println("Writing students infos to file : ");

                System.out.println(updateAllItem());

                List<Item> studentList = updateAllItem();
                String studentAsText;

                for (Item student : studentList) {

                    studentAsText = marshallItems(student);

                    out.println(studentAsText);

                    out.flush();

                }   

                out.close();

            } catch (IOException e) {
                throw new ClassRosterPersistenceException("Could not save data to file", e);
            }

        }

        public void loadItem() throws ClassRosterPersistenceException, FileNotFoundException {
            Scanner s;
            try {
                s = new Scanner(new BufferedReader(new FileReader(studentFile)));
            } catch (FileNotFoundException e) {
                throw new ClassRosterPersistenceException("Could not load the student into the file", e);
            }

            while (s.hasNextLine()) {
                String currentLine = s.nextLine();
                Item currentItem = unMarshallItems(currentLine);
                someItem.put(currentItem.getName(), currentItem);
            }
            s.close();

        }

        private String marshallItems(Item someItem) {
            String studentAsString = someItem.getName() + delemiter;
            studentAsString += someItem.getPrice()+ delemiter;
            studentAsString += someItem.getInStock() + delemiter;
            return studentAsString;
        }

        private Item unMarshallItems(String studentAsText) throws FileNotFoundException, ClassRosterPersistenceException {

            String[] studentInfo = studentAsText.split(delemiter);
            String name = studentInfo[0];
           BigDecimal grade = BigDecimal.valueOf(Double.parseDouble(studentInfo[1]));
            int id = Integer.parseInt(studentInfo[2]);
            Item newItem = new Item(name, grade, id);
            return newItem;

        }

        public Map<String, Item> getSomeItem() {
            return someItem;
        }

        public void setSomeItem(Map<String, Item> someItem) {
            this.someItem = someItem;
        }

    }
