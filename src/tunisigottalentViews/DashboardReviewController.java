/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisigottalentViews;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    
}
