/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.rmi.CORBA.UtilDelegate;
import tunisiagottalent.entity.Video;
import tunisiagottalent.services.UserServices;
import tunisiagottalent.services.VideoServices;
import tunisiagottalent.util.UserSession;

/**
 * FXML Controller class
 *
 * @author alaa
 */
public class AddVideoController {
    
    @FXML
    private AnchorPane parentContainer;
    
    @FXML
    private TextField videoTitle;
    
    @FXML
    private TextField videoUrl;
    
    @FXML
    public void addVideo(ActionEvent event) {
        VideoServices vs = new VideoServices();
        UserServices us = new UserServices();
        UserSession s = UserSession.instance;
        String url = "https://www.youtube.com/embed/";
        String code = videoUrl.getText().substring(videoUrl.getText().length() - 11);
        url = url + code;
        System.out.println(url);
               
        java.time.LocalDate current = java.time.LocalDateTime.now().toLocalDate();
        
        Video v = new Video(url, videoTitle.getText(), Date.valueOf(current), s.getU().getId());
        if (vs.AddVideo(v)) {
            System.out.println("Video added successfully !");
            try {
                fadeTransition("profile");
            } catch (IOException ex) {
                Logger.getLogger(AddVideoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void initialize() {
        
    }
    
    void fadeTransition(String scene) throws IOException {
        
        FadeTransition ft = new FadeTransition();
        ft.setDuration(Duration.millis(500));
        ft.setNode(parentContainer);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setOnFinished((ActionEvent event) -> {
            try {
                Parent second;
                if (scene.equals("login")) {
                    second = (StackPane) FXMLLoader.load(getClass().getResource("/tunisiagottalent/ui/" + scene + ".fxml"));
                } else {
                    second = (AnchorPane) FXMLLoader.load(getClass().getResource("/tunisiagottalent/ui/" + scene + ".fxml"));
                }
                Scene s = new Scene(second);
                Stage current = (Stage) parentContainer.getScene().getWindow();
                current.setScene(s);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        ft.play();
        
    }
}
