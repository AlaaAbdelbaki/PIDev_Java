package tunisiagottalent.UI.Competitions;

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

public class AdminCompetitions {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Competition> competitions;
    @FXML private TableColumn<Competition, Integer> ID;
    @FXML private TableColumn<Competition, String> Subject;
    @FXML private TableColumn<Competition, Timestamp> startDate;
    @FXML private TableColumn<Competition, Timestamp> endDate;
    @FXML private TableColumn<Competition, Void> Action;
    @FXML
    private Button btn_add;
    @FXML
    private AnchorPane Competitions_list;

    @FXML
    void initialize() {
        CompetitionServices cs=new CompetitionServices();
        ID.setCellValueFactory(new PropertyValueFactory<Competition,Integer>("id"));
        Subject.setCellValueFactory(new PropertyValueFactory<Competition,String>("subject"));
        startDate.setCellValueFactory(new PropertyValueFactory<Competition,Timestamp>("competition_date"));
        endDate.setCellValueFactory(new PropertyValueFactory<Competition,Timestamp>("competition_end_date"));
        Action.setCellFactory(param -> new TableCell<Competition, Void>() {
                    private final Button editButton = new Button("Edit");
                    private final Button deleteButton = new Button("Delete");
                    private final HBox pane = new HBox(deleteButton, editButton);

                    {
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
                                System.out.println(controller);
                                Competitions_list.getChildren().setAll(root);

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
                AnchorPane p = FXMLLoader.load(getClass().getResource("Add_COmpetition.fxml"));
                this.Competitions_list.getChildren().setAll(p);

            } catch (IOException ex) {
                Logger.getLogger(AdminCompetitions.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        


    }

}