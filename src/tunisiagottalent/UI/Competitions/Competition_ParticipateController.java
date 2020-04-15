package tunisiagottalent.UI.Competitions;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaView;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import sun.security.pkcs11.wrapper.Functions;
import tunisiagottalent.Entity.Competition;
import tunisiagottalent.Entity.User;
import tunisiagottalent.Entity.competition_participant;
import tunisiagottalent.Entity.video;
import tunisiagottalent.services.ParticipationServices;
import tunisiagottalent.util.UserSession;

public class Competition_ParticipateController {

    @FXML
    private AnchorPane participation;

    @FXML
    private Button btn_participate;

    @FXML
    private TextField title;

    @FXML
    private WebView  preview;

    @FXML
    private Button btn_cancel;

    @FXML
    private Label participation_date;

    @FXML
    private TextField url;
    private Competition c;
     public void setCompetition(Competition c) {
        this.c=c;
    }
    @FXML
    void initialize() {
        participation.setStyle("-fx-background-image: url('/tunisiagottalent/UI/Competitions/img/mp2.jpg')");
        participation_date.setText(new Timestamp(System.currentTimeMillis()).toString());
        
    }
    
        @FXML
    void previewVideo(MouseEvent event) {
       preview.getEngine().load(url.getText());
       preview.setVisible(true);
        
            
    }
    
    @FXML
    void participate(ActionEvent event) {
        String t=title.getText();
        String u=url.getText();
        Timestamp time= new Timestamp(System.currentTimeMillis());
        UserSession s=  UserSession.instance;
        User owner=s.getU();
        video v=new video(u, t, time, owner);
        competition_participant cp=new competition_participant(c, owner, time, v);
        ParticipationServices ps= new ParticipationServices();
        
        ps.create(cp, v);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Participation");
            alert.setHeaderText("Participation Added Succesfully");
            alert.setContentText("Go Back To The Homepage");

            alert.showAndWait();
            Stage stage = (Stage) participation.getScene().getWindow();
             stage.close();
             
       

    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) participation.getScene().getWindow();
        stage.close();

    }
   
    }


