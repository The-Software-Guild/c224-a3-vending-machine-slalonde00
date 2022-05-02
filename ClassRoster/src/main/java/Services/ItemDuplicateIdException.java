/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

/**
 *
 * @author slalo
 */
public class ItemDuplicateIdException extends Exception {
    
        public ItemDuplicateIdException(String message) {
        super(message);
    }

    public ItemDuplicateIdException(String message,
            Throwable cause) {
        super(message, cause);
    }

}

