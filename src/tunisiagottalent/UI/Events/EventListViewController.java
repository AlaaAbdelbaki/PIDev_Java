/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Events;

import tunisiagottalent.Entity.Event;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tunisiagottalent.services.EventService;

public class EventListViewController implements Initializable {

    EventService es = new EventService();
    
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    Image image;
    @FXML
    ImageView pic;
    @FXML
    AnchorPane rootPane;

    HBox hb = new HBox();
    ArrayList<File> all = new ArrayList<File>();
    ArrayList<Event> events = new ArrayList<Event>();

    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            events=es.getAll();
      /*  final String path = "C:\\xampp\\htdocs\\PIDev_Java\\src\\album";
        File folder = new File(path);

        for (File file : folder.listFiles()) {
            // System.out.println(file.getName());
            Event E = es.findUpcomingEvent(file.getName());

            if (E != null) {
                all.add(file);
                events.add(E);
                // System.out.println("end********");
            }
        }*/

        //gridPane and scrollPane settings:
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        grid.setHgap(20);
        grid.setVgap(20);

        int rows = events.size() / 4 + 1;
        int cols = 4;
        int imageIndex = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (imageIndex < events.size()) {
                    System.out.println(imageIndex);
                    addImage(events.get(imageIndex), j, i);
                    imageIndex++;
                }
            }
        }

    }

    public void addImage(Event E, int colIndex, int rowIndex) {

        //  String idToCut = all.get(index).getName();
        //  String id = idToCut.substring(0, (idToCut.length() - 4));
        //the id of the each img: ??? 
        image = new Image("http://127.0.0.1:8000/assets/img/shop-img/"+E.getImg());
        pic = new ImageView();
        pic.setFitWidth(200);
        pic.setFitHeight(300);
        pic.setImage(image);
        //pic.setId(idtoset);
        System.out.println(E.getImg());
        hb.getChildren().addAll(pic);
        GridPane.setConstraints(pic, colIndex, rowIndex, 1, 1, HPos.CENTER, VPos.CENTER);
        grid.getChildren().addAll(pic);
        
        
       pic.setOnMouseClicked(e -> {
            afficherFenetre(E);
            
            
        });
       
       
    }

    public void afficherFenetre(Event E) {
        try {
             
            
            //Load second scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tunisiagottalent/UI/Events/SelectedEventView.fxml"));
            Parent root = loader.load();

            //Get controller of scene2
            SelectedEventViewController EVC = loader.getController();
            //Pass whatever data you want. You can have multiple method calls here
            EVC.showDetails(E);

            //Show scene 2 in new window            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setOpacity(1);
            stage.setTitle(E.getTitle());
           // stage.initOwner(rootPane.getScene().getWindow());
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
