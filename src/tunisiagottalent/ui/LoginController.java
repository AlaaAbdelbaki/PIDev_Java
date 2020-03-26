package tunisiagottalent.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class LoginController{

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
        
        Parent root = FXMLLoader.load(getClass().getResource("/tunisiagottalent/ui/signup.fxml"));
        
        Scene scene = signup.getScene();
//        System.out.println(scene);
        root.translateXProperty().set(-scene.getWidth());
        parentContainer.getChildren().add(root);
        
        
        Timeline timeline = new Timeline();
//        KeyValue kv = new KeyValue(root.translateXProperty(),-scene.getWidth(), Interpolator.EASE_IN);
        KeyValue kv = new KeyValue(root.translateXProperty(),0, Interpolator.EASE_IN);
//        KeyValue kv2 = new KeyValue(parentContainer.translateXProperty(),scene.getWidth(), Interpolator.EASE_IN);
        
        
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
//        KeyFrame kf2 = new KeyFrame(Duration.seconds(1), kv2);
        timeline.getKeyFrames().add(kf);
//        timeline.getKeyFrames().add(kf2);
        timeline.setOnFinished(event1-> {
            parentContainer.getChildren().remove(anchorRoot);
        });
        timeline.play();
        
        
    }

    @FXML
    void initialize() {
        assert signup != null : "fx:id=\"signup\" was not injected: check your FXML file 'login.fxml'.";
        assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'login.fxml'.";
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'login.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'login.fxml'.";

    }
}
