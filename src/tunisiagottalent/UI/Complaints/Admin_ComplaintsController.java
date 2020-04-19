/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Complaints;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.mail.MessagingException;
import tunisiagottalent.Entity.Complaint;
import tunisiagottalent.services.ComplaintService;
import tunisiagottalent.UI.Reviews.User_detailsController;
import tunisiagottalent.util.sendEmailSMTP;

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class Admin_ComplaintsController implements Initializable {

    @FXML
    private TableView<Complaint> tab;
    @FXML
    private TableColumn<Complaint, Integer> colid;
    @FXML
    private TableColumn<Complaint, String> colsubj;
    @FXML
    private TableColumn<Complaint, String> colcont;
    @FXML
    private TableColumn<Complaint, Void> usernamec;
    @FXML
    private JFXTextArea areaText;
    @FXML
    private JFXTextField search;

    ComplaintService cs = new ComplaintService();
    ObservableList<Complaint> or = FXCollections.observableArrayList(cs.getAll());
    @FXML
    private JFXButton deleteR;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colsubj.setCellValueFactory(new PropertyValueFactory<>("Subject"));
        colcont.setCellValueFactory(new PropertyValueFactory<>("content"));

        usernamec.setCellFactory(param -> new TableCell<Complaint, Void>() {
            private JFXButton Viewvid = new JFXButton("View User");

            {
                Viewvid.resize(100, 45);
                Viewvid.setStyle("-fx-text-fill: white;-fx-font-size:20px;-fx-background-color:#145C9E");
                Viewvid.setRipplerFill(javafx.scene.paint.Color.GREENYELLOW);
                Viewvid.setOnAction(event -> {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tunisiagottalent/UI/Reviews/User_details.fxml"));
                        Parent third = loader.load();
                        Complaint cp = getTableView().getItems().get(getIndex());
                        User_detailsController controller = loader.<User_detailsController>getController();
                        controller.setU(cp.getUser_id());
                        Scene s = new Scene(third);
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.setOpacity(0.9);
                        stage.setTitle("Details");

                        stage.setScene(s);

                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(Admin_ComplaintsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                setGraphic(empty ? null : Viewvid);
            }

        });

        FilteredList<Complaint> filteredSearch = new FilteredList<>(or, b -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredSearch.setPredicate((Predicate<? super Complaint>) Complaint -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(Complaint.getId()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (Complaint.getSubject().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } //else if(String.valueOf(Complaint.getId()).indexOf(lowerCaseFilter) != -1){
                //      return true;
                // }
                else if (Complaint.getContent().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });
        SortedList<Complaint> sortedComplaint = new SortedList<>(filteredSearch);
        sortedComplaint.comparatorProperty().bind(tab.comparatorProperty());
        tab.setItems(sortedComplaint);
    }

    public Boolean ValidateFields() {
        if (areaText.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate fields");
            alert.setHeaderText(null);
            alert.setContentText("Your field is empty");
            alert.showAndWait();
            return false;
        }

        return true;

    }
@FXML
    private void respond(ActionEvent event) throws MessagingException {
        Complaint c = (Complaint) tab.getSelectionModel().getSelectedItem();
        or.remove(c);
        cs.deleteComplaint(c);

        if (c == null) {

            System.out.println("Choose a complaint to Respond to");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Choose a complaint to Respond to");
            alert.showAndWait();

        } else {

            if (ValidateFields()) {
                String s = areaText.getText();
                String text = c.getSubject();
                Platform.runLater(()->{
                    try {
                        sendEmailSMTP.RespondToComplaint(c.getUser_id().getUsername(), c.getUser_id().getEmail(), text,s);
                    } catch (MessagingException ex) {
                        Logger.getLogger(Admin_ComplaintsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
});
                
                or.remove(c);
                cs.deleteComplaint(c);
               

            }

        }
    }

    @FXML
    private void removeComplaint(ActionEvent event) {

        Complaint c = (Complaint) tab.getSelectionModel().getSelectedItem();
        or.remove(c);
        cs.deleteComplaint(c);

        if (c == null) {

            System.out.println("Choose a complaint to Delete ");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Choose a complaint to Delete ");
            alert.showAndWait();

        } else {

            or.remove(c);
            cs.deleteComplaint(c);

        }
    }

}
