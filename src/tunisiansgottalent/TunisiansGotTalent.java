/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiansgottalent;

import Entity.Product;
import Entity.ShoppingCart;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ProductServices;

/**
 *
 * @author paspo
 */
public class TunisiansGotTalent extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*Product p = new Product("testjava","C:\\Users\\paspo\\Documents\\Untitled.png",15,25);
        ProductServices crud = new ProductServices();
        crud.addProduct(p);*/
        
        /*ProductServices crud = new ProductServices();
        crud.getAll().forEach(System.out::println);*/
        
       /*Order o = new Order(666,"test 666");
       Order o1 = new Order(666,"corona");
       OrderServices crud = new OrderServices();*/
      
        
        /*Order o = new Order(777,"test 777");
        Product p1 = new Product(1002,"testjava1","C:\\Users\\paspo\\Documents\\Untitled.png1",1,1);
        Product p2 = new Product(1001,"testjava2","C:\\Users\\paspo\\Documents\\Untitled.png2",2,2);
        Product p3 = new Product(1000,"testjava3","C:\\Users\\paspo\\Documents\\Untitled.png3",3,3);*/
        
        /*List<Product> list;
        list = new ArrayList<>();
        
        list.add(p1);
        list.add(p2);
        list.add(p3);
        
        crudOrder crudo = new crudOrder();
        crudo.addOrder(o, list);*/
        
        /*ShoppingCart shoppingcart = new ShoppingCart();
        shoppingcart.addItem(p1);
        shoppingcart.addItem(p2);
        shoppingcart.addItem(p3);
        
        shoppingcart.getItems().forEach(System.out::println);
        
        shoppingcart.removeItem(p2);*/
        //Product p3 = new Product(1000,"testjava3","C:\\Users\\paspo\\Documents\\Untitled.png3",3,3);
        ShoppingCart shoppingcart = new ShoppingCart();
        //shoppingcart.addItem(p3);
        //shoppingcart.getItems().forEach(System.out::println);
        //shoppingcart.removeItem(p3);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane mainpane = FXMLLoader.load(TunisiansGotTalent.class.getResource("/UI/Shop.fxml"));
        Scene scene = new Scene(mainpane);
        
        primaryStage.setTitle("test test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
