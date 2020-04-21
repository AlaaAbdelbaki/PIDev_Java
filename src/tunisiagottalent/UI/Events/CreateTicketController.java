/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Events;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tunisiagottalent.Entity.Event;
import tunisiagottalent.Entity.Ticket;
import tunisiagottalent.services.TicketService;

/**
 * FXML Controller class
 *
 * @author Anis
 */

public class CreateTicketController implements Initializable {

    private Event e;
    @FXML
    private TextField price;
    @FXML
    private ImageView prev;
    @FXML
    private Label eventname;
    @FXML
    private AnchorPane root;

    public void setE(Event e) {
        this.e = e;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(()->{
        prev.setImage(new Image("http://127.0.0.1:8000/assets/img/shop-img/"+e.getImg()));
        eventname.setText(e.getTitle());
        });
    }    

    @FXML
    private void add(ActionEvent event) {
         String price = this.price.getText();
        int event_id = e.getId();

        TicketService ts = new TicketService();
        Float p = Float.parseFloat(price);
        

        Ticket t = new Ticket(p, event_id);
        ts.addTicket(t);

        Stage stage = (Stage) root.getScene().getWindow();
       
         try {
            AnchorPane p1 = FXMLLoader.load(getClass().getResource("AddEvent.fxml"));
            
             HBox h=(HBox) stage.getOwner().getScene().lookup("#content");
             h.getChildren().clear();
             h.getChildren().setAll(p1);
            

        } catch (IOException ex) {
            Logger.getLogger(CreateTicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.close();
    }

    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
    
}
