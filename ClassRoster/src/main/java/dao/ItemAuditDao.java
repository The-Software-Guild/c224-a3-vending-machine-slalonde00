/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author slalo
 */
public interface ItemAuditDao {
   
    public void writeAuditEntry(String entry) throws ItemPersistenceException;
   
}
