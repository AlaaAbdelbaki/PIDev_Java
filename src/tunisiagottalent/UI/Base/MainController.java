package tunisiagottalent.UI.Base;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;

import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;

import java.io.IOException;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javafx.stage.Modality;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import tunisiagottalent.Entity.Cart;

import tunisiagottalent.UI.User.ProfileController;
import tunisiagottalent.services.UserServices;

import tunisiagottalent.util.UserSession;

public class MainController {

    @FXML
    private VBox vboxsearch;

    @FXML
    private HBox hboxsearch;
    @FXML
    private JFXTextField searchbar;

    @FXML
    private Label loggedin;
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
    private FontAwesomeIcon searchicon;
    @FXML
    public HBox content;
    @FXML
    public JFXButton btn_login;
    @FXML
    public VBox vbox;
    @FXML
    private ImageView logo;
    @FXML
    private JFXButton btn_competitions;
    @FXML
    private FontAwesomeIcon exitApp;

    @FXML
    void initialize() {

        drawer.setSidePane(vbox);
        drawer.setStyle("-fx-background-color:transparent;");
        searchbar.setStyle("-fx-prompt-text-fill: white;-fx-text-inner-color: white");

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
            hboxsearch.setVisible(true);
            loggedin.setText("Logged In As: " + UserSession.instance.getU().getUsername());

            try {
                AnchorPane p = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
                content.getChildren().setAll(p);

            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            hamburger.setDisable(true);
        }

    }

    @FXML
    void logout(ActionEvent event) {
        UserSession.instance.cleanUserSession();
        Cart.instance.cleanCartSession();
        btn_login.setVisible(true);
        btn_dashboard.setVisible(false);
        btn_logout.setVisible(false);
        btn_signup.setVisible(true);
        content.getChildren().clear();
        drawer.close();
        hamburger.setDisable(true);
        loggedin.setText("");
        hboxsearch.setVisible(false);
         content.getChildren().clear();
      
        // View_CompetitionController.oneSecond.stop();
        mainAnchor.setStyle("-fx-background-image: url('/tunisiagottalent/UI/Base/img/bg-2.jpg')");
    }

