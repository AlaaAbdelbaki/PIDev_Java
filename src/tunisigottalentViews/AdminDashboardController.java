/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisigottalentViews;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tunisiagottalentEntities.Complaint;
import tunisiagottalentEntities.Review;
import tunisiagottalentServices.ComplaintService;
import tunisiagottalentServices.ReviewService;

/**
 * FXML Controller class
 *
 * @author sarah
 */
public class AdminDashboardController implements Initializable {

      @FXML
    private TabPane tab1;

    @FXML
    private TableView<Complaint> tab;

    
    @FXML
    private TableColumn<Complaint, Integer> colid;
    
    @FXML
    private TableColumn<Complaint,String > colsubj;
    
    @FXML
    private TableColumn<Complaint, String> colcont;

    @FXML
    private Button delete;

    @FXML
    private Button mail;

    @FXML
    private Button Refresh;

    @FXML
    private TextField search;

    @FXML
    private TableView<Review> tabrec;
    
 
    
   @FXML
    private TableColumn<Review, String> colcat;
   
    @FXML
    private TableColumn<Review, String> coltitre;
    
    @FXML
    private TableColumn<Review, Integer> rate;
    
    @FXML
    private TableColumn<Review,String> con;
    

    @FXML
    private TextField rech;

    @FXML
    private Button ActualR;

    @FXML
    private Button deleteR;

    @FXML
    private Button stat;


    private ObservableList<Complaint> search_list;
    @FXML
    private TableColumn<Review, Integer> id;
    @FXML
    private TableColumn<Review, String> username;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
       actualiserRev();
        // TODO
    }    
    public void afficher(){
         ComplaintService cs = new ComplaintService();
        ObservableList<Complaint> oc = FXCollections.observableArrayList(cs.getAll());
        //username.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
       colsubj.setCellValueFactory(new PropertyValueFactory<>("Subject"));
        colcont.setCellValueFactory(new PropertyValueFactory<>("content"));
        tab.setItems(oc);
       
    }
    @FXML
      public void actualiserRev() {
         ReviewService rs= new ReviewService();
        ObservableList<Review> or = FXCollections.observableArrayList(rs.getAll());

            id.setCellValueFactory(new PropertyValueFactory<>("id"));
         username.setCellValueFactory(new PropertyValueFactory<>("username"));
        colcat.setCellValueFactory(new PropertyValueFactory<>("category"));
         coltitre.setCellValueFactory(new PropertyValueFactory<>("title"));
        rate.setCellValueFactory(new PropertyValueFactory<>("rating"));
        con.setCellValueFactory(new PropertyValueFactory<>("content"));
        tabrec.setItems(or);
        
    }

    @FXML
    private void Remove(ActionEvent event)  {
         ComplaintService cs = new ComplaintService();
        Complaint c = (Complaint) tab.getSelectionModel().getSelectedItem();
        cs.deleteComplaint(c);
        
    }

    @FXML
    private void actualiser(ActionEvent event)  {
          ComplaintService cs = new ComplaintService();
        ObservableList<Complaint> oc = FXCollections.observableArrayList(cs.getAll());
       //coluser.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
       colsubj.setCellValueFactory(new PropertyValueFactory<>("Subject"));
        colcont.setCellValueFactory(new PropertyValueFactory<>("content"));
        tab.setItems(oc);
                }
    public void actualiserRev(ActionEvent event)  {
         ReviewService rs= new ReviewService();
        ObservableList<Review> or = FXCollections.observableArrayList(rs.getAll());

           id.setCellValueFactory(new PropertyValueFactory<>("id"));
         username.setCellValueFactory(new PropertyValueFactory<>("username"));
        colcat.setCellValueFactory(new PropertyValueFactory<>("category"));
         coltitre.setCellValueFactory(new PropertyValueFactory<>("title"));
        rate.setCellValueFactory(new PropertyValueFactory<>("rating"));
        con.setCellValueFactory(new PropertyValueFactory<>("content"));
        tabrec.setItems(or);
       
    }

    @FXML
    private void RemoveReview(ActionEvent event) {
          ReviewService rs = new ReviewService();
      Review  r = (Review) tabrec.getSelectionModel().getSelectedItem();
        rs.deleteReview(r);
      
    }

    @FXML
    private void SearchSubject(ActionEvent event)  {
        ComplaintService cs = new ComplaintService();
        ObservableList<Complaint> oc = FXCollections.observableArrayList(cs.getAll());
         //coluser.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
       colsubj.setCellValueFactory(new PropertyValueFactory<>("Subject"));
        colcont.setCellValueFactory(new PropertyValueFactory<>("content"));
        FilteredList<Complaint> filteredSearch = new FilteredList<>(oc,b->true);
             search.textProperty().addListener((observable,oldValue,newValue) -> {
            filteredSearch.setPredicate((Predicate<? super Complaint>) Complaint -> {
            if(newValue == null || newValue.isEmpty()){
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if(String.valueOf(Complaint.getId()).indexOf(lowerCaseFilter) != -1){
                return true;
            }
            else if(Complaint.getSubject().toLowerCase().indexOf(lowerCaseFilter) != -1){
                return true;
            }
            else if(String.valueOf(Complaint.getId()).indexOf(lowerCaseFilter) != -1){
                    return true;
            }
            else if (Complaint.getContent().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
            }
            else{
                    return false;
            }
            
            });
        });
        SortedList<Complaint> sortedComplaint = new SortedList<>(filteredSearch);
        sortedComplaint.comparatorProperty().bind(tab.comparatorProperty());
        tab.setItems(sortedComplaint);
        }

    @FXML
    private void SearchRating(ActionEvent event)  {
         ReviewService rs= new ReviewService();
        ObservableList<Review> or = FXCollections.observableArrayList(rs.getAll());
           id.setCellValueFactory(new PropertyValueFactory<>("id"));
         username.setCellValueFactory(new PropertyValueFactory<>("username"));
        colcat.setCellValueFactory(new PropertyValueFactory<>("category"));
         coltitre.setCellValueFactory(new PropertyValueFactory<>("title"));
        rate.setCellValueFactory(new PropertyValueFactory<>("rating"));
        con.setCellValueFactory(new PropertyValueFactory<>("content"));
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
    private void Stat(ActionEvent event) throws IOException{
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("PieChartC.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

}
