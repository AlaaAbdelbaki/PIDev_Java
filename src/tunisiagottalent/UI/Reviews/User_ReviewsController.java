/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Reviews;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tunisiagottalent.Entity.Review;
import tunisiagottalent.UI.Base.MainController;
import tunisiagottalent.UI.Competitions.AdminCompetitions;
import tunisiagottalent.services.ReviewService;
import tunisiagottalent.services.UserServices;
import tunisiagottalent.util.UserSession;

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class User_ReviewsController implements Initializable {

   @FXML
    private TableView<Review> tabrev;
    @FXML
    private TableColumn<Review, Integer> id;
    ObservableList<Review> or;
    @FXML
    private TableColumn<Review, String> category;
    @FXML
    private TableColumn<Review, String> title;
    @FXML
    private TableColumn<Review, String> content;
    @FXML
    private TableColumn<Review, Integer> rating;
    @FXML
    private JFXButton delete;
    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ReviewService rs = new ReviewService();
          
         or = FXCollections.observableArrayList(rs.getbyUser(UserSession.instance.getU()));
       id.setCellValueFactory(new PropertyValueFactory<>("id"));
         category.setCellValueFactory(new PropertyValueFactory<>("category"));
        rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
         title.setCellValueFactory(new PropertyValueFactory<>("title"));
        content.setCellValueFactory(new PropertyValueFactory<>("content"));
        tabrev.setItems(or);
    }    

    @FXML
    private void Supprimer(ActionEvent event) {
          ReviewService rs = new ReviewService();
      Review  r = (Review) tabrev.getSelectionModel().getSelectedItem();
       or.remove(r);
        rs.deleteReview(r);
    }

    @FXML
    private void add(ActionEvent event) {
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Reviews/AddReview.fxml"));
            root.getChildren().setAll(p);

        } catch (IOException ex) {
            Logger.getLogger(User_ReviewsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
