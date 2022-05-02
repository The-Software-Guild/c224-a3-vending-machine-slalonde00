/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author slalo
 */
public class ItemPersistenceException extends Exception {
   
    public ItemPersistenceException(String message) {
        super(message);
    }
    
   
    public ItemPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
