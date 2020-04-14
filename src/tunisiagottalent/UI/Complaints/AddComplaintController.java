/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Complaints;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import tunisiagottalent.Entity.Complaint;
import javax.mail.MessagingException;
import tunisiagottalent.services.ComplaintService;

/**
 * FXML Controller class
 *
 * @author sarah
 */
public class AddComplaintController implements Initializable {

    @FXML
    private TextField content;
    @FXML
    private Button add;
    @FXML
    private TextField subject;
    @FXML
    private Button retour;
    @FXML
    private Label text;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
public Boolean ValidateFields() {
        if (subject.getText().isEmpty() | content.getText().isEmpty() ) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate fields");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Into The Fields");
            alert.showAndWait();
            return false;
        }

        return true;

    }
    @FXML
    private void AddComplaint(ActionEvent event) throws IOException, MessagingException {
        if(ValidateFields() ){ 
        String sub= subject.getText();
         String con= content.getText(); 
         ComplaintService sc = new ComplaintService();
         Complaint c = new Complaint(sub,con);
           sc.insertComplaintPST(c);       
         Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText("Complaint added with succes .It will be processed and we will reply as soon as possible. ");
        alert.showAndWait();
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("AfficheComplaint.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();}
       
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
           javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void AddComplaint(javafx.scene.input.MouseEvent event) {
    }

    
 }
   
   
  
    
   
 
   
