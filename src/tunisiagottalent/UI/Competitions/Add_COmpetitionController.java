package tunisiagottalent.UI.Competitions;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tunisiagottalent.Entity.Competition;
import tunisiagottalent.services.CompetitionServices;

public class Add_COmpetitionController {

    @FXML
    private JFXDatePicker startDate;

    @FXML
    private JFXDatePicker endDate;

    @FXML
    private JFXTimePicker startTime;

    @FXML
    private JFXTimePicker endTime;

    @FXML
    private TextArea subject;

    @FXML
    private Button btn_cancel;

    @FXML
    private Button btn_add;
    @FXML
    private AnchorPane add_comp_anchor;
@FXML
    void initialize() {
        startTime.setValue(LocalTime.now());
        startDate.setValue(LocalDate.now());
        startDate.setMouseTransparent(true);
        startTime.setMouseTransparent(true);

    }

    @FXML
    void Cancel(ActionEvent event) {
        Stage stage = (Stage) add_comp_anchor.getScene().getWindow();
        stage.close();
    }

    @FXML
    void Add(ActionEvent event) {
        String sub = subject.getText();
        LocalDate s = startDate.getValue();
        LocalTime t = startTime.getValue();
        LocalDateTime dt = LocalDateTime.of(s, t);
        Timestamp f = Timestamp.valueOf(dt);
        LocalDate s2 = endDate.getValue();
        LocalTime t2 = endTime.getValue();
        LocalDateTime dt2 = LocalDateTime.of(s2, t2);
        Timestamp f2 = Timestamp.valueOf(dt2);
        CompetitionServices cs = new CompetitionServices();
        Competition c1 = new Competition(sub, f, f2);
        cs.create(c1);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Competition");
        alert.setHeaderText("Competition Added Succesfully");
        alert.setContentText("Go Back To The List");

        alert.showAndWait();
        Stage stage = (Stage) add_comp_anchor.getScene().getWindow();
       
         try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("AdminCompetitions.fxml"));
            
             HBox h=(HBox) stage.getOwner().getScene().lookup("#content");
             h.getChildren().clear();
             h.getChildren().setAll(p);
            

        } catch (IOException ex) {
            Logger.getLogger(Add_COmpetitionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.close();

    }
}
