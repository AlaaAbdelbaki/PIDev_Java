/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisigottalentViews;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private ChoiceBox category;
    @FXML
    private Slider rating;
    @FXML
    private TextField title;
     ObservableList<String> options=FXCollections.observableArrayList("Event","Orders","Competition","Blog");
    @FXML
    private TextField rate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        category.setItems(options);
      
  
      
          rating.valueProperty().addListener((observable, oldValue, newValue) -> {

            rate.setText(Integer.toString(newValue.intValue()));


        });
    }    
public Boolean ValidateFields() {
        if (category.getValue()==null | contenu.getText().isEmpty() | rate.getText().isEmpty() ) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate fields");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Into The Fields");
            alert.showAndWait();
            return false;
        }

        return true;

    }
    @FXML
    private void AddReview(ActionEvent event) throws IOException {
      if( ValidateFields()==false){String t= title.getText();
         String con= contenu.getText();
         String cat= category.getValue().toString();
        String note= rate.getText();
        int n= Integer.parseInt(note);
         ReviewService rs = new ReviewService();
         Review r = new Review(cat,n,con);
         rs.insertReviewPST(r);}        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText("Review added with succes");
        alert.showAndWait();
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("DashboardReview.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
       
    }

    @FXML
    private void cancelButton(ActionEvent event) throws IOException {
       javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }
    
}
