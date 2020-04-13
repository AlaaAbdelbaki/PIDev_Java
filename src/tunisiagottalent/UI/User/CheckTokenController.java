/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.ui;

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
import tunisiagottalent.services.UserServices;
import tunisiagottalent.util.UserSession;

/**
 * FXML Controller class
 *
 * @author alaa
 */
public class CheckTokenController {

    @FXML
    private AnchorPane parentContainer;

    @FXML
    private TextField tokenInput;

    

    @FXML
    void checkToken(ActionEvent event) throws Exception {
        UserServices us = new UserServices();
        UserSession s = UserSession.instance;

        String token = us.getToken(s.getU().getUsername());

        if (token.equals(tokenInput.getText())) {

            fadeTransition("updatePassword");

        }

    }

    void fadeTransition(String scene) throws IOException {

        FadeTransition ft = new FadeTransition();
        ft.setDuration(Duration.millis(500));
        ft.setNode(parentContainer);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setOnFinished((ActionEvent event) -> {
            try {
                Parent second;
                second = (AnchorPane) FXMLLoader.load(getClass().getResource("/tunisiagottalent/ui/" + scene + ".fxml"));
                Scene s = new Scene(second);
                Stage current = (Stage) parentContainer.getScene().getWindow();
                current.setScene(s);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        ft.play();

    }

    public void initialize() {
        
    }

}
