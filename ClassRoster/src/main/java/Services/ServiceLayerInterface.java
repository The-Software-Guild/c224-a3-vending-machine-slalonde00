/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Services.ServiceLayerException;
import dto.Item;
import dao.ItemPersistenceException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
