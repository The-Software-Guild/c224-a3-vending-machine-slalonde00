/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.math.BigDecimal;

/**
 *
 * @author slalo
 */
public class Customer {

    public Customer() {
    }

    public enum Coin {
        TOKEN(new BigDecimal("1"));
        public final BigDecimal label;

        public static Coin getTOKEN() {
            return TOKEN;
        }

        public BigDecimal getLabel() {
            return label;
        }

        Coin(BigDecimal label) {
            this.label = label;
        }
    }

    private int numberCoin = 0;

    private Double total;

    public int getNumberCoin() {
        return numberCoin;
    }

    public void setNumberCoin(int numberCoin) {
        this.numberCoin = numberCoin;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Customer(int numberCoin, double total) {
        this.numberCoin = numberCoin;
        this.total = total;

    }

    public Customer(double total) {
        this.total = total;
    }

}
