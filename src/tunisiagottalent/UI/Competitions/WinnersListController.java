/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Competitions;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.mail.MessagingException;
import tunisiagottalent.Entity.competition_participant;
import tunisiagottalent.services.UserServices;
import tunisiagottalent.util.sendEmailSMTP;

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class WinnersListController implements Initializable {

    @FXML
    private TableView<competition_participant> winners;
    @FXML
    private TableColumn<competition_participant, String> user;
    @FXML
    private TableColumn<competition_participant, String> comp;
    @FXML
    private TableColumn<competition_participant, Void> vid;
    @FXML
    private TableColumn<competition_participant, Void> prom;
    ObservableList<competition_participant> win;
    @FXML
    private AnchorPane root;

    public void setWin(ObservableList<competition_participant> win) {
        this.win = win;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(()->{loadinfo();});
    }

    public void loadinfo() {

        user.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        comp.setCellValueFactory(new PropertyValueFactory<>("competition_id"));
        prom.setCellFactory(param -> {
            return new TableCell<competition_participant, Void>() {
                private JFXButton promote = new JFXButton("Promote to Talented");

                {
                    promote.resize(100, 45);
                    promote.setStyle("-fx-text-fill: white;-fx-font-size:20px;-fx-background-color:orange");
                    promote.setRipplerFill(javafx.scene.paint.Color.ORANGE);
                    promote.setOnAction(event -> {
                        UserServices us = new UserServices();

                        competition_participant cp = getTableView().getItems().get(getIndex());

                        us.Promote(cp.getUser_id());
                        new Thread(() -> {
                            try {
                                sendEmailSMTP.PromoteUser(cp.getUser_id(), us.getUser(cp.getUser_id()).getEmail());
                            } catch (MessagingException ex) {
                                Logger.getLogger(AdminCompetitions.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }).start();

                        winners.getItems().remove(cp);
                    });

                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);

                    setGraphic(empty ? null : promote);
                }

            };
        });
        vid.setCellFactory(param -> new TableCell<competition_participant, Void>() {
            private JFXButton Viewvid = new JFXButton("View Video");

            {
                Viewvid.resize(100, 45);
                Viewvid.setStyle("-fx-text-fill: white;-fx-font-size:20px;-fx-background-color:green");
                Viewvid.setRipplerFill(javafx.scene.paint.Color.GREENYELLOW);
                Viewvid.setOnAction(event -> {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Vid_details.fxml"));
                        Parent third = loader.load();
                        competition_participant cp = getTableView().getItems().get(getIndex());
                        Vid_detailsController controller = loader.<Vid_detailsController>getController();
                        controller.setVideo(cp.getVideo_id());
                        Scene s = new Scene(third);
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.setOpacity(0.7);
                        stage.setTitle("Details");
                        stage.initOwner(root.getScene().getWindow());
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

                setGraphic(empty ? null : Viewvid);
            }

        });

        winners.setItems(win);
        winners.refresh();

    }

    @FXML
    private void close(ActionEvent event) {
         Stage stage = (Stage) root.getScene().getWindow();
         stage.close();
    }
}
