package tunisiagottalent.UI.Competitions;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import tunisiagottalent.Entity.Competition;
import static tunisiagottalent.UI.Competitions.View_CompetitionController.getDateDiff;
import static tunisiagottalent.UI.Competitions.View_CompetitionController.oneSecond;
import tunisiagottalent.services.CompetitionServices;
import tunisiagottalent.services.ParticipationServices;
import tunisiagottalent.util.UserSession;

public class User_CompetitionsController {

    @FXML
    private GridPane competitions;

    @FXML
    private AnchorPane Competitions_list;

    @FXML
    void initialize() {
        CompetitionServices cs = new CompetitionServices();

        ObservableList<Competition> tab = cs.getAll();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        Label TDate=new Label("Starting Date");
        TDate.setTextFill(javafx.scene.paint.Color.GREENYELLOW);
        TDate.setFont(Font.font("Book Antiqua", 22));
        Label TEndDate=new Label("End Date");
        TEndDate.setTextFill(javafx.scene.paint.Color.GREENYELLOW);
        TEndDate.setFont(Font.font("Book Antiqua", 22));
        Label TTimeLeft=new Label("Time Left");
        TTimeLeft.setTextFill(javafx.scene.paint.Color.GREENYELLOW);
        TTimeLeft.setFont(Font.font("Book Antiqua", 22));
        Label Tsubject=new Label("Subject");
        Tsubject.setTextFill(javafx.scene.paint.Color.GREENYELLOW);
        Tsubject.setFont(Font.font("Book Antiqua", 22));
        competitions.addRow(0, TDate, TEndDate, TTimeLeft,Tsubject);
        tab.forEach((comp) -> {

            Label start = new Label(sdf.format(new Date(comp.getCompetition_date().getTime())));
            start.setTextFill(javafx.scene.paint.Color.WHITE);
            start.setFont(Font.font("Cambria", 20));
            Label end = new Label(sdf.format(new Date(comp.getCompetition_end_date().getTime())));
            end.setTextFill(javafx.scene.paint.Color.WHITE);
            end.setFont(Font.font("Cambria", 20));
            Label sub = new Label(comp.getSubject());
            sub.setFont(Font.font("Cambria", 16));
            sub.setTextFill(javafx.scene.paint.Color.WHITE);
            Label TalentLabel = new Label("Already a Talent!");
            TalentLabel.setTextFill(javafx.scene.paint.Color.WHITE);
            TalentLabel.setFont(Font.font("Cambria", 13));
            
            Label OverLabel = new Label("Competition is Over !");
            OverLabel.setTextFill(javafx.scene.paint.Color.WHITE);
            OverLabel.setFont(Font.font("Cambria", 13));
            
            Label ParticipatedlLabel = new Label("Participated");
            ParticipatedlLabel.setTextFill(javafx.scene.paint.Color.WHITE);
            ParticipatedlLabel.setFont(Font.font("Cambria", 13));
            
            JFXButton v = new JFXButton("View");
            JFXButton p = new JFXButton("Participate");
            v.resize(175, 45);
            v.setStyle("-fx-text-fill: white;-fx-font-size:20px;-fx-background-color: #adff2f");
            v.setRipplerFill(javafx.scene.paint.Color.GREEN);
            p.setStyle("-fx-text-fill: white;-fx-font-size:20px; -fx-background-color:#F39C12");
            p.setRipplerFill(javafx.scene.paint.Color.BLUE);
            p.resize(175, 45);
            v.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    try {
                        Competition c = comp;
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("View_Competition.fxml"));
                        Parent root = loader.load();
                        View_CompetitionController controller = loader.<View_CompetitionController>getController();
                        controller.setCompetition(c);

                        Competitions_list.getChildren().setAll(root);

                    } catch (IOException ex) {
                        Logger.getLogger(User_CompetitionsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            p.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    try {
                        Competition c = comp;

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Competition_Participate.fxml"));
                        Parent root = loader.load();
                        Competition_ParticipateController controller = loader.<Competition_ParticipateController>getController();
                        controller.setCompetition(c);
                        Scene s = new Scene(root);
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.setOpacity(0.8);
                        stage.setTitle("Participate");
                        stage.initOwner(Competitions_list.getScene().getWindow());
                        stage.setScene(s);
                        stage.show();

                    } catch (IOException ex) {
                        Logger.getLogger(User_CompetitionsController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
            Label TimeleftLabel=new Label();
            TimeleftLabel.setTextFill(javafx.scene.paint.Color.GREENYELLOW);
            TimeleftLabel.setFont(Font.font("Cambria", 18));
            if(comp.getCompetition_end_date().after(new Timestamp(System.currentTimeMillis()))){
           Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                Timestamp t = new Timestamp(System.currentTimeMillis());
                long counter = getDateDiff(t, comp.getCompetition_end_date(), TimeUnit.SECONDS);
                  
                @Override
                public void handle(ActionEvent event) {

                    long h = TimeUnit.SECONDS.toHours(counter);
                    long m = TimeUnit.SECONDS.toMinutes(counter - h * 3600);
                    long s = TimeUnit.SECONDS.toSeconds(counter - h * 3600 - m * 60);
                    counter = counter - 1;
                     TimeleftLabel.setText(String.format("%02d:%02d:%02d", h,m,s));
                     
                    

                }
            }));
            timer.setCycleCount(60);
            timer.play();}
            else{
                TimeleftLabel.setText("00:00:00");
            }
            UserSession s = UserSession.instance;
            ParticipationServices ps=new ParticipationServices();
            if (s.getU().getRole().contains("ROLE_TALENTED")) {
                competitions.addRow(tab.indexOf(comp)+1, start, end,TimeleftLabel, sub, v, TalentLabel);
            }
            else if (comp.getCompetition_end_date().before(new Timestamp(System.currentTimeMillis())))
            {
                competitions.addRow(tab.indexOf(comp)+1, start,end,TimeleftLabel, sub, v, OverLabel);
            }
            else if(ps.findParticipation(comp, s.getU())){
                competitions.addRow(tab.indexOf(comp)+1, start, end,TimeleftLabel, sub, v, ParticipatedlLabel);
            }
            else 
            competitions.addRow(tab.indexOf(comp)+1, start, end,TimeleftLabel, sub, v, p);

            RowConstraints row = new RowConstraints(90);

            competitions.getRowConstraints().add(row);

        });
        competitions.setPadding(new Insets(10, 10, 10, 10));
        competitions.setStyle("-fx-background-color: #0c0527");
        competitions.setVgap(15);

    }

}
