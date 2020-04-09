/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.ui;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import javafx.scene.layout.AnchorPane;
//import org.controlsfx.control.Notifications;
import tunisiagottalent.entity.Article;
import tunisiagottalent.services.ServiceArticle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.Event;


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
    Article u = new Article();
    ServiceArticle sp = new ServiceArticle();
 
    public ObservableList<Article> list = FXCollections.observableArrayList(sp.afficher());
    
    @FXML
    private TableView<Article> ListArticle;
    @FXML
    private AnchorPane ContentPane;
    @FXML
    private Button supprimer;
    @FXML
    private TextField UpTitle;
    @FXML
    private TextField UpImg;
    @FXML
    private TextField UpContent;
    @FXML
    private Button modifier;
    private int current_id;
    @FXML
    private TextField search;
    @FXML
    private ImageView img;
    
    public void views() throws SQLException {  
      
        listp = sp.afficher();
        ObservableList<Article> l = FXCollections.observableArrayList(listp);  
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colimg.setCellValueFactory(new PropertyValueFactory<>("img"));
        colContent.setCellValueFactory(new PropertyValueFactory<>("content"));
        
        System.out.println("Perfect!");
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
        FilteredList<Article> filteredData = new FilteredList<>(list, u -> true);
        search.setOnKeyReleased(u -> {
            search.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Article>) Article -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lower = newValue.toLowerCase();
                    if (Article.getTitle().toLowerCase().contains(lower)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Article> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(ListArticle.comparatorProperty());
            ListArticle.setItems(sortedData);
        });
        
        ListArticle.setOnKeyReleased((KeyEvent e) -> {
             if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.DOWN) {
                 
                 Article rowData = ListArticle.getSelectionModel().getSelectedItem();
                 /**
                  * fill the fields with the selected data *
                  */
                 //Event et = ComTitre.getSelectionModel().getSelectedItem();
                 // LocalDate df= Updatec.getValue();
                 
                 
                 UpTitle.setText(rowData.getTitle());
                  UpImg.setText(rowData.getImg());
                 UpContent.setText(rowData.getContent());
                 
                 current_id = rowData.getId();
                 
             }
        });
        
        
        //file:/C:/Users/EZZEDINE/Desktop/PIDev_Java/src/tunisiagottalent/ui/img/blog.png
        ListArticle.setOnMouseClicked((MouseEvent e)->{
                   int selectedIndex = ListArticle.getSelectionModel().getSelectedIndex();
                   if (selectedIndex!=-1) {                     
                    Article pi = (Article) ListArticle.getSelectionModel().getSelectedItem();                        
                    img.setImage(new Image(pi.getImgArticle()) );                 
                         }                
                    });
         
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
    } }

    @FXML
     private void btnModifAction(ActionEvent event) throws IOException {
        ServiceArticle ps = new ServiceArticle();
           
           Article a=new Article(current_id,UpTitle.getText(),UpImg.getText(),UpContent.getText());
            ps.modifier(a);
            AnchorPane redirected;
                        redirected = FXMLLoader.load(getClass().getResource("/tunisiagottalent/ui/ArticleList.fxml")); 
                        ContentPane.getChildren().setAll(redirected);
            /**
             * refreshing the table view *
             */
          
            ListArticle.setItems(list);
                        
    }
}

    
