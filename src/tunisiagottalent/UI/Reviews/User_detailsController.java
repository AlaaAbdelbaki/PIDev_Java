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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tunisiagottalent.Entity.User;
import tunisiagottalent.UI.Base.MainController;
import tunisiagottalent.UI.User.ProfileController;

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class User_detailsController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private AnchorPane content;
      @FXML
    private AnchorPane root;
    private User U;

    public void setU(User U) {
        this.U = U;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(()->{
         try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/tunisiagottalent/UI/User/profile.fxml"));
                    Parent root = loader.load();
                    ProfileController controller = loader.<ProfileController>getController();
                    controller.setUser(U);
                    
                    content.getChildren().setAll((AnchorPane)root);
                  
                } catch (IOException ex) {
                    Logger.getLogger(User_detailsController.class.getName()).log(Level.SEVERE, null, ex);
                }});
      
    }    

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
    
}
