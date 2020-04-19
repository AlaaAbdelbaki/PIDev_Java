/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Base;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.OptionalDouble;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import tunisiagottalent.services.Event_ParticipantService;
import tunisiagottalent.services.OrderServices;
import tunisiagottalent.services.ParticipationServices;
import tunisiagottalent.services.UserServices;

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class AdminHomepageController implements Initializable {

    @FXML
    private LineChart<String, Double> lineChartOrders;
    @FXML
    private PieChart pieChartUsers;
    @FXML
    private BarChart<String, Number> BarChartCompetitions;

    OrderServices os = new OrderServices();
    UserServices us = new UserServices();
    ParticipationServices ps = new ParticipationServices();
    Event_ParticipantService Ep=new Event_ParticipantService();
    @FXML
    private Label totalProfit;
    @FXML
    private Label highestPurchase;
    @FXML
    private Label monthlyIncome;
    @FXML
    private Label totalUsers;
    @FXML
    private Label talentedUsers;
    @FXML
    private Label normalUsers;
    @FXML
    private Label totalCompetitions;
    @FXML
    private Label highestParticipation;
    @FXML
    private PieChart pieChartEvents;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        linechart();
        PieChart();
        Barchart();
        PieChartEvents();
    }

    public void linechart() {
        final NumberAxis yAxis = new NumberAxis();
        final CategoryAxis xAxis = new CategoryAxis();
        lineChartOrders.setTitle("Profit");
        XYChart.Series series = os.LineChartOrders();
        series.setName("Progress");
        lineChartOrders.getData().add(series);
        double totProfit = os.getAll().stream().mapToDouble(f -> f.getTotal()).sum();
        double high = os.getAll().stream().mapToDouble(f -> f.getTotal()).max().orElseThrow(IllegalStateException::new);
        double monthly = os.getAll().stream()
                .filter((t) -> t.getOrder_date().toLocalDate().getMonthValue() == LocalDate.now().getMonthValue())
                .mapToDouble(f -> f.getTotal()).sum();

        totalProfit.setText(totalProfit.getText() + " " + totProfit);
        highestPurchase.setText(highestPurchase.getText() + " " + high);
        monthlyIncome.setText(monthlyIncome.getText() + " " + monthly);
    }

    public void PieChart() {

        pieChartUsers.setTitle("User Stats");
        pieChartUsers.setData(us.pieChartUsers());
        totalUsers.setText(totalUsers.getText() + " " + ((int) us.pieChartUsers().get(0).getPieValue() + (int) us.pieChartUsers().get(1).getPieValue()));
        talentedUsers.setText(talentedUsers.getText() + " " + (int) us.pieChartUsers().get(0).getPieValue());
        normalUsers.setText(normalUsers.getText() + " " + (int) us.pieChartUsers().get(1).getPieValue());
    }

    public void Barchart() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        BarChartCompetitions.setTitle("Competitions");
        xAxis.setLabel("Competition Subjects");
        yAxis.setLabel("Participation");
        XYChart.Series series1 = ps.LineChartComp();
        series1.setName("Competitions");
        BarChartCompetitions.getData().add(series1);
        
        totalCompetitions.setText(totalCompetitions.getText()+" "+ps.getp().get(0));
        highestParticipation.setText(highestParticipation.getText()+" "+ps.getp().get(2)+": "+ps.getp().get(1)+" participations.");
        System.out.println( ps.getp());
        
        
        
    }
    public void PieChartEvents(){
     pieChartEvents.setTitle("Events Stats");
        pieChartEvents.setData(Ep.pieChartEvent());
        
    
    }
}
