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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
 * @author Anis
 */
public class EditArticleController implements Initializable {
 URI  imguriUri;
    @FXML
    private AnchorPane articlePane;
    @FXML
    private Button ValiderAction;
    @FXML
    private Button upload;
    @FXML
    private JFXTextArea content;
    @FXML
    private ImageView preview;
    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextField imgurl;
    private Article a;
    private String imgp;
    public void setA(Article a) {
        this.a = a;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(()->{
            
        content.setStyle("-fx-text-fill:white;");
        title.setStyle("-fx-text-fill:white;");
        imgurl.setStyle("-fx-text-fill:white;");
        content.setText(a.getContent());
        title.setText(a.getTitle());
        preview.setImage(new Image("http://127.0.0.1:8000/assets/img/shop-img/"+a.getImg()));
        imgurl.setText(a.getImg());
        });
    }    

    @FXML
    private void btnValiderAction(ActionEvent event) {
           
   
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
            if(!(imgp.equals(a.getImg()))){
        try {
              
                Files.copy(Paths.get(imguriUri), Paths.get("D:\\Projects\\PIDev\\web\\assets\\img\\shop-img\\"+imgp));
            } catch (IOException ex) {
                Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
            }}
            a.setContent(content.getText());
            a.setImg(imgurl.getText());
            a.setTitle(title.getText());
        ServiceArticle p = new ServiceArticle();
        p.modifier(a);
       
        
         Stage stage = (Stage) articlePane.getScene().getWindow();
       try {
                Scene p1=(Scene) stage.getOwner().getScene();
                HBox cAnchorPane=(HBox)p1.lookup("#content");
            AnchorPane p2 = FXMLLoader.load(getClass().getResource("ArticleList.fxml"));
            cAnchorPane.getChildren().setAll(p2);
            
           
            
        } catch (IOException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Done ");
        alert.setHeaderText("Blog Edited  ");
        alert.showAndWait();
        test = false;
            stage.close();
        }
        
    }

    @FXML
    private void uploadimage(ActionEvent event) {
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
    private void close(ActionEvent event) {
        Stage stage = (Stage) articlePane.getScene().getWindow();
        stage.close();
    }
    
}
