/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisigottalentViews;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tunisiagottalentEntities.Review;
import tunisiagottalentServices.ReviewService;

/**
 * FXML Controller class
 *
 * @author sarah
 */
public class DashboardReviewController implements Initializable {

    @FXML
    private TableView<Review> tabrev;
    @FXML
    private TableColumn<Review, Integer> id;
    @FXML
    private TableColumn<Review, String> username;
    @FXML
    private TableColumn<Review,String > category;
    @FXML
    private TableColumn<Review, String> title;
    @FXML
    private TableColumn<Review, Integer> rating;
    @FXML
    private TableColumn<Review, String> content;
    @FXML
    private Button refresh;
    @FXML
    private Button delete;
    @FXML
    private Button modifier;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   
    @FXML
    private void Actualiser(ActionEvent event) {
         ReviewService rs = new ReviewService();
        ObservableList<Review> or = FXCollections.observableArrayList(rs.getAll());
       id.setCellValueFactory(new PropertyValueFactory<>("id"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
         category.setCellValueFactory(new PropertyValueFactory<>("category"));
        rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
         title.setCellValueFactory(new PropertyValueFactory<>("title"));
        content.setCellValueFactory(new PropertyValueFactory<>("content"));
        tabrev.setItems(or);
    }

    @FXML
    private void Suppimer(ActionEvent event) {
        ReviewService cs = new ReviewService();
        Review r = (Review) tabrev.getSelectionModel().getSelectedItem();
        cs.deleteReview(r);
    }

    @FXML
    private void modifier(ActionEvent event) {
        Review r = tabrev.getSelectionModel().getSelectedItem();
         

if(r==null){
        
           System.out.println("Aucun événement séléctionné");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun événement séléctionné");

            alert.showAndWait();
     
        }else {
          try {   
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("EditReview.fxml"));
        Scene scene=new Scene(loader.load());
        

       EditRevController ec= loader.getController();
        Stage stageAff=new Stage();
        stageAff.setScene(scene);
        stageAff.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
        int as=tabrev.getSelectionModel().getSelectedItem().getId();
        String categorie = tabrev.getSelectionModel().getSelectedItem().getCategory();
        int n=tabrev.getSelectionModel().getSelectedItem().getRating();
        String content = tabrev.getSelectionModel().getSelectedItem().getContent();
        
       
        
        ec.setDAta(tabrev.getSelectionModel().getSelectedItem().getId(),
                tabrev.getSelectionModel().getSelectedItem().getCategory(),
                tabrev.getSelectionModel().getSelectedItem().getContent(),
                 tabrev.getSelectionModel().getSelectedItem().getRating());
                 
                 
       
        } catch(IOException ex)
    {
     System.out.println("eer");
}
        }
     
          
    
    }
    
}
