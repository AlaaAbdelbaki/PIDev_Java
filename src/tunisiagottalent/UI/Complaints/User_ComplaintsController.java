/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Complaints;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import tunisiagottalent.Entity.Complaint;
import tunisiagottalent.services.ComplaintService;
import tunisiagottalent.util.UserSession;

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class User_ComplaintsController implements Initializable, MapComponentInitializedListener {

    @FXML
    private AnchorPane root;
    @FXML
    private GoogleMapView googlemapview;
    @FXML
    private JFXTextField subject;
    @FXML
    private JFXTextArea content;
    @FXML
    private JFXButton send;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        googlemapview.addMapInializedListener((MapComponentInitializedListener) this);
        subject.setStyle("-fx-text-fill: white;-fx-prompt-text-fill: Green;");
        content.setStyle("-fx-text-fill: white;-fx-prompt-text-fill: Black;");
    }

    
    public Boolean ValidateFields() {
        if (subject.getText().isEmpty() | content.getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate fields");
            alert.setHeaderText(null);
            alert.setContentText("Please Fill The Fields");
            alert.showAndWait();
            return false;
        }

        return true;

    }
    @FXML
    private void SendComplaint(ActionEvent event) throws IOException, MessagingException {
     if(ValidateFields() ){ 
        String sub= subject.getText();
         String con= content.getText(); 
         ComplaintService sc = new ComplaintService();
         Complaint c = new Complaint(sub,con,UserSession.instance.getU());
           sc.insertComplaintPST(c);       
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText("Complaint added with success .It will be processed and we will reply as soon as possible. ");
        alert.showAndWait();
        subject.setText(null);
        content.setText(null);
        }}
    @Override
    public void mapInitialized() {
        LatLong Esprit = new LatLong(36.899278, 10.18964);

        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(Esprit)
                .overviewMapControl(true)
                .panControl(false)
                .rotateControl(true)
                .scaleControl(false)
                .streetViewControl(true)
                .zoomControl(true)
                .zoom(10).mapType(MapTypeIdEnum.ROADMAP);

        GoogleMap map = googlemapview.createMap(mapOptions);

        //Add markers to the map
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(Esprit);

        Marker EspritMarker = new Marker(markerOptions);
        EspritMarker.setTitle(" Esprit");

        map.addMarker(EspritMarker);

    }

    @FXML
    private void facebook(MouseEvent event) {
        try {
            Desktop.getDesktop().browse(new URL("https://www.facebook.com/ReformedAF").toURI());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void website(MouseEvent event) {
         try {
            Desktop.getDesktop().browse(new URL("http://127.0.0.1:8000/").toURI());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void github(MouseEvent event) {
         try {
            Desktop.getDesktop().browse(new URL("https://github.com/Anis-Bouaziz").toURI());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void linkedin(MouseEvent event) {
         try {
            Desktop.getDesktop().browse(new URL("https://www.linkedin.com/in/mohamedanis-bouaziz/").toURI());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
