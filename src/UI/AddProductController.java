/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entity.Product;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.ProductServices;

/**
 * FXML Controller class
 *
 * @author paspo
 */
public class AddProductController implements Initializable {

    @FXML
    private TextField newproductname;
    @FXML
    private TextField newproductimage;
    @FXML
    private TextField newproductstock;
    @FXML
    private TextField newproductprice;
    @FXML
    private Button confirmeditbutton;
    @FXML
    private Label newproductlabel;
    
    public void addproductbutton(){
        String pn=newproductname.getText();
        String img=newproductimage.getText();
        int stk=Integer.parseInt(newproductstock.getText());
        double pri=Double.parseDouble(newproductprice.getText());
        Product p=new Product(pn,img,stk,pri);
        ProductServices ps=new ProductServices();
        ps.addProduct(p);
        newproductlabel.setText("Product has been added !!");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
