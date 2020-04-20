/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.Entity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public final class Cart {

    public static Cart instance;

    private final ObservableList<Product> c;

    public Cart() {
        c = FXCollections.observableArrayList();

    }

    public ObservableList<Product> getCartList() {
        return c;
    }

    public void AddProduct(Product e) {
        this.c.add(e);
    }

    public void RemoveProduct(Product e) {
        
       int productid = e.getId();
        for(int i=0 ; i<this.c.size();i++){
            Product product = this.c.get(i);
            if(product.getId()==productid){
                this.c.remove(i);
            }
        }
    }
    public void RemoveAll(){
    this.c.clear();
    }

    public ObservableList<Product> getC() {
        return c;
    }

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public void cleanCartSession() {
        instance = null;
    }

    @Override
    public String toString() {
        return "Cart{"
                + "c=" + c
                + '}';
    }
    public double total(){
        double total=0;
     for(Product product : this.c){
                    total = total + (product.getPrice()*product.getQuantity());
                }
    return total;
    }
}
