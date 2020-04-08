/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tunisiagottalent.Entity.User;
import tunisiagottalent.services.UserServices;

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class SignupController implements Initializable {

    @FXML
    private JFXButton btn_close;
    @FXML
    private AnchorPane signup_anchor;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton btn_signup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) signup_anchor.getScene().getWindow();
        stage.close();
    }
    @FXML
    void signup(ActionEvent event) throws IOException{
        String mail = email.getText();
        String user = username.getText();
        String pwd = password.getText();
        if(mail.equals("") || user.equals("") || pwd.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Some fields are stille empty!\nPlease verify your credentials.");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/tunisiagottalent/ui/img/icon.png"));
//          stage.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        }
        else{
            User u = new User(user,mail,pwd);
            UserServices us = new UserServices();
            if(us.signup(u)){
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Success");
                 alert.setHeaderText(null);
                 alert.setContentText("Account created successfully!\nProceed to the login tab.");
                 Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                 stage.getIcons().add(new Image("/tunisiagottalent/ui/img/icon.png"));

                 alert.showAndWait();
                 stage.close();
            }
            else{
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText("Username already taken.\nPlease choose another username.");
                 Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                 stage.getIcons().add(new Image("/tunisiagottalent/ui/img/icon.png"));

                 alert.showAndWait();
            }
        }
        
    }
}
