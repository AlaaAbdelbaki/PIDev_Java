package tunisiagottalent.UI;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import tunisiagottalent.Entity.Competition;
import tunisiagottalent.services.CompetitionServices;
import tunisiagottalent.services.ParticipationServices;

public class User_CompetitionsController {

    @FXML
    private TableView<Competition> competitions;
    @FXML private TableColumn<Competition, Integer> ID;
    @FXML private TableColumn<Competition, String> Subject;
    @FXML private TableColumn<Competition, Timestamp> startDate;
    @FXML private TableColumn<Competition, Timestamp> endDate;
    @FXML private TableColumn<Competition, Void> Action;
    
    @FXML
    private AnchorPane Competitions_list;

    @FXML
    void initialize() {
        CompetitionServices cs=new CompetitionServices();
        
        Subject.setCellValueFactory(new PropertyValueFactory<Competition,String>("subject"));
        startDate.setCellValueFactory(new PropertyValueFactory<Competition,Timestamp>("competition_date"));
        endDate.setCellValueFactory(new PropertyValueFactory<Competition,Timestamp>("competition_end_date"));
        Action.setCellFactory(param -> new TableCell<Competition, Void>() {
                    private final Button participateButton = new Button("Participate");
                    private final Button ViewButton = new Button("View");
                    private final HBox pane = new HBox(ViewButton,participateButton);

                    {
                        participateButton.setOnAction(event -> {
                            try {
                            Competition c = getTableView().getItems().get(getIndex());
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("Competition_Participate.fxml"));
                            Parent root = loader.load();
                            Competition_ParticipateController controller = loader.<Competition_ParticipateController>getController();
                                controller.setCompetition(c);
                            Competitions_list.getChildren().setAll(root);
                           
                                } catch (IOException ex) {
                                    Logger.getLogger(User_CompetitionsController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            
                        });

                        ViewButton.setOnAction(event -> {
                            try {
                               Competition c = getTableView().getItems().get(getIndex());
                               FXMLLoader loader = new FXMLLoader(getClass().getResource("View_Competition.fxml"));
                               Parent root = loader.load();
                               View_CompetitionController controller = loader.<View_CompetitionController>getController();
                                controller.setCompetition(c);
                              
                                Competitions_list.getChildren().setAll(root);

                                } catch (IOException ex) {
                                    Logger.getLogger(User_CompetitionsController.class.getName()).log(Level.SEVERE, null, ex);
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
       
        


    }

}