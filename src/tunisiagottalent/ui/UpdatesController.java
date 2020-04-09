/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import tunisiagottalent.entity.Updates;
import tunisiagottalent.services.ServiceUpdates;

/**
 * FXML Controller class
 *
 * @author 
 */
public class UpdatesController implements Initializable {

    @FXML
    private AnchorPane ContentPane;
    @FXML
    private TextField title;
    @FXML
    private TextField img;
    @FXML
    private ComboBox<String> category;
    @FXML
    private TextField content;
    @FXML
    private DatePicker datepub;
    @FXML
    private Button ValiderAction;
    @FXML
    private Button upload;
    private String imgp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        category.getItems().addAll("Events", "Competitions","Shops");
    }    

    @FXML
    private void btnValiderAction(ActionEvent event) throws IOException {
         
        Date publish_date = new Date(datepub.getValue().getYear()-1900, datepub.getValue().getMonthValue()-1, datepub.getValue().getDayOfMonth());
        boolean test = false;
        
        if (title.getText().equals("")) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText("Le champs Title est vide");
            alert.showAndWait();
            test = false;
            }
        
        if (img.getText().equals("")) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText("Le champs Image est vide");
            alert.showAndWait();
            test = false;
            }
        
        if (content.getText().equals("")) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText("Le champs Content est vide");
            alert.showAndWait();
            test = false;
            }
        
        
        else{
         /*   
        ObservableList<String> options = 
        FXCollections.observableArrayList(
        "Music",
        "Sport");
        final ComboBox comboBox = new ComboBox(options); 
        category.setItems(options);
*/
        //Updates u = new Updates(title.getText(),img.getText(),category.getSelectionModel().getSelectedItem(),publish_date,content.getText());
        
        Updates u = new Updates(title.getText(),img.getText(),category.getSelectionModel().getSelectedItem(),publish_date,content.getText());
        ServiceUpdates sp = new ServiceUpdates();
        sp.ajouter(u);
        System.out.println("*************DONE**************");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Done ");
        alert.setHeaderText("Updates bien ajouté  ");
        alert.showAndWait();
        test = false;
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/tunisiagottalent/ui/UpdatesList.fxml"));
        ContentPane.getChildren().setAll(pane);
        }
        
    }

    @FXML
    private void uploadAction(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();

            Window stage = null;
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
               
                imgp=file.toURI().toString();
                img.setText(imgp);    
            }

    }
    
}
