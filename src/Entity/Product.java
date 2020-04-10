/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import javafx.scene.image.ImageView;

/**
 *
 * @author paspo
 */
public class Product {
    private int id;
    private String product_name;
    private String img;
    private int stock;
    private double price;


    
    
    public Product(int id,String product_name,String img,int stock,double price){
        this.id=id;
        this.product_name=product_name;
        this.img=img;
        this.stock=stock;
        this.price=price;
    }
    public Product(String product_name,String img,int stock,double price){
        this.product_name=product_name;
        this.img=img;
        this.stock=stock;
        this.price=price;
    }

    
    public int getId() {
        return id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getImg() {
        return img;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", product_name=" + product_name + ", img=" + img + ", stock=" + stock + ", price=" + price + '}';
    }
    

    
}
