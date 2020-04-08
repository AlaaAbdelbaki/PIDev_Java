/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entity.Product;
import Entity.ShoppingCart;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.ProductServices;


public class ShopViewController implements Initializable {

    @FXML
    private ScrollPane products_view;
    @FXML
    private Button shopping_cart_button;
    @FXML
    AnchorPane rootPane;
    
    public void loadproductsforuser(){
        ProductServices ps= new ProductServices();
        List<Product> products;
        ShoppingCart sc=new ShoppingCart();
        products=ps.getAll();
        
        VBox vbox = new VBox();
        
        products.forEach((p)->{
            Label title = new Label();
            title.setText(p.getProduct_name());

            ImageView product_image= new ImageView("/img/t-shirt.jpg");
            product_image.setFitHeight(200);
            product_image.setFitWidth(200);
            HBox hbox = new HBox();
            hbox.setSpacing(10);
            Button viewitembutton = new Button("View Item");
            Button addtocartbutton = new Button("Add To Cart");
            
            viewitembutton.setOnAction(event -> {
                Product pa = p;
                
                
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/ViewItemDetails.fxml"));
                    Parent second = loader.load();
                    
                    ViewItemDetailsController itemcontroller = loader.getController();
                    itemcontroller.getitemdetailsfromproductview(p);
                    Scene s = new Scene(second);
                    Stage stageedit = new Stage();
                    stageedit.setTitle("View Item n°: "+p.getId());
                    stageedit.setScene(s);
                    stageedit.show();
                } catch (IOException ex) {
                    Logger.getLogger(ShopViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            addtocartbutton.setOnAction(event -> {
                
                Product pa = p;
                sc.addItem(p);
                System.out.println(sc.getItems());
                
            });
            hbox.getChildren().addAll(viewitembutton,addtocartbutton);
            vbox.getChildren().addAll(title,product_image,hbox);
            products_view.setContent(vbox);
        });
        
        shopping_cart_button.setOnAction(event -> {
            
            try {
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/ShoppingCart.fxml"));
                Parent third = loader.load();
                ShoppingCartController shoppingcart = loader.getController();
                shoppingcart.testsc=sc.getItems();
                 
                
                Scene s = new Scene(third);
                Stage stageedit = new Stage();
                stageedit.setTitle("Shopping Cart");
                stageedit.setScene(s);
                                
                stageedit.show();
                
            } catch (IOException ex) {
                Logger.getLogger(ShopViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loadproductsforuser();
    }    
    
}
