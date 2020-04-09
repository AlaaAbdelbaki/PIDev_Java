/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.ui;

import java.io.FileWriter;
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
import tunisiagottalent.entity.User;
import tunisiagottalent.services.UserServices;
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
        us.updateToken(user.getUsername(), token);
        sendEmailSMTP.sendMail(user.getUsername(), user.getEmail(), token);
        FileWriter f = new FileWriter("info.dat");
        f.write(usernameInput.getText());
        f.close();
        fadeTransition("checkToken");
    }

    void fadeTransition(String scene) throws IOException {

        FadeTransition ft = new FadeTransition();
        ft.setDuration(Duration.millis(500));
        ft.setNode(parentContainer);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent second;
                    second = (AnchorPane) FXMLLoader.load(getClass().getResource("/tunisiagottalent/ui/" + scene + ".fxml"));
                    Scene s = new Scene(second);
                    Stage current = (Stage) parentContainer.getScene().getWindow();
                    current.setScene(s);
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        ft.play();

    }

    public void initialize() {

    }

}
