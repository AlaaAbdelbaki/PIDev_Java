/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Competitions;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import tunisiagottalent.Entity.video;

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class Vid_detailsController implements Initializable {
     @FXML
    private AnchorPane details_anchor;
    @FXML
    private WebView prev;
        private  video v;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
        prev.getEngine().load(v.getUrl());
        });
    }    
    public void setVideo(video v) {
        this.v=v;
    }
     @FXML
        void close(ActionEvent event) {
        Stage stage = (Stage) details_anchor.getScene().getWindow();
        stage.close();
    }
}
