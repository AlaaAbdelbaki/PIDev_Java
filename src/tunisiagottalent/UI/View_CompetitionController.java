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
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import tunisiagottalent.Entity.Competition;
import tunisiagottalent.Entity.video;
import tunisiagottalent.services.ParticipationServices;
import tunisiagottalent.util.UserSession;

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
            GridPane details=new GridPane();
            Text Title = new Text(vid.getTitle());  
            Text user = new Text(vid.getOwner().getUsername());  
            Text url=new Text(vid.getUrl());
            Button Edit=new Button("Edit");
            Text pubDate=new Text(vid.getPublish_date().toString());
            WebView preview=new WebView();
            preview.getEngine().load(vid.getUrl());
            UserSession s=  UserSession.instance;
            if(s.getU().getUsername().equals(vid.getOwner().getUsername())){
                details.add(Edit,1,3);}
            details.add(Title,1,0);
            details.add(user,1,2);
            details.add(pubDate,1,1);
            video_grid.add(preview,0,tabs.indexOf(vid));
            video_grid.add(details, 1, tabs.indexOf(vid));
            
                            
            
            
                });
    }  ); 
    }
    public void setCompetition(Competition c) {
        this.c=c;
    }
}
