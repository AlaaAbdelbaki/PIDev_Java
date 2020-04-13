/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.User;


import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import tunisiagottalent.Entity.User;
import tunisiagottalent.UI.Base.MainController;
import tunisiagottalent.services.UserServices;
import tunisiagottalent.util.UserSession;
import tunisiagottalent.util.sendEmailSMTP;

/**
 * FXML Controller class
 *
 * @author alaa
 */
public class ForgotPasswordController {

    @FXML 
    private AnchorPane parentContainer;
    @FXML
    private TextField usernameInput;

    @FXML
    void sendEmail(ActionEvent event) throws Exception {
        UserServices us = new UserServices();
        User user = us.getUser(usernameInput.getText());
        String token = us.tokenGenerator();
       
        sendEmailSMTP.sendMail(user.getUsername(), user.getEmail(), token);
        UserSession s = UserSession.getInstance(us.getUser(usernameInput.getText()));
         try {
              FXMLLoader loader = new FXMLLoader(getClass().getResource("/tunisiagottalent/UI/User/checkToken.fxml"));
                        Parent root = loader.load();
            
             CheckTokenController controller = loader.<CheckTokenController>getController();
                        controller.setToken(token);
            parentContainer.getChildren().setAll(root);
          
            
        } catch (IOException ex) {
            Logger.getLogger(ForgotPasswordController.class.getName()).log(Level.SEVERE, null, ex);
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
