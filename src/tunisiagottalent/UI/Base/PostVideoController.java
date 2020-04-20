/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Base;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import tunisiagottalent.Entity.video;
import tunisiagottalent.UI.Video.AddVideoController;
import tunisiagottalent.services.VideoServices;
import tunisiagottalent.util.UserSession;

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class PostVideoController implements Initializable {

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
       if (vs.AddVideo(v)) {
             Stage stage = (Stage) root.getScene().getWindow();
              stage.close();
             try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
            
             HBox h=(HBox) stage.getOwner().getScene().lookup("#content");
             h.getChildren().clear();
             h.getChildren().setAll(p);
            

        } catch (IOException ex) {
            Logger.getLogger(PostVideoController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
          
        }
    }

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
    
}
