package tunisiagottalent.UI.Base;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tunisiagottalent.Entity.Cart;

import tunisiagottalent.services.UserServices;
import tunisiagottalent.util.UserSession;

public class Login {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signup;

    @FXML
    private Button login;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private StackPane parentContainer;

    @FXML
    private AnchorPane login_anchor;
    @FXML
    private JFXButton btn_close;

    @FXML
    void close(ActionEvent event) {
        Stage stage = (Stage) login_anchor.getScene().getWindow();
        stage.close();
    }

    @FXML
    void login(ActionEvent event) throws IOException {

        String user = username.getText();
        String pwd = password.getText();

//          System.out.println(user);
//          System.out.println(pwd);
        if (user.equals("") || pwd.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Some fields are stille empty!\nPlease verify your credentials.");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/tunisiagottalent/UI/img/icon.png"));
//          stage.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        } else {
            UserServices u = new UserServices();
            switch (u.login(user, pwd)) {
                //Username and password correct

                case 1: {

                    UserServices us = new UserServices();
                    UserSession z = UserSession.getInstance(us.getUser(user));
                    
                    Cart ca=Cart.getInstance();
                    Stage stage = (Stage) login_anchor.getScene().getWindow();

                    Scene s = stage.getOwner().getScene();
                    
                    JFXButton l = (JFXButton) s.lookup("#btn_login");
                    JFXButton b = (JFXButton) s.lookup("#btn_signup");
                    JFXButton b2 = (JFXButton) s.lookup("#btn_logout");
                    JFXButton d = (JFXButton) s.lookup("#btn_dashboard");
                    JFXHamburger j = (JFXHamburger) s.lookup("#hamburger");
                    JFXDrawer e = (JFXDrawer) s.lookup("#drawer");
                    HBox search=(HBox)s.lookup("#hboxsearch");
                    AnchorPane p=(AnchorPane) stage.getOwner().getScene().getRoot();
                    HBox content=(HBox)s.lookup("#content");
                    Label log=(Label)s.lookup("#loggedin");
                    log.setText("Logged In As: "+ UserSession.instance.getU().getUsername());
                    p.setStyle("-fx-background-image: url('/tunisiagottalent/UI/Base/img/bg-3.jpg')");
                    e.open();
                    j.setDisable(false);
                    search.setVisible(true);
                    b.setVisible(false);
                    b2.setVisible(true);
                    l.setVisible(false);
                    if (z.getU().getRole().contains("ROLE_ADMIN")) {
                        d.setVisible(true);
                    }
                    stage.close();
                    try {
                AnchorPane p1 = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
                content.getChildren().setAll(p1);

            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
                    break;
                }
                //Username not found
                case -1: {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Username not found\nPlease verify your username.");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image("/tunisiagottalent/UI/Base/img/icon.png"));
//                    stage.initStyle(StageStyle.UNDECORATED);
                    alert.showAndWait();
                    break;
                }
                //Wrong password
                case 0: {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Password is incorrect\nPlease verify your password.");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image("/tunisiagottalent/UI/Base/img/icon.png"));

//                    stage.initStyle(StageStyle.UNDECORATED);
                    alert.showAndWait();
                    break;
                }
                default:
                    break;
            }
        }

    }
    @FXML
    void forgotPass(ActionEvent event) {
    try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/User/forgotPassword.fxml"));
            login_anchor.getScene().setFill(Color.TRANSPARENT);
            login_anchor.getScene().setRoot(p);
          
            
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    void initialize() {

    }
}
