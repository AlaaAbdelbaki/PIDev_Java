/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.User;


import javafx.event.ActionEvent;

import javafx.fxml.FXML;


import javafx.scene.control.PasswordField;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import tunisiagottalent.services.UserServices;
import tunisiagottalent.util.UserSession;

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
        UserSession s = UserSession.instance;
        UserServices us = new UserServices();
        if (password.getText().equals(passwordRetype.getText())) {
            if (us.upadtePassword(s.getU().getUsername(), password.getText())) {
                Stage stage = (Stage) parentContainer.getScene().getWindow();
        stage.close();
            }
        }
    }

   
    public void initialize() {
        // TODO
    }

}
