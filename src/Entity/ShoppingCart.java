/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author paspo
 */
public class ShoppingCart implements Serializable{
    private ArrayList<Product> items;
    
    public ShoppingCart(){
        items = new ArrayList<Product>();
    }
    
    public ArrayList<Product> getItems(){
        return items;
    }
    
    public void addItem(Product p){
        items.add(p);
    }
    
    public void removeItem(Product p){
        int productid = p.getId();
        for(int i=0 ; i<items.size();i++){
            Product product = items.get(i);
            if(product.getId()==productid){
                items.remove(i);
            }
        }
    }
}
