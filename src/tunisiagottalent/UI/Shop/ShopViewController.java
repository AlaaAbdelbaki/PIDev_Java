/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Shop;

import tunisiagottalent.Entity.Product;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tunisiagottalent.Entity.Cart;
import tunisiagottalent.services.ProductServices;
import tunisiagottalent.util.UserSession;


public class ShopViewController implements Initializable {
 @FXML
    private Pagination pagination;
  private final static int rowsPerPage = 4;
    @FXML
    private ScrollPane products_view;
    @FXML
    private Button shopping_cart_button;
    @FXML
    AnchorPane rootPane;
    @FXML
    private ImageView product_image;
        @FXML
    private HBox productsHbox;
    
    int test = 0 ;
     ProductServices ps= new ProductServices();
    List<Product> ListUp=ps.getAll();;
        
       
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        pagination.setPageFactory(this::loadproductsforuser);
        
    }    
   
    public Node loadproductsforuser(int pageIndex){
        System.out.println(pageIndex);
        int fromIndex = pageIndex * rowsPerPage;
	int toIndex = Math.min(fromIndex + rowsPerPage, ListUp.size());
         if(fromIndex>=toIndex) return null;
	ObservableList<Product> products=FXCollections.observableArrayList(ListUp.subList(fromIndex, toIndex));
       
        
        
        productsHbox.getChildren().clear();
        products.forEach((p)->{
            VBox vbox = new VBox();
            HBox hboxstock = new HBox();
            Label title = new Label();
            title.setText(p.getProduct_name()+" ");
            title.setFont(new Font("Bold",30));
            title.setTextFill(Color.web("#55b3f3"));
            Label stock = new Label();
            
            
            ImageView product_image = new ImageView();

            product_image.setImage(new Image("http://127.0.0.1:8000/assets/img/shop-img/"+p.getImg()));
            product_image.setFitHeight(200);
            product_image.setFitWidth(200);
            
            VBox hbox = new VBox();
            hbox.setSpacing(10);
            hbox.setAlignment(Pos.CENTER);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tunisiagottalent/UI/Shop/ViewItemDetails.fxml"));
            Parent third = loader.load();
            ViewItemDetailsController itemcontroller = loader.getController();
            itemcontroller.getitemdetailsfromproductview(p);
            Scene s = new Scene(third);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setOpacity(0.9);
            stage.setTitle("Product Details");
            stage.initOwner(rootPane.getScene().getWindow());
            stage.setScene(s);

            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }});
                    
               
            
            
            addtocartbutton.setOnAction(event -> {
                    test = 0;
                Cart.instance.getC().forEach( cartP -> {
                    if(cartP.getId()==p.getId()){
                        test = 1;
                        int quantity=cartP.getQuantity()+1;
                        cartP.setQuantity(quantity);
                    }

                });
                
                if(test == 0){
                        //sc.addItem(p);
                        Cart.instance.AddProduct(p);
                }
            });
            hboxstock.getChildren().addAll(title,stock);
            hbox.getChildren().addAll(viewitembutton,addtocartbutton);
            vbox.getChildren().addAll(hboxstock,product_image,hbox);
            vbox.setSpacing(20);
            productsHbox.setSpacing(20);
            
            productsHbox.getChildren().add(vbox);
        });
        
        shopping_cart_button.setOnAction(event -> {
            try {
                System.out.println(Cart.instance);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tunisiagottalent/UI/Shop/ShoppingCart.fxml"));
                Parent third = loader.load();
                
                
            Scene s = new Scene(third);
            s.setFill(Color.TRANSPARENT);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setOpacity(0.9);
            stage.setTitle("Product Details");
            stage.initOwner(rootPane.getScene().getWindow());
            stage.setScene(s);

            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        });
        return productsHbox;
    }
    
        public void gotoorderlist() throws IOException{
            AnchorPane pane=FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Shop/Shop.fxml"));
            rootPane.getChildren().setAll(pane);
    }

    
  
}
