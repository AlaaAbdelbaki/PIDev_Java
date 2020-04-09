/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisigottalentViews;
import java.io.FileNotFoundException;
import tunisiagottalentEntities.Complaint;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tunisiagottalentServices.ComplaintService;
/**
 * FXML Controller class
 *
 * @author sarah
 */
public class EditComplaintController implements Initializable {

    @FXML
    private TextField content;
    @FXML
    private Button retour;
    @FXML
    private Button add;
    @FXML
    private TextField subject;
    @FXML
    private Label text;
    Complaint c;
    Complaint ev=new Complaint();
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        Platform.runLater(()->{         
            System.out.println(c);
            
                subject.setText(c.getSubject());
               content.setText(c.getContent());
               
        });
               
          
    }    
     

    @FXML
    private void retout(ActionEvent event) throws IOException {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("AfficheComplaint.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void send(ActionEvent event) throws FileNotFoundException, IOException {
        if (ev == null) {

            System.out.println("choisir un evenement");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Modify event");
            alert.setHeaderText(null);
            alert.setContentText("the event is not modified !");

            alert.showAndWait();
        }else {
            
             ev.setSubject(subject.getText());
             ev.setContent(content.getText());
             ComplaintService es = new ComplaintService();
             try{
             es.EditComplaint(ev);
             System.out.println("ok");}
             catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
            System.out.println("Modification terminé");}
             
           
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification terminée avec succès.");
        alert.setHeaderText(null);
    alert.setContentText("Your complaint has been modified.");
        alert.showAndWait();
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("AfficheComplaint.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }
     void setData(int id, String sub, String contenu) {
       ev.setId(id);
       subject.setText(sub);
       content.setText(contenu);
       
    
    
      
    }
    }

    

