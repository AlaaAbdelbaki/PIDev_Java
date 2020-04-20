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
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import tunisiagottalent.Entity.User;
import tunisiagottalent.UI.User.ProfileController;

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class User_detailsController implements Initializable {

   
    
      @FXML
    private AnchorPane root;
    private User U;
    @FXML
    private ImageView profilepic;
    @FXML
    private Label username;
    @FXML
    private Label mail;
    @FXML
    private Label bio;
    @FXML
    private Label admin;
    @FXML
    private Label talented;
    @FXML
    private Label normal;

    public void setU(User U) {
        this.U = U;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(()->{
            
             Rectangle clip = new Rectangle(
                250, 250
        );
        clip.setArcWidth(250);
        clip.setArcHeight(250);
        profilepic.setEffect(new DropShadow(20, Color.BLACK));
        profilepic.setClip(clip);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        username.setText(U.getUsername());
        profilepic.setImage(new Image("http://127.0.0.1:8000/assets/uploads/" + U.getProfilePic()));
           mail.setText(U.getEmail());
           bio.setText(U.getBio());
           if(U.getRole().contains("ROLE_TALENTED"))talented.setVisible(true);
           else if (U.getRole().contains("ROLE_ADMIN"))admin.setVisible(true);
           else normal.setVisible(true);
        
        
        });
      
    }    

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
    
}
