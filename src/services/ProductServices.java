/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DataSource;

/**
 *
 * @author paspo
 */
public class ProductServices {
    private Connection cnx;
    private Statement ste;
    private PreparedStatement pste;
    private ResultSet rez;
    
    public ProductServices(){
        cnx=DataSource.getInstance().getCnx();
    }
    
    public ObservableList<Product> getAll(){
        String req= "select * from product ;";
        ObservableList<Product> list ;
        list=FXCollections.observableArrayList();
        
        try {
            ste=cnx.createStatement();
            rez=ste.executeQuery(req);
            
            while(rez.next()){
                list.add(new Product(rez.getInt("id"),rez.getString("product_name"),rez.getString("img"),rez.getInt("stock"),rez.getDouble("price")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        
    }
    
    public void addProduct(Product p){
        String req="insert into product(product_name,img,stock,price) values(?,?,?,?) ;";
        
        try {
            
            pste=cnx.prepareStatement(req);
            pste.setString(1, p.getProduct_name());
            pste.setString(2, p.getImg());
            
            pste.setInt(3, p.getStock());
            pste.setDouble(4, p.getPrice());
            
            pste.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void deleteProduct(int id){
        String req="delete from product where id='"+id+"' ;";
        
        try {
            ste=cnx.createStatement();
            ste.executeUpdate(req);
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     public void modifyProduct(Product p,int id){
        String req = "update product set product_name='"+p.getProduct_name()+"',img='"+p.getImg()+"',stock='"+p.getStock()+"',price='"+p.getPrice()+"' where id='"+id+"' ;";
        
        try {
            ste=cnx.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ProductServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
