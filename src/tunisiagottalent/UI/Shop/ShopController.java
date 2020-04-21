/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Shop;

import tunisiagottalent.Entity.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import tunisiagottalent.services.ProductServices;
import java.util.function.Predicate;
import javafx.collections.transformation.SortedList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import tunisiagottalent.UI.Base.MainController;

/**
 * FXML Controller class
 *
 * @author paspo
 */
public class ShopController implements Initializable {
    
    @FXML
    private TableView<Product> tableProduct;
    @FXML
    private TableColumn<Product, Integer> table_id;
    @FXML
    private TableColumn<Product, String> table_productname;

    @FXML
    private TableColumn<Product, Integer> table_stock;
    @FXML
    private TableColumn<Product, Double> table_price;

    @FXML
    private TableColumn table_edit;
    @FXML
    private TableColumn table_delete;
    @FXML
    private TableColumn table_imageview;

    @FXML
    private TextField product_search;
    
    @FXML
    private Button refreshprodutbutton;
    
    private ObservableList<Product> product_list;
    
    @FXML
    private AnchorPane rootPane;

    
    public void afficherProductsTableView() {
        ProductServices ps = new ProductServices();       
        product_list = FXCollections.observableArrayList(ps.getAll());
        table_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        table_productname.setCellValueFactory(new PropertyValueFactory<>("Product_name"));
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
            
            return cell;
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
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("EditProduct.fxml"));
                                Parent second = loader.load();
                                
                                EditProductController secondcontroller = loader.getController();
                                secondcontroller.modifiyproductbutton(pa);
                                
                                Scene s = new Scene(second);
                                s.setFill(Color.TRANSPARENT);
                                Stage stageedit = new Stage();
                                stageedit.initStyle(StageStyle.TRANSPARENT);
                                stageedit.setOpacity(0.95);
                                stageedit.setTitle("Edit Product n° : "+pa.getId());
                                stageedit.initOwner(rootPane.getScene().getWindow());
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
            
            return cell;
        };
        
        
                Callback<TableColumn<Product, String>, TableCell<Product, String>> cellFactory2 = (param) -> {
            final TableCell<Product, String> cell = new TableCell<Product, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                            Product pa = getTableView().getItems().get(getIndex());
                          Label lb = new Label();
                          ImageView product_image_view = new ImageView();
                          product_image_view.setImage(new Image("http://127.0.0.1:8000/assets/img/shop-img/"+pa.getImg()));
                          product_image_view.setFitHeight(80);
                          product_image_view.setFitWidth(80);
                          lb.setGraphic(product_image_view);
                           setGraphic(lb);
                           setText(null);
                    }
                    
                };
                
            };
            
            return cell;
        };
       
        table_delete.setCellFactory(cellFactory);
        table_edit.setCellFactory(cellFactory1);
        table_imageview.setCellFactory(cellFactory2);

        FilteredList<Product> filteredProduct = new FilteredList<>(product_list,b->true);
        
        product_search.textProperty().addListener((observable,oldValue,newValue) -> {
            filteredProduct.setPredicate((Predicate<? super Product>) Product -> {
            if(newValue == null || newValue.isEmpty()){
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if(String.valueOf(Product.getId()).indexOf(lowerCaseFilter) != -1){
                return true;
            }
            else if(Product.getProduct_name().toLowerCase().indexOf(lowerCaseFilter) != -1){
                return true;
            }
            else if(String.valueOf(Product.getPrice()).indexOf(lowerCaseFilter) != -1){
                    return true;
            }
            else if (String.valueOf(Product.getStock()).indexOf(lowerCaseFilter) != -1){
                    return true;
            }
            else{
                    return false;
            }
            
            });
        });
        SortedList<Product> sortedProduct = new SortedList<>(filteredProduct);
        
        sortedProduct.comparatorProperty().bind(tableProduct.comparatorProperty());
       // ObservableList<Product> p=FXCollections.observableArrayList(sortedProduct);
         
        tableProduct.setItems(sortedProduct);
        
    }   
        
    public void afficheraddproduct(){
        
      try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddProduct.fxml"));
            Parent third = loader.load();
            Scene s = new Scene(third);
            s.setFill(Color.TRANSPARENT);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setOpacity(0.95);
            stage.setTitle("Add Product");
            stage.initOwner(rootPane.getScene().getWindow());
            stage.setScene(s);

            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
    public void gotoorderlist() throws IOException{
            AnchorPane pane=FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Shop/Order.fxml"));
            rootPane.getChildren().setAll(pane);
    }
    
  
    
    public void refreshtable() throws IOException{
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Shop/Shop.fxml"));
            rootPane.getChildren().setAll(pane);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherProductsTableView();
        
    }    
    
}
