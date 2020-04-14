/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Articles;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
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
import tunisiagottalent.Entity.Article;

import tunisiagottalent.services.ServiceArticle;


/**
 * FXML Controller class
 *
 * @author ghassen
 */
public class ArticleController implements Initializable {
    @FXML
    private AnchorPane articlePane;
    @FXML
    private TextField title;
    @FXML
    private TextField img;
    @FXML
    private TextField content;
    @FXML
    private Button ValiderAction;
    @FXML
    private Button RetourAction;
    @FXML
    private Button affichage;
    @FXML
    private Button upload;
    private String imgp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnValiderAction(ActionEvent event) throws IOException {
          
   
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
        
       Article a = new Article(title.getText(),img.getText(),content.getText());
        ServiceArticle p = new ServiceArticle();
        p.ajouter(a);
        System.out.println("*************DONE**************");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Done ");
        alert.setHeaderText("Article bien ajouté  ");
        alert.showAndWait();
        test = false;
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Articles/ArticleList.fxml"));
        articlePane.getChildren().setAll(pane);
        }
        
    }

    @FXML
    private void btnAffichageAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Articles/ArticleList.fxml"));
        articlePane.getChildren().setAll(pane);
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
