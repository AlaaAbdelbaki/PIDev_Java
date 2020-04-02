
        package tunisiagottalent.UI;

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
    private AnchorPane anchorRoot;

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
                    UserSession.getInstance(us.getByUsername(user));
                    System.out.println(us.getByUsername(user));
                   
                   // fadeTransition("Admin_Competitions");
                    fadeTransition("User_Competitions");

                    //Sessions using usersessions class
                    //UserSession.getInstace(user,roles.add(us.getRole(user))));

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
        assert signup != null : "fx:id=\"signup\" was not injected: check your FXML file 'login.fxml'.";
        assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'login.fxml'.";
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'login.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'login.fxml'.";

    }
}

