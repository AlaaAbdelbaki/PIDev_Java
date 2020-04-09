/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisigottalentViews;

import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tunisiagottalent.util.DataSource;
import tunisiagottalentEntities.Complaint;
import tunisiagottalentEntities.Review;
import tunisiagottalentServices.ComplaintService;
import tunisiagottalentServices.ReviewService;

/**
 * FXML Controller class
 *
 * @author sarah
 */
public class PieChartCController implements Initializable {
    private Connection  connexion =(Connection) DataSource.getInstance().getCnx();
    @FXML
    private BarChart<String, Integer> barchart;
    @FXML
    private Button Retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String req =" SELECT category , rating from review ";
        XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
        try {
             PreparedStatement ste = (PreparedStatement) connexion.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));
                
            }
           barchart.getData().add(series); 
        } catch (SQLException ex) {
            Logger.getLogger(PieChartCController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
    }  
    private void loadData(){
        ReviewService rs= new ReviewService();
        Review r=new Review();
        ObservableList<PieChart.Data> list= FXCollections.observableArrayList();
        rs.getAll();
    
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
           javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }
    
    
}
