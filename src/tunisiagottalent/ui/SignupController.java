package tunisiagottalent.ui;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
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
import javafx.util.Duration;
import tunisiagottalent.entity.User;
import tunisiagottalent.services.UserServices;

public class SignupController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button login;

    @FXML
    private Button signup;

    @FXML
    private TextField email;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private AnchorPane anchorRoot2;
    @FXML
    void login(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/tunisiagottalent/ui/login.fxml"));
        
        Scene scene = signup.getScene();
//        System.out.println(scene);
        root.translateXProperty().set(scene.getWidth());
        StackPane parentContainer = (StackPane)scene.getRoot();
       
        parentContainer.getChildren().add(root);
        
        
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(),0, Interpolator.EASE_IN);
//        KeyValue kv2 = new KeyValue(parentContainer.translateXProperty(),-scene.getWidth(), Interpolator.EASE_IN);
        
        
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
//        KeyFrame kf2 = new KeyFrame(Duration.seconds(1), kv2);
        timeline.getKeyFrames().add(kf);
//        timeline.getKeyFrames().add(kf2);
        
        timeline.setOnFinished(event1-> {
            parentContainer.getChildren().remove(anchorRoot2);
        });
        timeline.play();
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
//                 stage.initStyle(StageStyle.UNDECORATED);
                 alert.showAndWait();
            }
            else{
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Error");
                 alert.setHeaderText(null);
                 alert.setContentText("Username already taken.\nPlease choose another username.");
                 Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                 stage.getIcons().add(new Image("/tunisiagottalent/ui/img/icon.png"));
//                 stage.initStyle(StageStyle.UNDECORATED);
                 alert.showAndWait();
            }
        }
        
    }
    @FXML
    void initialize() {
        assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'signup.fxml'.";
        assert signup != null : "fx:id=\"signup\" was not injected: check your FXML file 'signup.fxml'.";
        assert email != null : "fx:id=\"email\" was not injected: check your FXML file 'signup.fxml'.";
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'signup.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'signup.fxml'.";

    }
}
