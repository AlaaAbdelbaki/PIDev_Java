package tunisiagottalent.UI;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import tunisiagottalent.Entity.Competition;
import tunisiagottalent.services.CompetitionServices;

public class Edit_CompetitionController {

    private Competition c;
    
    @FXML
    private AnchorPane edit_comp_anchor;

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
    private Button btn_update;
@FXML
    void initialize() {
        Platform.runLater(() -> {
            Timestamp t=c.getCompetition_date();
            LocalDate d=t.toLocalDateTime().toLocalDate();
            LocalTime s=t.toLocalDateTime().toLocalTime();
            Timestamp t2=c.getCompetition_end_date();
            LocalDate d2=t2.toLocalDateTime().toLocalDate();
            LocalTime s2=t2.toLocalDateTime().toLocalTime();
        startDate.setValue(d);
        startTime.setValue(s);
        endDate.setValue(d2);
        endTime.setValue(s2);
        subject.setText(c.getSubject());

    });
        
      
      
    }
    @FXML
    void Cancel(ActionEvent event) {
try {
                AnchorPane p = FXMLLoader.load(getClass().getResource("Admin_Competitions.fxml"));
                this.edit_comp_anchor.getChildren().setAll(p);

            } catch (IOException ex) {
                Logger.getLogger(Add_COmpetitionController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    void update(ActionEvent event) {
         try { String sub=subject.getText();
            LocalDate s=startDate.getValue();
            LocalTime t=startTime.getValue();
            LocalDateTime dt= LocalDateTime.of(s, t);
            Timestamp f= Timestamp.valueOf(dt);
            LocalDate s2=endDate.getValue();
            LocalTime t2=endTime.getValue();
            LocalDateTime dt2= LocalDateTime.of(s2, t2);
            Timestamp f2= Timestamp.valueOf(dt2);
            CompetitionServices cs =new CompetitionServices();
            Competition c1=new Competition(this.c.getId(),sub, f, f2);
             
            cs.update(c1);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Competition");
            alert.setHeaderText("Competition Updated Succesfully");
            alert.setContentText("Go Back To The List");

            alert.showAndWait();
                AnchorPane p = FXMLLoader.load(getClass().getResource("Admin_Competitions.fxml"));
                this.edit_comp_anchor.getChildren().setAll(p);

            } catch (IOException ex) {
                Logger.getLogger(Edit_CompetitionController.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
public void setCompetition(Competition c) {
        this.c=c;
    }
}
