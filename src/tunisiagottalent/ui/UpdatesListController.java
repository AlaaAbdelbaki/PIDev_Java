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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import tunisiagottalent.entity.Updates;
import tunisiagottalent.services.ServiceUpdates;

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
    @FXML
    private TableView<Updates> ListUp;
    @FXML
    private AnchorPane ContentPane;

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
    }    

    @FXML
    private void btnRetourAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/tunisiagottalent/ui/Updates.fxml"));
        ContentPane.getChildren().setAll(pane);
    }
    
}
