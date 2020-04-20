/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Competitions;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import tunisiagottalent.Entity.Competition;
import tunisiagottalent.Entity.competition_participant;
import tunisiagottalent.Entity.video;
import tunisiagottalent.services.ParticipationServices;
import tunisiagottalent.services.VideoServices;
import tunisiagottalent.util.UserSession;

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class Competition_ParticipateController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private WebView preview;
    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextField urlField;
    String embededurl;
    VideoServices vs=new VideoServices();
    ParticipationServices ps =new ParticipationServices();
    Competition c;

    public void setC(Competition c) {
        this.c = c;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        title.setStyle("-fx-text-fill: white;-fx-prompt-text-fill: white;");
        urlField.setStyle("-fx-text-fill: white;-fx-prompt-text-fill: white;");
    }    

    @FXML
    private void showPreview(MouseEvent event) {
        if (!urlField.getText().isEmpty()){
        embededurl = "https://www.youtube.com/embed/";
        String code = urlField.getText().substring(urlField.getText().length() - 11);
        embededurl = embededurl + code;
        preview.getEngine().load(embededurl);
       preview.setVisible(true);}
    }

    @FXML
    private void submit(ActionEvent event) {
         video v = new video(embededurl, title.getText(), new Timestamp(System.currentTimeMillis()), UserSession.instance.getU());
         competition_participant cp=new competition_participant(c, UserSession.instance.getU(), v.getPublish_date(), v);
         ps.create(cp, v);
       
             Stage stage = (Stage) root.getScene().getWindow();
               Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Participation");
        alert.setHeaderText("Video Added Succesfully");
        alert.setContentText("Go Back To The List");
        ImageView icon=new ImageView("/tunisiagottalent/UI/Base/img/icon.png");
        icon.setFitHeight(100);
        icon.setFitWidth(100);
        alert.setGraphic(icon);
        alert.showAndWait();
        
        
         try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("User_Competitions.fxml"));
            
             HBox h=(HBox) stage.getOwner().getScene().lookup("#content");
             h.getChildren().clear();
             h.getChildren().setAll(p);
            

        } catch (IOException ex) {
            Logger.getLogger(Competition_ParticipateController.class.getName()).log(Level.SEVERE, null, ex);
        }
              stage.close();
             
          
        
    }

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
    
}
