/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author slalo
 */
public class Customer {

    public Customer() {
    }

    private enum token {
        DOLLARS, PENNIES, QUARTER;

        public double getDollarsValue() {
            return 1;
        }

        public double getPenniesValue() {
            return 0.01;
        }

        public double getQuarterValue() {
            return 0.25;
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

    public double getTotal() {
        this.total = ((token.DOLLARS.getDollarsValue() * getNumberCoin()) + (token.PENNIES.getPenniesValue() * getNumberCoin()) + (token.QUARTER.getQuarterValue() * getNumberCoin()));;
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Customer(int numberCoin) {
        this.numberCoin = numberCoin;
        this.total = this.getTotal();

    }

}
