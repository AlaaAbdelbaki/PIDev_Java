/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Shop;

import tunisiagottalent.Entity.Order;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import tunisiagottalent.services.OrderServices;

/**
 * FXML Controller class
 *
 * @author paspo
 */
public class OrderController implements Initializable {

    @FXML
    private TableView<Order> tableOrder;
    @FXML
    private TableColumn<Order,Integer> table_id;
    @FXML
    private TableColumn<Order,Date> table_orderdate;
    @FXML
    private TableColumn<Order,Double> table_total;
    @FXML
    private TableColumn<Order,String> table_address;
    @FXML
    private TableColumn table_edit;
    @FXML
    private TableColumn table_delete;
    @FXML
    private TextField search_order;
    private ObservableList<Order> order_list;
    @FXML
    AnchorPane rootPane;
    
        public void afficherOrdersTableView() {
        
        OrderServices os = new OrderServices();
        
        order_list = FXCollections.observableArrayList(os.getAll());
        
        table_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        table_orderdate.setCellValueFactory(new PropertyValueFactory<>("Order_date"));
        table_total.setCellValueFactory(new PropertyValueFactory<>("Total"));
        table_address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        
        

        Callback<TableColumn<Order, String>, TableCell<Order, String>> cellFactory1 = (param) -> {
            final TableCell<Order, String> cell = new TableCell<Order, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button editbutton = new Button("Edit");
                        editbutton.setOnAction(event ->{
                            Order oa = getTableView().getItems().get(getIndex());
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/tunisiagottalent/UI/Shop/EditOrder.fxml"));
                                Parent second = loader.load();
                                
                                EditOrderController secondcontroller = loader.getController();
                                secondcontroller.modifyorderbutton(oa);
                                
                                Scene s = new Scene(second);
                                Stage stageedit = new Stage();
                                stageedit.setTitle("Edit Order n° : "+oa.getId());
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
        
        table_edit.setCellFactory(cellFactory1);
        
        FilteredList<Order> filteredProduct = new FilteredList<>(order_list,b->true);
        
        search_order.textProperty().addListener((observable,oldValue,newValue) -> {
            filteredProduct.setPredicate((Predicate<? super Order>) Order -> {
            if(newValue == null || newValue.isEmpty()){
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if(String.valueOf(Order.getId()).indexOf(lowerCaseFilter) != -1){
                return true;
            }
            else if(Order.getAddress().toLowerCase().indexOf(lowerCaseFilter) != -1){
                return true;
            }
            else if(String.valueOf(Order.getTotal()).indexOf(lowerCaseFilter) != -1){
                    return true;
            }
            else if(String.valueOf(Order.getOrder_date()).indexOf(lowerCaseFilter) != -1){
                return true;
            }
            else{
                    return false;
            }
            
            });
        });
        SortedList<Order> sortedProduct = new SortedList<>(filteredProduct);
        sortedProduct.comparatorProperty().bind(tableOrder.comparatorProperty());
        
        tableOrder.setItems(sortedProduct);
        
    }
    
    
    
        public void gotoproductlist() throws IOException{
            AnchorPane pane=FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Shop/Shop.fxml"));
            rootPane.getChildren().setAll(pane);
    }
     
    
    public void refreshtable() throws IOException{
            AnchorPane pane=FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Shop/Order.fxml"));
            rootPane.getChildren().setAll(pane);
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       afficherOrdersTableView();
    }    
    
}
