/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import dto.Item;
import dao.ItemPersistenceException;
import java.util.List;

/**
 *
 * @author slalo
 */
public interface ServiceLayerInterface {

    List<Item> getAllItems() throws ItemPersistenceException;

    Item getItem(int itemId) throws
            ItemPersistenceException;

    List<Item> updateAllItems() throws ItemPersistenceException;

}
