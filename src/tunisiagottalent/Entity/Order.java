/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;
/**
 *
 * @author paspo
 */
public class Order {
    private int id;
    private Date order_date;
    private double total;
    private String address;

    public Order(int id, Date order_date, double total, String address) {
        this.id = id;
        this.order_date = order_date;
        this.total = total;
        this.address = address;
    }

    public Order(Date order_date, double total, String address) {
        this.order_date = order_date;
        this.total = total;
        this.address = address;
    }

    public Order(double total, String address) {
        this.total = total;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public double getTotal() {
        return total;
    }

    public String getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", order_date=" + order_date + ", total=" + total + ", address=" + address + '}';
    }
    
    
}
