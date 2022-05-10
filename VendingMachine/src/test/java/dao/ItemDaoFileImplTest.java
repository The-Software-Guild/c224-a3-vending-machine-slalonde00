/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import Services.ServiceLayer;
import dto.Customer;
import dto.Item;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import views.ItemView;
import views.UserIOConsoleImpl;

/**
 *
 * @author slalo
 */
public class ItemDaoFileImplTest {

    ItemDaoFileImpl instance;
    List<Item> someItemList;

    public ItemDaoFileImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        instance = new ItemDaoFileImpl("testItem.txt");
        someItemList = new ArrayList();

    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of editItem method, of class ItemDaoFileImpl.
     */
    @Test
    public void testEditItem() throws ItemPersistenceException, FileNotFoundException {
        System.out.println("editItem");
        Item oldItem = new Item("test", 1.0, 10, 0);
        instance.addItem(oldItem);
        List<Item> updatedList = instance.updateAllItem();
        System.out.println(updatedList);
        Item expResult = new Item("test", 1.0, 9, 0);
        Item result = instance.editItem(oldItem, 1);

        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of editCustomer method, of class ItemDaoFileImpl.
     */
    @Test
    public void testEditCustomer() throws ItemPersistenceException {
        System.out.println("editCustomer");
        int amountCoin = 100;
        Item boughtItem = new Item("test2", 1, 1, 1);
        Customer editedCustomer = new Customer(amountCoin - 1);
        Customer oldCustomer = new Customer(amountCoin);
        Double expResult = editedCustomer.getTotal();
        System.out.println(oldCustomer.getTotal());
        Double result = instance.editCustomer(oldCustomer, boughtItem).getTotal();
        System.out.println(oldCustomer.getTotal());
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

}
