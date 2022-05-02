/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import dao.ClassRosterPersistenceException;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 *
 * @author slalo
 */
public class UserIOConsoleImpl implements UserIO {

    private Scanner sc = new Scanner(System.in);

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public int readInt(String prompt) throws ClassRosterPersistenceException {
        System.out.println(prompt);
        int ans;

        ans = 0;
        try {

            ans = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            throw new ClassRosterPersistenceException("Integer was not entered");

        }

        return ans;
    }

    @Override
    public int readInt(String prompt, int min, int max) throws ClassRosterPersistenceException {
        int ans = 0;

        while (true) {
            System.out.println(prompt);
            try {
                ans = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                throw new ClassRosterPersistenceException("Integer was not entered");
            }

            if (ans >= min && ans <= max) {
                break;
            }
            System.out.println("Please enter an Integer between " + min + " and " + max);

        }
        return ans;
    }

    @Override
    public String readString(String prompt) {
        String nextline;
        System.out.println(prompt);
        nextline = sc.nextLine();

        return nextline;

    }

    @Override
    public double readDouble(String prompt) {
        return Double.parseDouble(prompt);
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        return Double.parseDouble(prompt);
    }

    @Override
    public float readFloat(String prompt) {
        return Float.parseFloat(prompt);
    }

    @Override
    public float readFloat(String prompt, float min, float max) {

        return Float.parseFloat(prompt);
    }

    @Override
    public long readLong(String prompt) {
        return Long.parseLong(prompt);
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        return Long.parseLong(prompt);
    }

    @Override
    public String returnString(String prompt) {
         String nextline;
        nextline = sc.nextLine();

        return nextline;
     }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
         return BigDecimal.valueOf(Double.parseDouble(prompt));
     
    }

}
