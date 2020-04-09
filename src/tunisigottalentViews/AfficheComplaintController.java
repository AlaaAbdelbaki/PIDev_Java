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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tunisiagottalentEntities.Complaint;
import tunisiagottalentServices.ComplaintService;

/**
 * FXML Controller class
 *
 * @author sarah
 */
public class AfficheComplaintController implements Initializable {

    @FXML
    private TableView<Complaint> tabrec;
    @FXML
    private Button remove;
    @FXML
    private Button modifier;
    @FXML
    private Button afficher;
    @FXML
    private TableColumn<?, ?> colsub;
    @FXML
    private TableColumn<?, ?> colcontent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Show();
        // TODO
    }    

    @FXML
    private void Delete(ActionEvent event) {
         ComplaintService cs = new ComplaintService();
        Complaint c = (Complaint) tabrec.getSelectionModel().getSelectedItem();
        cs.deleteComplaint(c);
      
    }
  @FXML
    private void Show() {
        ComplaintService cs = new ComplaintService();
        ObservableList<Complaint> oc = FXCollections.observableArrayList(cs.getAll());
       colsub.setCellValueFactory(new PropertyValueFactory<>("Subject"));
        colcontent.setCellValueFactory(new PropertyValueFactory<>("content"));
        
        tabrec.setItems(oc);
    }
    private void Show(ActionEvent event) {
        ComplaintService cs = new ComplaintService();
        ObservableList<Complaint> oc = FXCollections.observableArrayList(cs.getAll());
       colsub.setCellValueFactory(new PropertyValueFactory<>("Subject"));
        colcontent.setCellValueFactory(new PropertyValueFactory<>("content"));
        
        tabrec.setItems(oc);
    }

    @FXML
    private void modify(ActionEvent event) {
        ComplaintService cs = new ComplaintService();
        Complaint c = (Complaint) tabrec.getSelectionModel().getSelectedItem();   
     
          
    }

    
}
