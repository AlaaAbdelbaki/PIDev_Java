/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisigottalentViews;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tunisiagottalentEntities.Review;
import tunisiagottalentServices.ReviewService;

/**
 * FXML Controller class
 *
 * @author sarah
 */
public class EditRevController implements Initializable {

    @FXML
    private TextField contenu;
    @FXML
    private Button send;
    @FXML
    private Button cancel;
    @FXML
    private ChoiceBox<String> category;
    @FXML
    private Slider rating;
    @FXML
    private TextField title;
    @FXML
    private TextField rate;
Review r;
Review rv=new Review();
ObservableList<String> options=FXCollections.observableArrayList("Event","Orders","Competitions","Blog");
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        category.setItems(options);
        Platform.runLater(()->{
            
            System.out.println(r);
            
               contenu.setText(r.getContent());
              rating.setValue(r.getRating());
               category.setValue(r.getCategory());
               
               ;
               
         
    });    
    }    

    @FXML
    private void save(ActionEvent event) throws IOException {
           if (rv == null) {

            System.out.println("choisir un evenement");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Modify Review");
            alert.setHeaderText(null);
            alert.setContentText("your review is not modified !");

            alert.showAndWait();
        }else {
           
             rv.setContent(contenu.getText());
             rv.setRating((int) rating.getValue());
             rv.setCategory(category.getValue());
             ReviewService es = new ReviewService();
             try{
             es.EditReview(r);
             System.out.println("ok");}
             catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
            System.out.println("Modification terminé");}
             
           
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification terminée avec succès.");
        alert.setHeaderText(null);
    alert.setContentText("Your review  has been modified.");
        alert.showAndWait();
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("DashboardReview.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
      
    }
     void setDAta(int id, String cat, String content, int rate) {
       rv.setId(id);
       category.setValue(cat);
       contenu.setText(content);
       rating.setValue(rate);
    
    
      
    }
    

    @FXML
    private void retour(MouseEvent event) {
    }

    @FXML
    private void Return(ActionEvent event) {
    }
    
}
