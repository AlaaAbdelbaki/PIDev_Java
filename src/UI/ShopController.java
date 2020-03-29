/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entity.Product;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.OrderServices;
import services.ProductServices;

/**
 * FXML Controller class
 *
 * @author paspo
 */
public class ShopController implements Initializable {

    /*@FXML
    TextField product_name = new TextField();
    
    @FXML
    TextField img = new TextField();
    
    @FXML
    TextField stock = new TextField();
    
    @FXML
    TextField price = new TextField();
   
    @FXML
    Button btn = new Button();
    
    
    
    @FXML
    void insertIntoOrder(ActionEvent event) throws IOException{
        String product_namedb = product_name.getText();
        String imgdb = img.getText();
        String stocksb = stock.getText();
        String pricedb = price.getText();
        
        int intstock=Integer.parseInt(stocksb);
        double dbdouble=Double.parseDouble(pricedb);
        
        Product p1 = new Product(product_namedb, imgdb, intstock, dbdouble);
        ProductServices ps = new ProductServices();
        
        ps.addProduct(p1);

    }*/
    //private Label product_label;
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
    
    private ObservableList<Product> product_list;
    

    
    
    
    public void afficherProductsTableView() {
        
        ProductServices ps = new ProductServices();
        
        product_list = FXCollections.observableArrayList(ps.getAll());
        
        table_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        table_productname.setCellValueFactory(new PropertyValueFactory<>("Product_name"));
        table_image.setCellValueFactory(new PropertyValueFactory<>("Img"));
        table_stock.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        table_price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        
        Callback<TableColumn<Product, String>, TableCell<Product, String>> cellFactory = (param) -> {
            final TableCell<Product, String> cell = new TableCell<Product, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button deletebutton = new Button("Delete");
                        deletebutton.setOnAction(event -> {
                            Product p = getTableView().getItems().get(getIndex());
                            ProductServices ps = new ProductServices();
                            product_list.remove(p);
                            ps.deleteProduct(p.getId());
                        });

                        
                        setGraphic(deletebutton);
                        setText(null);
                        
                    }
                    
                };
                
            };
            
            return cell; //To change body of generated lambdas, choose Tools | Templates.
        };
        
        Callback<TableColumn<Product, String>, TableCell<Product, String>> cellFactory1 = (param) -> {
            final TableCell<Product, String> cell = new TableCell<Product, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button editbutton = new Button("Edit");
                        editbutton.setOnAction(event ->{
                            Product pa = getTableView().getItems().get(getIndex());
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/EditProduct.fxml"));
                                Parent second = loader.load();
                                
                                EditProductController secondcontroller = loader.getController();
                                secondcontroller.modifiyproductbutton(pa);
                                
                                Scene s = new Scene(second);
                                Stage stageedit = new Stage();
                                stageedit.setTitle("Edit Product n° : "+pa.getId());
                                stageedit.setScene(s);
                                
                                stageedit.show();
                                
                            } catch (IOException ex) {
                                Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                             
                        });
                        
                        setGraphic(editbutton);
                        setText(null);
                        
                    }
                    
                };
                
            };
            
            return cell; //To change body of generated lambdas, choose Tools | Templates.
        };
        
        table_delete.setCellFactory(cellFactory);
        table_edit.setCellFactory(cellFactory1);
        
        
        tableProduct.setItems(product_list);
        
    }
    
    public void afficheraddproduct(){
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/AddProduct.fxml"));
            Parent third = loader.load();
            Scene s = new Scene(third);
            Stage stageadd = new Stage();
            stageadd.setTitle("Add Product");
            stageadd.setScene(s);
                                
        stageadd.show();
        } catch (IOException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherProductsTableView();
    }    
    
}
