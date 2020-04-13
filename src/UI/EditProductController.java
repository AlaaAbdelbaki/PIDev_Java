/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entity.Product;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import services.ProductServices;

/**
 * FXML Controller class
 *
 * @author paspo
 */
public class EditProductController implements Initializable {

    @FXML
    private TextField newproductname;
    @FXML
    private TextField newproductimage;
    @FXML
    private TextField newproductstock;
    @FXML
    private TextField newproductprice;
    @FXML
    private Label newproductid;
    @FXML
    private Label labelsuccess;
    private String imgp;
    @FXML
    private ImageView product_imageview;
    
    public void modifiyproductbutton(Product p){
        newproductname.setText(p.getProduct_name());
        newproductimage.setText(p.getImg());
        newproductstock.setText(Integer.toString(p.getStock()));
        newproductprice.setText(Double.toString(p.getPrice()));
        newproductid.setText(Integer.toString(p.getId()));
        product_imageview.setImage(new Image(p.getImg()));
        
    }
    
   public void confirmmodifybutton(){
       try{
            Integer i = Integer.parseInt(newproductstock.getText());
        }
        catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Enter a valide stock");
            alert.showAndWait();
        }
        try{
            Double d = Double.parseDouble(newproductprice.getText());
        }
        catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Enter a valide price");
            alert.showAndWait();
        }
       
       
       if(newproductname.getText().trim().isEmpty() || newproductimage.getText().trim().isEmpty() || newproductprice.getText().trim().isEmpty() || newproductstock.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please fill all fields");
            alert.showAndWait();
        }
       else{
            String pn=newproductname.getText();
            String img=newproductimage.getText();
            int stk=Integer.parseInt(newproductstock.getText());
            double pri=Double.parseDouble(newproductprice.getText());
            int pid = Integer.parseInt(newproductid.getText());
            Product pm = new Product(pn,img,stk,pri);
            ProductServices ps = new ProductServices();
            ps.modifyProduct(pm, pid);
            labelsuccess.setText("Product has been modified !!"); 
       }
            

    }
   public void uploadimage(ActionEvent event){
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files","*.jpg","*png");
        
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(imageFilter);
        Window stage = null;
        File file = fileChooser.showOpenDialog(stage);
         if (file != null) {
               
                imgp=file.toURI().toString();
                
                newproductimage.setText(imgp);
                product_imageview.setImage(new Image(imgp));
            }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        newproductimage.setDisable(true);
    }    
    
}
