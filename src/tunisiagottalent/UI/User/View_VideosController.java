/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.User;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import tunisiagottalent.Entity.User;
import tunisiagottalent.Entity.video;
import tunisiagottalent.services.VideoServices;
import tunisiagottalent.services.VoteServices;


/**
 * FXML Controller class
 *
 * @author Anis
 */
public class View_VideosController implements Initializable {

    private User user;
    VideoServices vs=new VideoServices();
    @FXML
    private VBox vboxvids;
    @FXML
    private AnchorPane root;

    public void setUser(User user) {
        this.user = user;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(()->{loadVideos();});
    }    
     void loadVideos() {

        List<video> tabs = vs.getVideos(user.getId());
        
        tabs.forEach((vid) -> {
                HBox video_grid=new HBox();
           

            WebView preview = new WebView();
            
            preview.getEngine().load(vid.getUrl());

            video_grid.getChildren().addAll(  preview);
            video_grid.setPrefHeight(250);
            video_grid.setPrefWidth(350);
             vboxvids.getChildren().addAll(video_grid);
             vboxvids.setSpacing(30);
             
             
             
        });
        
    }
     @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
}
