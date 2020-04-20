/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Video;


import java.io.IOException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tunisiagottalent.Entity.video;
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
               
       
        
        video v = new video(url, videoTitle.getText(), new Timestamp(System.currentTimeMillis()), s.getU());
        if (vs.AddVideo(v)) {
             Stage stage = (Stage) parentContainer.getScene().getWindow();
              stage.close();
              
        
        
            System.out.println("Video added successfully !");
          
        }
        
    }
     @FXML
    public void initialize() {
        
    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) parentContainer.getScene().getWindow();
        stage.close();
    }
    
   
      
    
}
