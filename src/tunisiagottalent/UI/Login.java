
        package tunisiagottalent.UI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
        import java.io.FileWriter;
        import java.io.IOException;
        import java.net.URL;
        import java.util.HashSet;
        import java.util.ResourceBundle;
        import java.util.Set;
        import java.util.logging.Level;
        import java.util.logging.Logger;
        import javafx.animation.FadeTransition;
        import javafx.animation.Interpolator;
        import javafx.animation.KeyFrame;
        import javafx.animation.KeyValue;
        import javafx.animation.Timeline;
        import javafx.application.Application;
        import javafx.event.ActionEvent;
        import javafx.event.EventHandler;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Alert;
        import javafx.scene.control.Button;
        import javafx.scene.control.PasswordField;
        import javafx.scene.control.TextField;
        import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
        import javafx.scene.layout.AnchorPane;
        import javafx.scene.layout.StackPane;
        import javafx.stage.Stage;
        import javafx.stage.StageStyle;
        import javafx.util.Duration;
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
    void close(ActionEvent event){
        Stage stage=(Stage) login_anchor.getScene().getWindow();
        stage.close();
    }
/*
    @FXML
    void signup(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/signup.fxml"));

        Scene scene = signup.getScene();
//        System.out.println(scene);
        root.translateXProperty().set(-scene.getWidth());
        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
//        KeyValue kv = new KeyValue(root.translateXProperty(),-scene.getWidth(), Interpolator.EASE_IN);
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
//        KeyValue kv2 = new KeyValue(parentContainer.translateXProperty(),scene.getWidth(), Interpolator.EASE_IN);

        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
//        KeyFrame kf2 = new KeyFrame(Duration.seconds(1), kv2);
        timeline.getKeyFrames().add(kf);
//        timeline.getKeyFrames().add(kf2);
        timeline.setOnFinished(event1 -> {
            parentContainer.getChildren().remove(anchorRoot);
        });
        timeline.play();

    }

    public void fadeTransition(String scene) throws IOException {

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
                    second = (AnchorPane) FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/" + scene + ".fxml"));
                    Scene s = new Scene(second);
                    Stage current = (Stage) parentContainer.getScene().getWindow();
                    current.setScene(s);
                } catch (IOException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        ft.play();

    }
*/
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
                   UserSession z= UserSession.getInstance(us.getByUsername(user));
                    
                   
                    Stage stage=(Stage) login_anchor.getScene().getWindow();
                    
                    Scene s=stage.getOwner().getScene();
                    System.out.println(s);
                    JFXButton l=(JFXButton)s.lookup("#btn_login");
                    JFXButton b=(JFXButton)s.lookup("#btn_signup");
                    JFXButton b2=(JFXButton)s.lookup("#btn_logout");
                    JFXButton d=(JFXButton)s.lookup("#btn_dashboard");
                    JFXHamburger j= (JFXHamburger)s.lookup("#hamburger");
                    JFXDrawer e=(JFXDrawer)s.lookup("#drawer");
                    e.open();
                    
                    j.setDisable(false);
                    
                    b.setVisible(false);
                    b2.setVisible(true);
                    l.setVisible(false);
                    if (z.getU().getRole().contains("ROLE_ADMIN")){
                        d.setVisible(true);
                    }
                    stage.close();
                    break;
                }
                //Username not found
                case -1: {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Username not found\nPlease verify your username.");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image("/tunisiagottalent/UI/img/icon.png"));
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
                    stage.getIcons().add(new Image("/tunisiagottalent/UI/img/icon.png"));

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
    void initialize() {
       

    }
}

