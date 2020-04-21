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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import tunisiagottalent.Entity.Article;
import tunisiagottalent.UI.Shop.AddProductController;
import tunisiagottalent.services.EventService;
import tunisiagottalent.services.TicketService;

/**
 * FXML Controller class
 *
 * @author hela
 */
public class AddEventController implements Initializable {

    @FXML
    private ComboBox<String> type_id;
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
    private TableColumn<Event, Void> col_img;
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
    @FXML
    private Button btn_update;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_cancel;
    @FXML
    private TableColumn<Event, Void> col_ticket;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type_id.getItems().addAll("audition", "casting", "concert", "offre emploi");
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_SDate.setCellValueFactory(new PropertyValueFactory<>("start_date"));
        col_EDate.setCellValueFactory(new PropertyValueFactory<>("end_date"));

        col_location.setCellValueFactory(new PropertyValueFactory<>("location"));
        col_nbPlaces.setCellValueFactory(new PropertyValueFactory<>("nb_places"));
        col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        Callback<TableColumn<Event, Void>, TableCell<Event, Void>> cellfactoryimage = (param) -> {
            final TableCell<Event, Void> cell = new TableCell<Event, Void>() {
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Event pa = getTableView().getItems().get(getIndex());
                        Label lb = new Label();
                        ImageView product_image_view = new ImageView();
                        product_image_view.setImage(new Image("http://127.0.0.1:8000/assets/img/shop-img/" + pa.getImg()));
                        product_image_view.setFitHeight(100);
                        product_image_view.setFitWidth(100);
                        lb.setGraphic(product_image_view);
                        setGraphic(lb);
                        setText(null);
                    }

                }
            ;

            };
            
            return cell;
        };
        Callback<TableColumn<Event, Void>, TableCell<Event, Void>> cellFactory1 = (param) -> {
            final TableCell<Event, Void> cell = new TableCell<Event, Void>() {
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        TicketService ts=new TicketService();
                         Button ticketButton = new Button("Create Ticket");
                        Event pa = getTableView().getItems().get(getIndex());
                        if(ts.find(pa)){ticketButton.setDisable(true);
                        ticketButton.setText("Ticket Created!");
                        ticketButton.setStyle("-fx-background-color:orange;");}
                        ticketButton.setOnAction(event ->{
                            
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateTicket.fxml"));
                                Parent second = loader.load();
                                
                                CreateTicketController secondcontroller = loader.getController();
                                secondcontroller.setE(pa);
                                
                                Scene s = new Scene(second);
                                Stage stageedit = new Stage();
                                stageedit.initStyle(StageStyle.TRANSPARENT);
                                stageedit.setOpacity(0.95);
                                stageedit.setTitle("add ticket n° : "+pa.getId());
                                stageedit.initOwner(EventScene_id.getScene().getWindow());
                                stageedit.setScene(s);
                                
                                stageedit.show();
                                
                            } catch (IOException ex) {
                                Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                             
                        });
                        
                        setGraphic(ticketButton);
                       
                        
                    }
                    
                };
                
            };
            
            return cell;
        };
        col_ticket.setCellFactory(cellFactory1);
        col_img.setCellFactory(cellfactoryimage);
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
        if (titleE_id.getText().isEmpty() | img_id.getText().isEmpty() | nb_places_id.getText().isEmpty() | loc_id.getText().isEmpty() | desc_id.getText().isEmpty() | type_id.getValue().isEmpty()) {
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
        String img = imgp;
        String location = loc_id.getText();
        String nb_places = nb_places_id.getText();
        String description = desc_id.getText();
        String type = type_id.getValue();
        if (ValidateFields() && ValidateDate()) {
            EventService es = new EventService();
            Date sd = convertToDateViaSqlDate(start_date);
            Date ed = convertToDateViaSqlDate(end_date);
            int nb = Integer.parseInt(nb_places);
            Event e = new Event(title, sd, ed, img, location, nb, description, type);
            es.addEvent(e);

            //copying added img :
            try {

                Files.copy(Paths.get(imguriUri), Paths.get("D:\\Projects\\PIDev\\web\\assets\\img\\shop-img\\" + imgp), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Table_event.getItems().add(e);
            Table_event.refresh();

        }

        clearFields();
    }

    //update fnt:
    @FXML
    private void update_event(ActionEvent event) throws SQLException {

        //recuperer
        String title = titleE_id.getText();
        LocalDate start_date = startD_id.getValue();
        LocalDate end_date = endD_id.getValue();
        String img = img_id.getText();
        String location = loc_id.getText();
        String nb_places = nb_places_id.getText();
        String description = desc_id.getText();
        String type = type_id.getValue();

        //creer instance
        Date sd = convertToDateViaSqlDate(start_date);
        Date ed = convertToDateViaSqlDate(end_date);
        int nb = Integer.parseInt(nb_places);
         //copying added img :
            try {

                Files.copy(Paths.get(imguriUri), Paths.get("D:\\Projects\\PIDev\\web\\assets\\img\\shop-img\\" + imgp), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        Event e = new Event(Table_event.getSelectionModel().getSelectedItem().getId(),title, sd, ed, img, location, nb, description, type);

        //update event on the view:
        int l = Table_event.getSelectionModel().getSelectedIndex();
        Table_event.getItems().set(l, e);

       
            es.updateEvent(e);
       
        clearFields();

    }

    //changing to ticket scene:
    @FXML
    private void loadTicketScene(ActionEvent event) throws IOException {
        Parent tableview = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Events/AddTicket.fxml"));

        EventScene_id.getChildren().clear();
        EventScene_id.getChildren().addAll(tableview);
    }

    //get selected ligne info and set them on the text fieldText:
    @FXML
    private void showDetails(MouseEvent event) {
        btn_cancel.setVisible(true);
        btn_add.setVisible(false);
        btn_delete.setVisible(true);
        btn_update.setVisible(true);
        Event E = Table_event.getSelectionModel().getSelectedItem();
        titleE_id.setText(E.getTitle());
        startD_id.setValue(E.getStart_date().toLocalDate());
        endD_id.setValue(E.getEnd_date().toLocalDate());
        img_id.setText(E.getImg());
        loc_id.setText(E.getLocation());
        imgview.setImage(new Image("http://127.0.0.1:8000/assets/img/shop-img/" + E.getImg()));
        nb_places_id.setText(String.valueOf(E.getNb_places()));
        desc_id.setText(E.getDescription());
        type_id.setValue(E.getType());
    }

    //insert img:
    @FXML
    private void insererimg(ActionEvent event) throws FileNotFoundException {

        FileChooser fc = new FileChooser();
        File selectedfile = fc.showOpenDialog(null);
        if (selectedfile != null) {
            imgurl = selectedfile.toURI().toString();

            img_id.setText(selectedfile.getName());
            Image image = new Image(imgurl);
            imgview.setImage(image);
            imguriUri = selectedfile.toURI();
            imgp = selectedfile.getName();
        }
    }

    //to clear textfields:
    @FXML
    private void clearFields() {
        type_id.setValue(null);
        titleE_id.clear();

        img_id.clear();
        loc_id.clear();
        nb_places_id.clear();
        desc_id.clear();
        imgview.setImage(null);
        startD_id.setValue(null);
        endD_id.setValue(null);
        btn_add.setVisible(true);
        btn_delete.setVisible(false);
        btn_update.setVisible(false);
        btn_cancel.setVisible(false);

    }

    @FXML
    private void loadStatScene(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tunisiagottalent/UI/Events/Stat.fxml"));
            Parent third = loader.load();
            Scene s = new Scene(third);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setOpacity(1);
            stage.setTitle("Stats");
            stage.initOwner(EventScene_id.getScene().getWindow());
            stage.setScene(s);

            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
