package tunisiagottalent.UI.Competitions;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;

import java.sql.Timestamp;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.mail.MessagingException;

import tunisiagottalent.Entity.Competition;

import tunisiagottalent.Entity.competition_participant;

import tunisiagottalent.services.CompetitionServices;
import tunisiagottalent.services.ParticipationServices;
import tunisiagottalent.services.UserServices;
import tunisiagottalent.services.VoteServices;
import tunisiagottalent.util.sendEmailSMTP;

public class AdminCompetitions {

    @FXML
    private TableView<Competition> competitions;
    @FXML
    private TableColumn<Competition, Integer> ID;
    @FXML
    private TableColumn<Competition, String> Subject;
    @FXML
    private TableColumn<Competition, Timestamp> startDate;
    @FXML
    private TableColumn<Competition, Timestamp> endDate;
    @FXML
    private TableColumn<Competition, Void> Action;
    
    @FXML
    private Button btn_add;
    @FXML
    private AnchorPane Competitions_list;
    
    @FXML
    private Text winners_list;
    ObservableList<competition_participant> l=FXCollections.observableArrayList();
    @FXML
    void initialize() {
        CompetitionServices cs = new CompetitionServices();

        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        Subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("competition_date"));
        endDate.setCellValueFactory(new PropertyValueFactory<>("competition_end_date"));
        Action.setCellFactory(param -> new TableCell<Competition, Void>() {
            private JFXButton editButton = new JFXButton("Edit");
            private JFXButton deleteButton = new JFXButton("Delete");

            private final HBox pane = new HBox(50, deleteButton, editButton);

            {
                editButton.resize(100, 45);
                editButton.setStyle("-fx-text-fill: white;-fx-font-size:20px;-fx-background-color:#d65f1a");
                editButton.setRipplerFill(javafx.scene.paint.Color.ORANGE);
                deleteButton.setStyle("-fx-text-fill: white;-fx-font-size:20px; -fx-background-color:#82171d");
                deleteButton.setRipplerFill(javafx.scene.paint.Color.WHITE);
                deleteButton.resize(100, 45);
                deleteButton.setOnAction(event -> {
                    Competition c = getTableView().getItems().get(getIndex());
                    cs.delete(c);
                    competitions.getItems().remove(c);
                });

                editButton.setOnAction(event -> {
                    try {
                        Competition c = getTableView().getItems().get(getIndex());
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Edit_Competition.fxml"));
                        Parent root = loader.load();
                        Edit_CompetitionController controller = loader.<Edit_CompetitionController>getController();
                        controller.setCompetition(c);
                        Scene s = new Scene(root);
                        s.setFill(Color.TRANSPARENT);
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.setOpacity(1);
                        stage.setTitle("Edit Competition");
                        stage.initOwner(Competitions_list.getScene().getWindow());
                        stage.setScene(s);
                        stage.show();

                    } catch (IOException ex) {
                        Logger.getLogger(AdminCompetitions.class.getName()).log(Level.SEVERE, null, ex);
                    }

                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                setGraphic(empty ? null : pane);
            }
        });

        competitions.setItems(cs.getAll());

        btn_add.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Add_COmpetition.fxml"));
                Parent root = loader.load();
                Scene s = new Scene(root);
                s.setFill(Color.TRANSPARENT);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setOpacity(0.8);
                stage.setTitle("Add Competition");
                stage.initOwner(Competitions_list.getScene().getWindow());
                stage.setScene(s);
                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(AdminCompetitions.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        ObservableList<Competition> comps=cs.getAll();
        ParticipationServices ps =new ParticipationServices();
        VoteServices vs=new VoteServices();
         comps.forEach((c) -> {
              if(c.getCompetition_end_date().before(new Timestamp(System.currentTimeMillis()))){
            
             if(!vs.ranks(c).isEmpty()){
                 
                if(!(vs.ranks(c).iterator().next().getOwner().getRole().contains("ROLE_TALENTED"))){
                l.add(ps.getWinners(vs.ranks(c).iterator().next().getId()));}
             }}
            
         });}
        
           

    @FXML
    private void win_list(MouseEvent event) {
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("WinnersList.fxml"));
                Parent root = loader.load();
                WinnersListController controller=loader.getController();
                controller.setWin(l);
                Scene s = new Scene(root);
                s.setFill(Color.TRANSPARENT);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setOpacity(0.8);
                stage.setTitle("Winners");
                stage.initOwner(Competitions_list.getScene().getWindow());
                stage.setScene(s);
                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(AdminCompetitions.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

}
