/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Shop;

import Entity.Product;
import Entity.ShoppingCart;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import services.ProductServices;


public class ShopViewController implements Initializable {

    @FXML
    private ScrollPane products_view;
    @FXML
    private Button shopping_cart_button;
    @FXML
    AnchorPane rootPane;
    @FXML
    private ImageView product_image;
    
    int test = 0 ;
    
    public void loadproductsforuser(){
        ProductServices ps= new ProductServices();
        List<Product> products;
        ShoppingCart sc=new ShoppingCart();
        products=ps.getAll();
        
        VBox vbox = new VBox();
        
        products.forEach((p)->{
            
            HBox hboxstock = new HBox();
            Label title = new Label();
            title.setText(p.getProduct_name()+" ");
            title.setFont(new Font("Bold",30));
            title.setTextFill(Color.web("#55b3f3"));
            Label stock = new Label();
            
            
            
            Image image;
            ImageView product_image = new ImageView();

            product_image.setImage(new Image(p.getImg()));
            product_image.setFitHeight(200);
            product_image.setFitWidth(200);
            
            HBox hbox = new HBox();
            hbox.setSpacing(10);
            Button viewitembutton = new Button("View Item");
            Button addtocartbutton = new Button("Add To Cart");
            
            if(p.getStock()>0){
                stock.setText("    In Stock");
                stock.setTextFill(Color.GREEN);
            }
            else{
                stock.setText("    Out Of Stock");
                stock.setTextFill(Color.RED);   
                addtocartbutton.setDisable(true);
            }
            
            viewitembutton.setOnAction(event -> {
                Product pa = p;
                
                
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Shop/ViewItemDetails.fxml"));
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
                    test = 0;
                sc.getItems().forEach( cart -> {
                    if(cart.getId()==p.getId()){
                        test = 1;
                        int quantity=cart.getQuantity()+1;
                        cart.setQuantity(quantity);
                    }

                });
                
                if(test == 0){
                        sc.addItem(p);
                }
            });
            hboxstock.getChildren().addAll(title,stock);
            hbox.getChildren().addAll(viewitembutton,addtocartbutton);
            vbox.getChildren().addAll(hboxstock,product_image,hbox);
            products_view.setContent(vbox);
        });
        
        shopping_cart_button.setOnAction(event -> {
            
            try {
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Shop/ShoppingCart.fxml"));
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
    
        public void gotoorderlist() throws IOException{
            AnchorPane pane=FXMLLoader.load(getClass().getResource("/UI/Shop/Shop.fxml"));
            rootPane.getChildren().setAll(pane);
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loadproductsforuser();
    }    
    
}
