/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Shop;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import tunisiagottalent.Entity.Order;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tunisiagottalent.services.OrderServices;

/**
 * FXML Controller class
 *
 * @author paspo
 */
public class EditOrderController implements Initializable {

    @FXML
    private DatePicker neworderdate;
    @FXML
    private TextField newordertotal;
    @FXML
    private TextField neworderaddress;
    
    @FXML
    private Label neworderid;
    @FXML
    private Label labelsuccess;
    @FXML
    private Button confirmeditbutton;
    @FXML
    private AnchorPane rootpane;
    
    
    public void modifyorderbutton(Order o){
        LocalDate orderdate=o.getOrder_date().toLocalDate();
        neworderdate.setValue(orderdate);
        newordertotal.setText(Double.toString(o.getTotal()));
        neworderaddress.setText(o.getAddress());
        neworderid.setText(Integer.toString(o.getId()));
    }
    
    @FXML
    public void confirmmodifybutton(){
        try{
            Double d = Double.parseDouble(newordertotal.getText());
        }
        catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Enter a valide total");
            alert.showAndWait();
        }
        
        
        if(neworderdate.getValue().equals(null) || newordertotal.getText().trim().isEmpty() || neworderaddress.getText().trim().isEmpty() ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please fill all fields");
            alert.showAndWait();
        }
        else{
            LocalDate localorderdate=neworderdate.getValue();
            Date date=java.sql.Date.valueOf(localorderdate);
            double total=Double.parseDouble(newordertotal.getText());
            String address=neworderaddress.getText();
            int id=Integer.parseInt(neworderid.getText());
            Order o =new Order((java.sql.Date) date,total,address);
            OrderServices os= new OrderServices();
            os.modifyOrder(o, id);
            labelsuccess.setText("Order has been modified !!");
            Stage stage = (Stage) rootpane.getScene().getWindow();
             try {
                Scene p1 = (Scene) stage.getOwner().getScene();
                HBox cAnchorPane = (HBox) p1.lookup("#content");
                AnchorPane p2 = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Shop/Order.fxml"));
                cAnchorPane.getChildren().setAll(p2);

            } catch (IOException ex) {
                Logger.getLogger(EditOrderController.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        stage.close();
        }
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        neworderdate.getEditor().setDisable(true);
    }    

    @FXML
    private void close(ActionEvent event) {
         Stage stage = (Stage) rootpane.getScene().getWindow();
        stage.close();
    }
    
}
