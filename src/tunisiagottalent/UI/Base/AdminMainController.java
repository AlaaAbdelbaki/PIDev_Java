/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Base;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import tunisiagottalent.Entity.Cart;
import tunisiagottalent.util.UserSession;

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class AdminMainController implements Initializable {

    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXButton btn_logout;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXButton btn_competitions;
    @FXML
    private VBox vbox;
    @FXML
    private AnchorPane AdminMainAnchor;
    @FXML
    private ImageView logo;
    @FXML
    private HBox content;
    @FXML
    private FontAwesomeIcon quit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        drawer.setSidePane(vbox);
        drawer.open();
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
        
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Base/AdminHomepage.fxml"));
            content.getChildren().setAll(p);
            //drawer.close();
        } catch (IOException ex) {
            Logger.getLogger(AdminMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void logout(ActionEvent event) {
        UserSession.instance.cleanUserSession();
        Cart.instance.cleanCartSession();
        try {//Stage stage = (Stage) mainAnchor.getScene().getWindow();
            Parent p = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Scene s = p.getScene();
            AdminMainAnchor.getScene().setRoot(p);
            p.translateYProperty().set(850);
           // AdminMainAnchor.getChildren().add(p);
            //stage.setScene(s);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(p.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished((e) -> {
                AdminMainAnchor.getChildren().removeAll(AdminMainAnchor);
            });
            timeline.play();

        } catch (IOException ex) {
            Logger.getLogger(AdminMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToMain(MouseEvent event) {
        try {//Stage stage = (Stage) mainAnchor.getScene().getWindow();
            Parent p = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Scene s = p.getScene();
            AdminMainAnchor.getScene().setRoot(p);
            p.translateYProperty().set(850);
           // AdminMainAnchor.getChildren().removeAll(AdminMainAnchor);
           // AdminMainAnchor.getChildren().add(p);
            //stage.setScene(s);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(p.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
            timeline.getKeyFrames().add(kf);

            timeline.setOnFinished((e) -> {
                 AdminMainAnchor.getChildren().removeAll(AdminMainAnchor);
            });
            timeline.play();

        } catch (IOException ex) {
            Logger.getLogger(AdminMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void competitions(ActionEvent event) {
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Competitions/Admin_Competitions.fxml"));
            content.getChildren().setAll(p);
            drawer.close();
        } catch (IOException ex) {
            Logger.getLogger(AdminMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void Profiles(ActionEvent event) {
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/User/DashboardUsers.fxml"));
            content.getChildren().setAll(p);
            drawer.close();
        } catch (IOException ex) {
            Logger.getLogger(AdminMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void Shop(ActionEvent event) {
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Shop/Shop.fxml"));
            content.getChildren().setAll(p);
            drawer.close();
        } catch (IOException ex) {
            Logger.getLogger(AdminMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void quit(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void Articles(ActionEvent event) {
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Articles/ArticleList.fxml"));
            content.getChildren().setAll(p);
            drawer.close();

            //mainAnchor.setStyle("-fx-background-image: url('/tunisiagottalent/UI/Competitions/img/bg-9.jpg')");
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void Updates(ActionEvent event) {
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Updates/UpdatesList.fxml"));
            content.getChildren().setAll(p);
            drawer.close();

            //mainAnchor.setStyle("-fx-background-image: url('/tunisiagottalent/UI/Competitions/img/bg-9.jpg')");
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void Complaints(ActionEvent event) {
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Complaints/Admin_Complaints.fxml"));
            content.getChildren().setAll(p);
            drawer.close();

            //mainAnchor.setStyle("-fx-background-image: url('/tunisiagottalent/UI/Competitions/img/bg-9.jpg')");
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void Events(ActionEvent event) {
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Events/AddEvent.fxml"));
            content.getChildren().setAll(p);
            drawer.close();

            //mainAnchor.setStyle("-fx-background-image: url('/tunisiagottalent/UI/Competitions/img/bg-9.jpg')");
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void Ratings(ActionEvent event) {
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Reviews/Admin_Reviews.fxml"));
            content.getChildren().setAll(p);
            drawer.close();

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AdminDash(ActionEvent event) {
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Base/AdminHomepage.fxml"));
            content.getChildren().setAll(p);
            //drawer.close();
        } catch (IOException ex) {
            Logger.getLogger(AdminMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
