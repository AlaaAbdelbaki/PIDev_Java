/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Reviews;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.controlsfx.control.Rating;
import tunisiagottalent.Entity.Review;
import tunisiagottalent.Entity.competition_participant;
import tunisiagottalent.UI.Competitions.AdminCompetitions;
import tunisiagottalent.UI.Competitions.Vid_detailsController;
import tunisiagottalent.services.ReviewService;

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class Admin_ReviewsController implements Initializable {

    @FXML
    private TableView<Review> tabrec;
    @FXML
    private TableColumn<Review, Integer> id;
    @FXML
    private TableColumn<Review, Void> username;
    @FXML
    private TableColumn<Review, String> colcat;
    @FXML
    private TableColumn<Review, String> coltitre;
    @FXML
    private TableColumn<Review, String> con;
    @FXML
    private TableColumn<Review, Void> rate;
    @FXML
    private JFXButton stat;
    @FXML
    private JFXTextField rech;
    @FXML
    private JFXButton deleteR;

    /**
     * Initializes the controller class.
     */
    ObservableList<Review> or;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReviewService rs= new ReviewService();
        or = FXCollections.observableArrayList(rs.getAll());
           id.setCellValueFactory(new PropertyValueFactory<>("id"));
        colcat.setCellValueFactory(new PropertyValueFactory<>("category"));
         coltitre.setCellValueFactory(new PropertyValueFactory<>("title"));
        
        con.setCellValueFactory(new PropertyValueFactory<>("content"));
         Callback<TableColumn<Review, Void>, TableCell<Review, Void>> cellFactory1 = (param) -> {
            final TableCell<Review, Void> cell = new TableCell<Review, Void>() {
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        int rate=getTableView().getItems().get(getIndex()).getRating();
                        Rating r=new Rating(5,rate);
                        r.setMouseTransparent(true);
                        
                        
                        setGraphic(r);
                        setText(null);
                        
                    }
                    
                };
                
            };
            
            return cell;
        };
        rate.setCellFactory(cellFactory1);
           username.setCellFactory(param -> new TableCell<Review,Void>() { 
             private JFXButton Viewvid = new JFXButton("View User");
            {   Viewvid.resize(100, 45);
                Viewvid.setStyle("-fx-text-fill: white;-fx-font-size:20px;-fx-background-color:#145C9E");
                Viewvid.setRipplerFill(javafx.scene.paint.Color.GREENYELLOW);
                Viewvid.setOnAction(event -> {
                  try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("User_details.fxml"));
            Parent third = loader.load();
            Review cp=getTableView().getItems().get(getIndex());
            User_detailsController controller = loader.<User_detailsController>getController();
            controller.setU(cp.getUser_id());
            Scene s = new Scene(third);
            s.setFill(Color.TRANSPARENT);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setOpacity(0.8);
            stage.setTitle("Details");
            
            stage.setScene(s);

            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdminCompetitions.class.getName()).log(Level.SEVERE, null, ex);
        }
                });
 
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                setGraphic(empty ? null : Viewvid);
            }
            
});
        
        
        FilteredList<Review> filteredrech = new FilteredList<>(or,b->true);
        
        rech.textProperty().addListener((observable,oldValue,newValue) -> {
            filteredrech.setPredicate((Predicate<? super Review>) Review -> {
            if(newValue == null || newValue.isEmpty()){
                return true;
            }
            
    String lowerCaseFilter = newValue.toLowerCase();
            if(String.valueOf(Review.getId()).indexOf(lowerCaseFilter) != -1){
                return true;
            }
            else if(Review.getCategory().toLowerCase().indexOf(lowerCaseFilter) != -1){
                return true;
            }
            else if(String.valueOf(Review.getRating()).indexOf(lowerCaseFilter) != -1){
                    return true;
            }
            else if (Review.getContent().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
            }
           
            else{
                    return false;
            }
            
            });
        });
        SortedList<Review> sortedRev = new SortedList<>(filteredrech);
        sortedRev.comparatorProperty().bind(tabrec.comparatorProperty());
        tabrec.setItems(sortedRev);
    }    

    @FXML
    private void stat(ActionEvent event) throws IOException {
       
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("stat_rating.fxml"));
            Parent third = loader.load();
          
            Scene s = new Scene(third);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setOpacity(0.9);
            stage.setTitle("Stat");
            
            stage.setScene(s);

            stage.show();
    }

   

    @FXML
    private void RemoveReview(ActionEvent event) {
        ReviewService rs = new ReviewService();
      Review  r = (Review) tabrec.getSelectionModel().getSelectedItem();
       or.remove(r);
        rs.deleteReview(r);
    }
    
}
