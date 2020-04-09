/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisigottalentViews;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tunisiagottalentEntities.Review;
import tunisiagottalentServices.ReviewService;

/**
 * FXML Controller class
 *
 * @author sarah
 */
public class AddReviewController implements Initializable {

    @FXML
    private TextField contenu;
    @FXML
    private Button send;
    @FXML
    private Button cancel;
    @FXML
    private ChoiceBox<?> category;
    @FXML
    private Slider rating;
    @FXML
    private TextField title;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddReview(ActionEvent event) throws IOException {
      String t= title.getText();
         String con= contenu.getText();
         String cat= category.getTypeSelector();
         int rate= 2;
         ReviewService rs = new ReviewService();
         Review r = new Review(cat,rate,con);
         rs.insertReviewPST(r);        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText("Complaint added with succes");
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("DashboardReview.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
       
    }

    @FXML
    private void cancelButton(ActionEvent event) throws IOException {
       javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("DashboardComplaint.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }
    
}
