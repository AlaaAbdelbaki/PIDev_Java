/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.ui;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Notifications;
import tunisiagottalent.entity.Article;
import tunisiagottalent.services.ServiceArticle;
//import javafx.util.Duration:

/**
 * FXML Controller class
 *
 * @author ghassen
 */
public class ArticleListController implements Initializable {

    @FXML
    private TableColumn<Article, String> colTitle;
    @FXML
    private TableColumn<Article, String> colimg;
    @FXML
    private TableColumn<Article, String> colContent;
    @FXML
    private Button RetourAction;

      List listp = new ArrayList();
    Article a ;
    ServiceArticle sp = new ServiceArticle();
    @FXML
    private TableView<Article> ListArticle;
    @FXML
    private AnchorPane ContentPane;
    @FXML
    private Button supprimer;

    public void views() throws SQLException {  
      
        listp = sp.afficher();
        ObservableList<Article> l = FXCollections.observableArrayList(listp);  
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colimg.setCellValueFactory(new PropertyValueFactory<>("img"));
        colContent.setCellValueFactory(new PropertyValueFactory<>("content"));
        
        System.out.println("Perfect Saw !");
       ListArticle.setEditable(true);
        ListArticle.setItems(l);
  }
    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
           views();
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
    }    

    @FXML
    private void btnRetourAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/tunisiagottalent/ui/Article.fxml"));
        ContentPane.getChildren().setAll(pane);
    }

    @FXML
    private void btnSuppAction(ActionEvent event) throws SQLException {
       Article a = ListArticle.getSelectionModel().getSelectedItem();
        
        if (a == null) { 
           
            System.out.println("nope");
                
                

             /*  Image img = new Image("/tunisiagotatlent/img/cup.png");
                Notifications n = Notifications.create()
                                  .title("Error")
                                  .text("  Choix invalide")
                                  .graphic(new ImageView(img))
                                  .position(Pos.TOP_CENTER)
                                 .hideAfter(Duration.seconds(5));
                                  n.darkStyle();
                                  n.show(); */
                                }
        else{
                ServiceArticle Sp = new ServiceArticle();
                Article t = new Article(a.getId());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                              alert.setTitle("Confirmation ");
                              alert.setHeaderText(null);
                              alert.setContentText("Vous voulez vraiment supprimer cet article ");
                              Optional<ButtonType> action = alert.showAndWait();
                              if (action.get() == ButtonType.OK) {
                                    Sp.supprimer(t);
                                   views();}
    } }}

    
