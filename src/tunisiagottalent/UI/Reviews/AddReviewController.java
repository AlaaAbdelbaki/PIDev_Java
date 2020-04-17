/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Reviews;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import tunisiagottalent.Entity.Review;
import tunisiagottalent.UI.User.EditProfileController;
import tunisiagottalent.UI.User.ProfileController;
import tunisiagottalent.services.ReviewService;
import tunisiagottalent.util.UserSession;

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class AddReviewController implements Initializable {

    @FXML
    private TextField contenu;
    @FXML
    private Button send;
    @FXML
    private ChoiceBox<String> category;
   
   
    @FXML
    private TextField title;
     ObservableList<String> options=FXCollections.observableArrayList("Event","Orders","Competition","Blog");
    @FXML
    private Rating rating;
    @FXML
    private AnchorPane rootp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        category.setItems(options);
      
  
    }    
public Boolean ValidateFields() {
        if (category.getValue()==null | contenu.getText().isEmpty() ) {
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
      if( ValidateFields()==true){
         String con= contenu.getText();
         String cat= category.getValue();
         String tit=title.getText();
        double note= rating.getRating();
        int n= (int) note;
         ReviewService rs = new ReviewService();
         Review r = new Review(UserSession.instance.getU(),cat,n,con,tit);
         rs.insertReviewPST(r);      
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText("Review added with succes");
        alert.showAndWait();
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/tunisiagottalent/UI/Reviews/User_Reviews.fxml"));
                    Parent root = loader.load();
                    
                    rootp.getChildren().setAll((AnchorPane)root);

            } catch (IOException ex) {
                Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
      }
    }
    
  
    
}
