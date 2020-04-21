/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Articles;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import tunisiagottalent.Entity.Article;
import tunisiagottalent.UI.Shop.AddProductController;

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
    private TextField img;
    @FXML
    private JFXTextArea content;
    @FXML
    private Button ValiderAction;
    @FXML
    private Button upload;
      URI  imguriUri;
    private String imgp;
    @FXML
    private ImageView preview;
    @FXML
    private JFXTextField imgurl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        content.setStyle("-fx-text-fill:white;");
        title.setStyle("-fx-text-fill:white;");
        imgurl.setStyle("-fx-text-fill:white;");
        
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
        
        if (imgurl.getText().equals("")) {

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
        
       Article a = new Article(title.getText(),imgurl.getText(),content.getText());
        ServiceArticle p = new ServiceArticle();
        p.ajouter(a);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Done ");
        alert.setHeaderText("Blog Addded  ");
        alert.showAndWait();
        test = false;
        try {
              
                Files.copy(Paths.get(imguriUri), Paths.get("D:\\Projects\\PIDev\\web\\assets\\img\\shop-img\\"+imgp),StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
         Stage stage = (Stage) articlePane.getScene().getWindow();
       try {
                Scene p1=(Scene) stage.getOwner().getScene();
                HBox cAnchorPane=(HBox)p1.lookup("#content");
            AnchorPane p2 = FXMLLoader.load(getClass().getResource("ArticleList.fxml"));
            cAnchorPane.getChildren().setAll(p2);
            
           
            
        } catch (IOException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
            stage.close();
        }
        
    }

    @FXML
   public void uploadimage(ActionEvent event) {
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*png");

        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(imageFilter);
        Window stage = null;
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {

            imgp = file.getName();
          imguriUri=file.toURI();
            imgurl.setText(imgp);
            preview.setImage(new Image(file.toURI().toString()));
           
        }

    }
     @FXML
    void close(ActionEvent event) {
        Stage stage = (Stage) articlePane.getScene().getWindow();
        stage.close();
    }

}
