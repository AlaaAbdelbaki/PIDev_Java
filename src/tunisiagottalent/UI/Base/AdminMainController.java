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
    }

    @FXML
    private void logout(ActionEvent event) {
        UserSession.instance.cleanUserSession();
         try {
            Stage stage = (Stage) AdminMainAnchor.getScene().getWindow();
            Parent p = FXMLLoader.load(getClass().getResource("Main.fxml"));

            stage.setScene(new Scene(p));
        } catch (IOException ex) {
            Logger.getLogger(AdminMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goToMain(MouseEvent event) {
         try {
            Stage stage = (Stage) AdminMainAnchor.getScene().getWindow();
            Parent p = FXMLLoader.load(getClass().getResource("Main.fxml"));

            stage.setScene(new Scene(p));
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
            AnchorPane p = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Complaints/adminDashboard.fxml"));
            content.getChildren().setAll(p);
            drawer.close();
            
            //mainAnchor.setStyle("-fx-background-image: url('/tunisiagottalent/UI/Competitions/img/bg-9.jpg')");
            
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
