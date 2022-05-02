/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

/**
 *
 * @author slalo
 */
public class ItemDataValidationException extends Exception {
 
    public ItemDataValidationException(String message) {
        super(message);
    }

    public ItemDataValidationException(String message,
            Throwable cause) {
        super(message, cause);
    }

}

