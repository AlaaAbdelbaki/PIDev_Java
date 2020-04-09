/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import tunisiagottalent.services.UserServices;

/**
 * FXML Controller class
 *
 * @author alaa
 */
public class UpdatePasswordController {

    @FXML
    private AnchorPane parentContainer;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField passwordRetype;

    @FXML
    void changePassword(ActionEvent event) throws Exception {
        File f = new File("info.dat");
        Scanner s = new Scanner(f);
        String username = s.nextLine();
        UserServices us = new UserServices();
        if (password.getText().equals(passwordRetype.getText())) {
            if (us.upadtePassword(username, password.getText())) {
                fadeTransition("login");
            }
        }
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
                    second = (StackPane) FXMLLoader.load(getClass().getResource("/tunisiagottalent/ui/" + scene + ".fxml"));
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
        // TODO
    }

}
