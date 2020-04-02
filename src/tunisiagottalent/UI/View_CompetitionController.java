/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import tunisiagottalent.Entity.Competition;
import tunisiagottalent.Entity.video;
import tunisiagottalent.services.ParticipationServices;

/**
 * FXML Controller class
 *
 * @author Anis
 */

public class View_CompetitionController {

    /**
     * Initializes the controller class.
     */
       private Competition c;
     @FXML
    private AnchorPane video_list;
     @FXML
    private GridPane video_grid;
    @FXML
    public void initialize() {
        Platform.runLater(() -> {
        ParticipationServices ps=new ParticipationServices();
        System.out.println(c);
        ObservableList<video> tabs = ps.getAll(c);
      
        tabs.forEach((vid) -> { 
            Text Title = new Text(vid.getTitle());  
            Text url=new Text(vid.getUrl());
            video_grid.add(Title,0,tabs.indexOf(vid));
            video_grid.add(url, 1, tabs.indexOf(vid));
            
                            
            
            
                });
    }  ); 
    }
    public void setCompetition(Competition c) {
        this.c=c;
    }
}
