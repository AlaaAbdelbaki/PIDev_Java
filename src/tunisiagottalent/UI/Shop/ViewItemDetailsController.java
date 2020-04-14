/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Shop;

import tunisiagottalent.Entity.Product;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class ViewItemDetailsController implements Initializable {
@FXML
    private AnchorPane rootpane ;
    @FXML
    private Label product_name ;
    @FXML
    private Label price;
    @FXML
    private Label stock;
    @FXML
    private ImageView product_image_view;
    
    
    public void getitemdetailsfromproductview(Product p){
        product_name.setText(p.getProduct_name());
        price.setText(Double.toString(p.getPrice()));
        stock.setText(Integer.toString(p.getStock()));
        product_image_view.setImage(new Image("http://127.0.0.1:8000/assets/img/shop-img/"+p.getImg()));
    }
    @FXML
    void cancel(ActionEvent event) {
Stage stage = (Stage) rootpane.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}
