/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entity.Order;
import Entity.Product;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.OrderServices;

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
    private Button confirmeditbutton;
    @FXML
    private Label neworderid;
    @FXML
    private Label labelsuccess;
    
    
    public void modifyorderbutton(Order o){
        LocalDate orderdate=o.getOrder_date().toLocalDate();
        neworderdate.setValue(orderdate);
        newordertotal.setText(Double.toString(o.getTotal()));
        neworderaddress.setText(o.getAddress());
        neworderid.setText(Integer.toString(o.getId()));
    }
    
    public void confirmmodifybutton(){
        LocalDate localorderdate=neworderdate.getValue();
        Date date=java.sql.Date.valueOf(localorderdate);
        double total=Double.parseDouble(newordertotal.getText());
        String address=neworderaddress.getText();
        int id=Integer.parseInt(neworderid.getText());
        Order o =new Order((java.sql.Date) date,total,address);
        OrderServices os= new OrderServices();
        os.modifyOrder(o, id);
        labelsuccess.setText("Product has been modified !!");
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
