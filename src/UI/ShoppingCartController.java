/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entity.Order;
import Entity.Product;
import Entity.ShoppingCart;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.Window;
import services.OrderServices;
import services.ProductServices;

/**
 * FXML Controller class
 *
 * @author paspo
 */
public class ShoppingCartController implements Initializable {
    
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private TableView<Product> tableProduct;
    @FXML
    private TableColumn<Product, Integer> table_id;
    @FXML
    private TableColumn<Product, String> table_productname;
    @FXML
    private TableColumn<Product, String> table_image;
    @FXML
    private TableColumn<Product, Integer> table_stock;
    @FXML
    private TableColumn<Product, Double> table_price;
    @FXML
    private Rectangle rectange;
    @FXML
    private Button gotoorder;
    @FXML
    private Button button_shopping_cart_test;
    @FXML
    private Label shipping_address;
    @FXML
    private TextField address;
    @FXML
    private TextField city;
    @FXML
    private TextField country;
    @FXML
    private TextField postal_code;
    @FXML
    private Button commander;
    
    ObservableList<Product> testsc;
    
    
    public static ObservableList<Product> product_list;
    
    @FXML
    public void affichershoppingcart(){
                shipping_address.setVisible(false);
                address.setVisible(false);
                city.setVisible(false);
                country.setVisible(false);
                postal_code.setVisible(false);
                commander.setVisible(false);
                rectange.setVisible(false);
        
        ObservableList<Product> list;
        list=FXCollections.observableArrayList();
        gotoorder.setOnAction(event -> {
            for(Product ppp : testsc){
            list.add(new Product(ppp.getId(),ppp.getProduct_name(),ppp.getImg(),ppp.getStock(),ppp.getPrice()));
        }
            System.out.println(list);
                table_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
                table_productname.setCellValueFactory(new PropertyValueFactory<>("Product_name"));
                table_image.setCellValueFactory(new PropertyValueFactory<>("Img"));
                table_stock.setCellValueFactory(new PropertyValueFactory<>("Stock"));
                table_price.setCellValueFactory(new PropertyValueFactory<>("Price"));
                tableProduct.setItems(list);
                shipping_address.setVisible(true);
                address.setVisible(true);
                city.setVisible(true);
                country.setVisible(true);
                postal_code.setVisible(true);
                commander.setVisible(true);
                rectange.setVisible(true);
                gotoorder.setDisable(true);
        });
        
        commander.setOnAction(event -> {
                double total = 0;
                for(Product product : tableProduct.getItems()){
                    total = total + product.getPrice();
                }
                String adr = address.getText()+" "+city.getText()+" "+country.getText()+" "+postal_code.getText();
                Order o = new Order(total,adr);
                OrderServices os = new OrderServices();
                os.addOrder(o, list);
        });

    }
    
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        affichershoppingcart();  
    }    
    
}
