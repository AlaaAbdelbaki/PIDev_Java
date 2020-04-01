/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entity.Product;
import Entity.ShoppingCart;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author paspo
 */
public class ShoppingCartController implements Initializable {

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
    private TableColumn table_edit;
    @FXML
    private TableColumn table_delete;
    @FXML
    private Button gotoorder;
    
    private ObservableList<Product> product_list;
    
    public void affichershoppingcart(){
        ShoppingCart sc = new ShoppingCart();
        product_list = FXCollections.observableArrayList(sc.getItems());
        
        table_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        table_productname.setCellValueFactory(new PropertyValueFactory<>("Items"));
        table_image.setCellValueFactory(new PropertyValueFactory<>("Img"));
        table_stock.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        table_price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        
        tableProduct.setItems(product_list);
        gotoorder.setOnAction(event -> {
            Product p1 = new Product(1002,"testjava1","C:\\Users\\paspo\\Documents\\Untitled.png1",1,1);
            Product p2 = new Product(1001,"testjava2","C:\\Users\\paspo\\Documents\\Untitled.png2",2,2);
            Product p3 = new Product(1000,"testjava3","C:\\Users\\paspo\\Documents\\Untitled.png3",3,3);
            sc.addItem(p3);
            sc.addItem(p2);
            sc.addItem(p1);
            System.out.println("test test");
            System.out.println(sc.getItems());
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        affichershoppingcart();
    }    
    
}
