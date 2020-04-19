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

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Node;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;

import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

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
    ArrayList<File> all = new ArrayList<>();
    ArrayList<Event> events = es.getAll();

    
    @FXML
    private Pagination pagination;
  private final static int rowsPerPage = 3;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        pagination.setPageFactory(this::loadeventsforuser);
           
   

       

    }
public Node loadeventsforuser(int pageIndex){
        int fromIndex = pageIndex * rowsPerPage;
	int toIndex = Math.min(fromIndex + rowsPerPage, events.size());
         if(fromIndex>=toIndex) return null;
	ObservableList<Event> evts=FXCollections.observableArrayList(events.subList(fromIndex, toIndex));
       
        
        
        grid.getChildren().clear();

        grid.setHgap(20);
        grid.setVgap(20);

        int rows = evts.size() / 4 + 1;
        int cols = 4;
        int imageIndex = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (imageIndex < evts.size()) {
                    
                    addImage(evts.get(imageIndex), j, i);
                    imageIndex++;
                }
            }
        }
return grid;
}
    public void addImage(Event E, int colIndex, int rowIndex) {

        image = new Image("http://127.0.0.1:8000/assets/img/shop-img/"+E.getImg());
        pic = new ImageView();
        pic.setFitWidth(300);
        pic.setFitHeight(400);
        pic.setImage(image);
       
        
        hb.getChildren().addAll(pic);
        GridPane.setConstraints(pic, colIndex, rowIndex, 1, 1, HPos.CENTER, VPos.CENTER);
        grid.getChildren().addAll(pic);
        
        pic.setCursor(Cursor.HAND);
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
            EVC.setE(E);

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
