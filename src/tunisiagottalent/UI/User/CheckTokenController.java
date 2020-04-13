/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import tunisiagottalent.UI.Base.MainController;
import tunisiagottalent.services.UserServices;
import tunisiagottalent.util.UserSession;

/**
 * FXML Controller class
 *
 * @author alaa
 */
public class CheckTokenController {
 private String Token;

    public void setToken(String Token) {
        this.Token = Token;
    }
    @FXML
    private AnchorPane parentContainer;

    @FXML
    private TextField tokenInput;

    

    @FXML
    void checkToken(ActionEvent event) throws Exception {
        UserServices us = new UserServices();
        UserSession s = UserSession.instance;

        

        if (Token.equals(tokenInput.getText())) {

           try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/User/updatePassword.fxml"));
            parentContainer.getChildren().setAll(p);
          
            
        } catch (IOException ex) {
            Logger.getLogger(CheckTokenController.class.getName()).log(Level.SEVERE, null, ex);
        }

        }

    }

   
@FXML
    void cancel(ActionEvent event) {
 Stage stage = (Stage) parentContainer.getScene().getWindow();
        stage.close();
    }
    public void initialize() {
        
    }

}