    @FXML
    void dash(ActionEvent event) {
        try {
            //Stage stage = (Stage) mainAnchor.getScene().getWindow();
            Parent p = FXMLLoader.load(getClass().getResource("AdminMain.fxml"));
            Scene s = p.getScene();
            p.translateXProperty().set(1700);

            mainAnchor.getScene().setRoot(p);

            // mainAnchor.getChildren().add(p);
            //stage.setScene(s);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(p.translateXProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished((e) -> {
                mainAnchor.getChildren().removeAll(mainAnchor);
            });
            timeline.play();
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
            third.setTranslateY(s.getHeight());
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
            s.setFill(Color.TRANSPARENT);
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
            p.translateYProperty().set(750);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(p.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
            timeline.setOnFinished((e) -> {
                mainAnchor.getChildren().remove(content);
            });
            timeline.getKeyFrames().add(kf);

            content.getChildren().setAll(p);
            drawer.close();
            timeline.play();
            mainAnchor.setStyle("-fx-background-image: url('/tunisiagottalent/UI/Competitions/img/bg-9.jpg')");

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void home(MouseEvent event) {
        content.getChildren().clear();
        
            try {
                AnchorPane p = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
                content.getChildren().setAll(p);

            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        mainAnchor.setStyle("-fx-background-image: url('/tunisiagottalent/UI/Base/img/bg-3.jpg')");
        drawer.close();

    }

    @FXML
    private void exitApp(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void Profile(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tunisiagottalent/UI/User/profile.fxml"));
            Parent root = loader.load();

            ProfileController controller = loader.<ProfileController>getController();
            controller.setUser(UserSession.instance.getU());
            root.translateYProperty().set(750);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
            timeline.setOnFinished((e) -> {
                mainAnchor.getChildren().remove(content);
            });
            timeline.getKeyFrames().add(kf);

            content.getChildren().setAll((AnchorPane) root);
            drawer.close();
            timeline.play();
            mainAnchor.setStyle("-fx-background-image: url('/tunisiagottalent/UI/Base/img/bg-11.jpg')");

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void Rating(ActionEvent event) {
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Reviews/User_Reviews.fxml"));
            p.translateYProperty().set(750);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(p.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
            timeline.setOnFinished((e) -> {
                mainAnchor.getChildren().remove(content);
            });
            timeline.getKeyFrames().add(kf);

            content.getChildren().setAll(p);
            drawer.close();
            timeline.play();
            mainAnchor.setStyle("-fx-background-image: url('/tunisiagottalent/UI/Base/img/bg-12.jpg')");

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void Complaints(ActionEvent event) {
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Complaints/User_Complaints.fxml"));
            p.translateYProperty().set(750);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(p.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
            timeline.setOnFinished((e) -> {
                mainAnchor.getChildren().remove(content);
            });
            timeline.getKeyFrames().add(kf);

            content.getChildren().setAll(p);
            drawer.close();
            timeline.play();
            // mainAnchor.setStyle("-fx-background-image: url('/tunisiagottalent/UI/Competitions/img/bg-9.jpg')");

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void Shop(ActionEvent event) {
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Shop/ShopView.fxml"));
            p.translateYProperty().set(750);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(p.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
            timeline.setOnFinished((e) -> {
                mainAnchor.getChildren().remove(content);
            });
            timeline.getKeyFrames().add(kf);

            content.getChildren().setAll(p);
            drawer.close();
            timeline.play();

            mainAnchor.setStyle("-fx-background-image: url('/tunisiagottalent/UI/Base/img/bg-10.jpg')");
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void Events(ActionEvent event) {
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Events/EventListView.fxml"));
            p.translateYProperty().set(750);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(p.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
            timeline.setOnFinished((e) -> {
                mainAnchor.getChildren().remove(content);
            });
            timeline.getKeyFrames().add(kf);

            content.getChildren().setAll(p);
            drawer.close();
            timeline.play();

            mainAnchor.setStyle("-fx-background-image: url('/tunisiagottalent/UI/Base/img/bg-1.jpg')");

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void News(ActionEvent event) {
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Updates/User_Updates.fxml"));
            p.translateYProperty().set(750);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(p.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
            timeline.setOnFinished((e) -> {
                mainAnchor.getChildren().remove(content);
            });
            timeline.getKeyFrames().add(kf);

            content.getChildren().setAll(p);
            drawer.close();
            timeline.play();

            mainAnchor.setStyle("-fx-background-image: url('/tunisiagottalent/UI/Base/img/bg-4.jpg')");
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void Articles(ActionEvent event) {
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Articles/User_Articles.fxml"));
            p.translateYProperty().set(750);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(p.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
            timeline.setOnFinished((e) -> {
                mainAnchor.getChildren().remove(content);
            });
            timeline.getKeyFrames().add(kf);

            content.getChildren().setAll(p);
            drawer.close();
            timeline.play();

            mainAnchor.setStyle("-fx-background-image: url('/tunisiagottalent/UI/Base/img/bg-13.jpg')");
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void show_search(MouseEvent event) {

        ListView<String> Searched = new ListView<>();
        UserServices us = new UserServices();
        searchbar.setOnKeyPressed((e) -> {

            FilteredList<String> filteredUser = new FilteredList<>(FXCollections.observableArrayList(us.getUsernames()), b -> true);

            searchbar.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredUser.setPredicate((Predicate<? super String>) e1 -> {
                    if (newValue == null || newValue.isEmpty()) {

                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(e1).indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else {
                        return false;
                    }

                });

            });
            SortedList<String> sortedUser = new SortedList<>(filteredUser);
            Searched.setOrientation(Orientation.VERTICAL);
            Searched.setItems(sortedUser);
            vboxsearch.getChildren().clear();
            vboxsearch.getChildren().addAll(Searched);
            vboxsearch.setVisible(true);
        });
        Searched.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue == null || newValue.isEmpty()) {
                    searchbar.setPromptText("No Result");
                } else {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tunisiagottalent/UI/User/profile.fxml"));
                        Parent root = loader.load();
                        ProfileController controller = loader.<ProfileController>getController();
                        controller.setUser(us.getUser(newValue));
                        vboxsearch.setVisible(false);
                        content.getChildren().setAll((AnchorPane) root);
                        if (drawer.isShown()) {
                            drawer.close();
                        }
                        mainAnchor.setStyle("-fx-background-image: url('/tunisiagottalent/UI/Base/img/bg-4.jpg')");
                    } catch (IOException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    @FXML
    void hide_search(MouseEvent event) {
        vboxsearch.getScene().setOnMouseClicked((e) -> {
            vboxsearch.setVisible(false);

        });

    }
}
