package tunisiagottalent.UI.Base;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;

import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;

import javafx.stage.Modality;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

import tunisiagottalent.util.UserSession;

public class MainController {

    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private JFXButton btn_logout;
    @FXML
    private JFXButton btn_dashboard;
    @FXML
    private JFXButton btn_signup;

    @FXML
    public HBox content;
    @FXML
    public JFXButton btn_login;
    @FXML
    public VBox vbox;

    @FXML
    void initialize() {
        drawer.setSidePane(vbox);
        drawer.setStyle("-fx-background-color:transparent;");

        HamburgerNextArrowBasicTransition transition = new HamburgerNextArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {

            transition.setRate(transition.getRate() * -1);
            transition.play();
            if (drawer.isShown()) {
                drawer.close();
            } else {

                drawer.open();
            }

        });

        if (UserSession.instance != null) {
            btn_login.setVisible(false);
            btn_dashboard.setVisible(true);
            btn_logout.setVisible(true);
            btn_signup.setVisible(false);
        } else {
            hamburger.setDisable(true);
        }

    }

    @FXML
    void logout(ActionEvent event) {
        UserSession.instance.cleanUserSession();
        btn_login.setVisible(true);
        btn_dashboard.setVisible(false);
        btn_logout.setVisible(false);
        btn_signup.setVisible(true);
        content.getChildren().clear();
        drawer.close();
        hamburger.setDisable(true);
        System.out.println(UserSession.instance);
    }

    @FXML
    void dash(ActionEvent event) {
        try {
            Stage stage = (Stage) mainAnchor.getScene().getWindow();
            Parent p = FXMLLoader.load(getClass().getResource("AdminMain.fxml"));

            stage.setScene(new Scene(p));
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void popup_login(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent third = loader.load();
            Scene s = new Scene(third);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setOpacity(0.7);
            stage.setTitle("Login");
            stage.initOwner(mainAnchor.getScene().getWindow());
            stage.setScene(s);

            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     @FXML
    void popup_signup(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("signup.fxml"));
            Parent third = loader.load();
            Scene s = new Scene(third);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setOpacity(0.8);
            stage.setTitle("Signup");
            stage.initOwner(mainAnchor.getScene().getWindow());
            stage.setScene(s);

            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void competitions(ActionEvent event) {

        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Competitions/User_Competitions.fxml"));
            content.getChildren().setAll(p);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
