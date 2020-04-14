/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.List;
/**
 *
 * @author paspo
 */
public class Order_Line {
    private int order_lin_id;
    private int order_id;
    private int product_id;
    private int quantity;

    public Order_Line(int order_lin_id, int order_id, int product_id, int quantity) {
        this.order_lin_id = order_lin_id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public Order_Line(int order_id, int product_id, int quantity) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public int getOrder_lin_id() {
        return order_lin_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setOrder_lin_id(int order_lin_id) {
        this.order_lin_id = order_lin_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order_Line{" + "order_lin_id=" + order_lin_id + ", order_id=" + order_id + ", product_id=" + product_id + ", quantity=" + quantity + '}';
    }
}
