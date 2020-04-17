/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Complaints;


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
import tunisiagottalent.Entity.Complaint;
import tunisiagottalent.Entity.Review;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javax.mail.MessagingException;
import tunisiagottalent.services.ComplaintService;
import tunisiagottalent.services.ReviewService;

/**
 * FXML Controller class
 *
 * @author sarah
 */
public class AdminDashboardController  {

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
    @FXML
    private TableColumn<Complaint,Integer> usernamec;
    @FXML
    private TextArea areaText;


   /* @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
       actualiserRev();
        // TODO
    }  */  
    public void afficher(){
         ComplaintService cs = new ComplaintService();
        ObservableList<Complaint> oc = FXCollections.observableArrayList(cs.getAll());
        usernamec.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
       colsubj.setCellValueFactory(new PropertyValueFactory<>("Subject"));
        colcont.setCellValueFactory(new PropertyValueFactory<>("content"));
        tab.setItems(oc);
       
    }
   /* @FXML
      public void actualiserRev() {
         ReviewService rs= new ReviewService();
        ObservableList<Review> or = FXCollections.observableArrayList(rs.getAll());
         id.setCellValueFactory(new PropertyValueFactory<>("id"));
         username.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        colcat.setCellValueFactory(new PropertyValueFactory<>("category"));
         coltitre.setCellValueFactory(new PropertyValueFactory<>("title"));
        rate.setCellValueFactory(new PropertyValueFactory<>("rating"));
        con.setCellValueFactory(new PropertyValueFactory<>("content"));
        tabrec.setItems(or);
        
    }*/

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
       usernamec.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
       colsubj.setCellValueFactory(new PropertyValueFactory<>("Subject"));
        colcont.setCellValueFactory(new PropertyValueFactory<>("content"));
        tab.setItems(oc);
                }
   /* public void actualiserRev(ActionEvent event)  {
         ReviewService rs= new ReviewService();
        ObservableList<Review> or = FXCollections.observableArrayList(rs.getAll());
           id.setCellValueFactory(new PropertyValueFactory<>("id"));
         username.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        colcat.setCellValueFactory(new PropertyValueFactory<>("category"));
         coltitre.setCellValueFactory(new PropertyValueFactory<>("title"));
        rate.setCellValueFactory(new PropertyValueFactory<>("rating"));
        con.setCellValueFactory(new PropertyValueFactory<>("content"));
        tabrec.setItems(or);
       
    }

    @FXML
    private void RemoveReview(ActionEvent event) {
       
      
    }*/

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
            //else if(String.valueOf(Complaint.getId()).indexOf(lowerCaseFilter) != -1){
           //      return true;
           // }
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
/*
    @FXML
    private void SearchRating(ActionEvent event)  {
         
        }

    @FXML
    private void Stat(ActionEvent event) throws IOException{
        
    }*/
public Boolean ValidateFields() {
        if (areaText.getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate fields");
            alert.setHeaderText(null);
            alert.setContentText("Your field is empty");
            alert.showAndWait();
            return false;
        }

        return true;

    } 
    @FXML
    private void respond(ActionEvent event) throws MessagingException {
        Complaint c = tab.getSelectionModel().getSelectedItem();
         

if(c==null){
        
           System.out.println("Aucun événement séléctionné");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun événement séléctionné");
            alert.showAndWait();
     
        }else {
             
      
        if(ValidateFields()){
       String s=areaText.getText();
       String text=  colsubj.getText();
       MailController envoyermail= new MailController();
       envoyermail.sendMail(text,s);
      
    }
       

}}

}
