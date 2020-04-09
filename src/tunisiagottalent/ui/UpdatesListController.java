/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.ui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import tunisiagottalent.entity.Updates;
import tunisiagottalent.services.ServiceUpdates;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import tunisiagottalent.entity.Article;



/**
 * FXML Controller class
 *
 * @author ghassen
 */
public class UpdatesListController implements Initializable {

    @FXML
    private TableColumn<Updates, String> colTitle;
    @FXML
    private TableColumn<Updates, String> colimg;
    @FXML
    private TableColumn<Updates, String> colcategory;
    @FXML
    private TableColumn<Updates, Date> coldate;
    @FXML
    private TableColumn<Updates, String> colContent;
    @FXML
    private Button RetourAction;
    
    List listp = new ArrayList();
    Updates u = new Updates();
    ServiceUpdates sp = new ServiceUpdates();
    public ObservableList<Updates> list = FXCollections.observableArrayList(sp.afficher());
    
    @FXML
    private TableView<Updates> ListUp;
    @FXML
    private AnchorPane ContentPane;
    @FXML
    private Button SupprimerAction;
    @FXML
    private TextField UpTitle;
    @FXML
    private TextField UpImg;
    @FXML
    private TextField UpCategory;
    @FXML
    private TextField UpContent;
    private int current_id;
    @FXML
    private DatePicker UpPublish_date;
    @FXML
    private Button modifer;
    @FXML
    private ImageView img;
    


    public void views() throws SQLException {  
      
        listp = sp.afficher();
        ObservableList<Updates> l = FXCollections.observableArrayList(listp);  
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colimg.setCellValueFactory(new PropertyValueFactory<>("img"));
        colcategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("publish_date"));
        colContent.setCellValueFactory(new PropertyValueFactory<>("content"));
        System.out.println("Perfect Saw !");
        ListUp.setEditable(true);
        ListUp.setItems(l);
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
        
        //Modifier Starts Here
         ListUp.setOnKeyReleased((KeyEvent e) -> {
             if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.DOWN) {
                 
                 Updates rowData = ListUp.getSelectionModel().getSelectedItem();
                 /**
                  * fill the fields with the selected data *
                  */
                 //Event et = ComTitre.getSelectionModel().getSelectedItem();
                 // LocalDate df= Updatec.getValue();
                 
                 
                 UpTitle.setText(rowData.getTitle());
                 UpImg.setText(rowData.getImg());
                 UpContent.setText(rowData.getContent());
                 UpCategory.setText(rowData.getCategory());
                 //UpPublish_date.setValue(LOCAL_DATE(rowData.getPublish_date().toString()));
                 UpPublish_date.setValue(LOCAL_DATE(rowData.getPublish_date().toString()));
                 current_id = rowData.getId();
                 
             }
             //file:/C:/Users/EZZEDINE/Desktop/PIDev_Java/src/tunisiagottalent/ui/img/blog.png
        ListUp.setOnMouseClicked((MouseEvent x)->{
                   int selectedIndex = ListUp.getSelectionModel().getSelectedIndex();
                   if (selectedIndex!=-1) {                     
                    Updates pi = (Updates) ListUp.getSelectionModel().getSelectedItem();                        
                    img.setImage(new Image(pi.getImg()) );                 
                         }                
                    });
        });
         

    }   
    

    @FXML
    private void btnRetourAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/tunisiagottalent/ui/Updates.fxml"));
        ContentPane.getChildren().setAll(pane);
    }
    @FXML
    private void btnSuppAction(ActionEvent event) throws SQLException {
       Updates a = ListUp.getSelectionModel().getSelectedItem();
        
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
                ServiceUpdates Sp = new ServiceUpdates();
                Updates t = new Updates(a.getId());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                              alert.setTitle("Confirmation ");
                              alert.setHeaderText(null);
                              alert.setContentText("Vous voulez vraiment supprimer cet updates ");
                              Optional<ButtonType> action = alert.showAndWait();
                              if (action.get() == ButtonType.OK) {
                                    Sp.supprimer(t);
                                   views();}
    } }

    @FXML
    private void btnModifAction(ActionEvent event) throws IOException {
        ServiceUpdates ps = new ServiceUpdates();
           
            LocalDate publish_date= UpPublish_date.getValue();
            Date dateE=Date.valueOf(publish_date.toString());
           
        
           
            Updates a =new Updates(current_id,UpTitle.getText(),UpImg.getText(),UpContent.getText(),dateE,UpCategory.getText());
            ps.modifier(a);
            AnchorPane redirected;
                        redirected = FXMLLoader.load(getClass().getResource("/tunisiagottalent/ui/UpdatesList.fxml")); 
                        ContentPane.getChildren().setAll(redirected);
            /**
             * refreshing the table view *
             */
          
            ListUp.setItems(list);
                        
    }
    
    
    public static final LocalDate NOW_LOCAL_DATE (){
        String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date , formatter);
        return localDate;
    }
    
     public static final LocalDate LOCAL_DATE (String dateString){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate = LocalDate.parse(dateString, formatter);
    return localDate;
}


    }
    

   

    

    

