/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author slalo
 */
public class Item {

    private BigDecimal cost;
    private String name = "";
    private int inStock;
   

   
    public Item(String studentId) {
        this.name = studentId;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public Item(String name, BigDecimal grade, int id) {
        this.name = name;
        this.cost = grade;
        this.inStock = id;

    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (!Objects.equals(this.cost, other.cost)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.inStock, other.inStock);
    }

    @Override
    public String toString() {
        return "Student{" + "Name= " + name + "  Grade = " + cost + ", id=" + inStock + '}';
    }

}
