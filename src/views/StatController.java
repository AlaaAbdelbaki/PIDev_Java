/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import services.EventService;

/**
 * FXML Controller class
 *
 * @author hela
 */
public class StatController implements Initializable {
 EventService es = new EventService();
    @FXML
    private PieChart pieChart;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<PieChart.Data> pieChartData;
     try {
         pieChartData = FXCollections.observableArrayList(
                 
                 new PieChart.Data("Casting",es.castingType()),
                 new PieChart.Data("Concert",es.concertType()),
                 new PieChart.Data("Audition",es.auditionType()),
                 new PieChart.Data("Offre emploi",es.offreType())
                 
         );
           pieChart.setData(pieChartData);
     } catch (SQLException ex) {
         Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
     }
        
      
    }    
    
}
