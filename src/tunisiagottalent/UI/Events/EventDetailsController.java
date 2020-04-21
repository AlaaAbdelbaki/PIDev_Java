/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Events;

import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tunisiagottalent.Entity.Event;
import tunisiagottalent.Entity.Ticket;
import tunisiagottalent.services.TicketService;

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class EventDetailsController implements Initializable {

    Ticket t;
    @FXML
    private Label to;
    @FXML
    private Label from;
    @FXML
    private ImageView preview;
    @FXML
    private Label available;
    @FXML
    private JFXTextArea description;
    @FXML
    private Label p;
    @FXML
    private Label title;
    @FXML
    private Label type;
    @FXML
    private Label price;
    @FXML
    private AnchorPane root;

    public void setT(Ticket t) {
        this.t = t;
    }
    Event e;
    TicketService ts =new TicketService();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(()->{
            try {
                 e=ts.findEvent(t);
            } catch (SQLException ex) {
                Logger.getLogger(EventDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            title.setText(e.getTitle());
            from.setText(e.getStart_date().toString());
            to.setText(e.getEnd_date().toString());
            type.setText(e.getType());
            preview.setImage(new Image("http://127.0.0.1:8000/assets/img/shop-img/"+e.getImg()));
            available.setText(String.valueOf(e.getNb_places()));
            description.setText(e.getDescription());
            description.setStyle("-fx-text-fill:white;");
            price.setText(String.valueOf(t.getPrice()));
            
        
        
        });
        
    }    

    @FXML
    private void close(ActionEvent event) {
         Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
    
}
