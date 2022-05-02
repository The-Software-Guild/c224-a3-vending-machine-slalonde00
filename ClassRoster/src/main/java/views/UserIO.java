

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import dao.ClassRosterPersistenceException;
import java.math.BigDecimal;

/**
 *
 * @author slalo
 */
public interface UserIO {
    

    void print(String msg);

    int readInt(String prompt) throws ClassRosterPersistenceException;

    int readInt(String prompt, int min, int max) throws ClassRosterPersistenceException;

    String readString(String prompt);


    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);

    String returnString(String prompt);
    
    BigDecimal readBigDecimal(String prompt);
}
    

