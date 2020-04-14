/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Events;

import tunisiagottalent.Entity.Event;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import tunisiagottalent.services.EventService;

/**
 * FXML Controller class
 *
 * @author hela
 */
public class AddEventController implements Initializable {

    @FXML
    private TextField type_id;
    @FXML
    private TextField titleE_id;
    @FXML
    private DatePicker startD_id;
    @FXML
    private DatePicker endD_id;
    @FXML
    private TextField img_id;
    @FXML
    private TextField loc_id;
    @FXML
    private TextField nb_places_id;
    @FXML
    private TextField desc_id;
    @FXML
    private TableView<Event> Table_event;
    @FXML
    private TableColumn<Event, String> col_id;
    @FXML
    private TableColumn<Event, String> col_title;
    @FXML
    private TableColumn<Event, String> col_SDate;
    @FXML
    private TableColumn<Event, String> col_EDate;
    @FXML
    private TableColumn<Event, String> col_img;
    @FXML
    private TableColumn<Event, String> col_location;
    @FXML
    private TableColumn<Event, String> col_nbPlaces;
    @FXML
    private TableColumn<Event, String> col_desc;
    @FXML
    private TableColumn<Event, String> col_type;

    EventService es = new EventService();
    @FXML
    private AnchorPane EventScene_id;
    @FXML
    private Button insererimg;
    @FXML
    private ImageView imgview;
    public String imgurl;
    private URI imguriUri;
    String imgp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_SDate.setCellValueFactory(new PropertyValueFactory<>("start_date"));
        col_EDate.setCellValueFactory(new PropertyValueFactory<>("end_date"));
        col_img.setCellValueFactory(new PropertyValueFactory<>("img"));
        col_location.setCellValueFactory(new PropertyValueFactory<>("location"));
        col_nbPlaces.setCellValueFactory(new PropertyValueFactory<>("nb_places"));
        col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));

        Table_event.setItems(es.afficherEvent());
        Table_event.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    //convertion :
    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    //controle de saisie:
    public Boolean ValidateDate() {
        if ((endD_id.getValue().isBefore(startD_id.getValue())) | (startD_id.getValue().isBefore(java.time.LocalDate.now())) | (endD_id.getValue().isBefore(java.time.LocalDate.now()))) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate fields");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter a valid date");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    public Boolean ValidateFields() {
        if (titleE_id.getText().isEmpty() | img_id.getText().isEmpty() | nb_places_id.getText().isEmpty() | loc_id.getText().isEmpty() | desc_id.getText().isEmpty() | type_id.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate fields");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Into The Fields");
            alert.showAndWait();
            return false;
        }

        return true;

    }

    //delete fnt:
    @FXML
    private void delete_Event(ActionEvent event) {
        ObservableList<Event> selectedRows, allRows;
        allRows = Table_event.getItems();
        selectedRows = Table_event.getSelectionModel().getSelectedItems();
        for (Event e : selectedRows) {
            allRows.remove(e);
            try {
                es.cancelEvent(e.getId());
            } catch (SQLException ex) {
                Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //add fnt:
    @FXML
    private void AddEvent(ActionEvent event) throws IOException {

        String title = titleE_id.getText();
        LocalDate start_date = startD_id.getValue();
        LocalDate end_date = endD_id.getValue();
        String img = img_id.getText();
        String location = loc_id.getText();
        String nb_places = nb_places_id.getText();
        String description = desc_id.getText();
        String type = type_id.getText();
        if (ValidateFields() && ValidateDate()) {
            EventService es = new EventService();
            Date sd = convertToDateViaSqlDate(start_date);
            Date ed = convertToDateViaSqlDate(end_date);
            int nb = Integer.parseInt(nb_places);
            Event e = new Event(title, sd, ed, img, location, nb, description, type);
            es.addEvent(e);

            //copying added img :
            //File s = new File(imgurl);
            // File d = new File("C:\\xampp\\htdocs\\PIDev_Java\\src\\album\\" + imgurl.substring(imgurl.lastIndexOf('/') + 1, imgurl.length()));
            //System.out.println(s.getPath());
            //System.out.println(d.getPath());
            //Files.copy(s.toPath(), d.toPath(), StandardCopyOption.REPLACE_EXISTING);
            Files.copy(Paths.get(imguriUri), Paths.get("C:\\xampp\\htdocs\\PIDev_Java\\src\\album\\" + imgp));

            javafx.scene.Parent tableview = null;
            try {
                tableview = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Events/AddEvent.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(AddTicketController.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Table_event.getItems().add(e);
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();

        }

        clearFields();
    }

    //update fnt:
    @FXML
    private void update_event(ActionEvent event) {

        //recuperer
        String title = titleE_id.getText();
        LocalDate start_date = startD_id.getValue();
        LocalDate end_date = endD_id.getValue();
        String img = img_id.getText();
        String location = loc_id.getText();
        String nb_places = nb_places_id.getText();
        String description = desc_id.getText();
        String type = type_id.getText();
        Image image = new Image(img);
        imgview.setImage(image);

        //creer instance
        Date sd = convertToDateViaSqlDate(start_date);
        Date ed = convertToDateViaSqlDate(end_date);
        int nb = Integer.parseInt(nb_places);
        Event e = new Event(title, sd, ed, img, location, nb, description, type);

        //update event on the view:
        int l = Table_event.getSelectionModel().getSelectedIndex();
        Table_event.getItems().set(l, e);

        try {
            es.updateEvent(e);
        } catch (SQLException ex) {
            Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        clearFields();

    }

    //changing to ticket scene:
    @FXML
    private void loadTicketScene(ActionEvent event) throws IOException {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Events/AddTicket.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    //get selected ligne info and set them on the text fieldText:
    @FXML
    private void showDetails(MouseEvent event) {
        Event E = Table_event.getSelectionModel().getSelectedItem();
        titleE_id.setText(E.getTitle());
        //startD_id.setValue(E.getStart_date());
        // endD_id.getValue();
        img_id.setText(E.getImg());
        loc_id.setText(E.getLocation());
        nb_places_id.setText(String.valueOf(E.getNb_places()));
        desc_id.setText(E.getDescription());
        type_id.setText(E.getType());
    }

    //insert img:
    @FXML
    private void insererimg(ActionEvent event) throws FileNotFoundException {

        FileChooser fc = new FileChooser();
        File selectedfile = fc.showOpenDialog(null);
        if (selectedfile != null) {
            imgurl = selectedfile.toURI().toString();
            String fileName = imgurl.substring(imgurl.lastIndexOf('/') + 1, imgurl.length());
            img_id.setText(fileName);
            Image image = new Image(imgurl);
            imgview.setImage(image);
            imguriUri = selectedfile.toURI();
            imgp = selectedfile.getName();
        }
    }

    //to clear textfields:
    private void clearFields() {
        type_id.clear();
        titleE_id.clear();

        img_id.clear();
        loc_id.clear();
        nb_places_id.clear();
        desc_id.clear();
        imgview.setImage(null);
        startD_id.setValue(null);
        endD_id.setValue(null);

    }

    @FXML
    private void loadStatScene(ActionEvent event) {
        javafx.scene.Parent tableview;
        try {
            tableview = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Events/Stat.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
