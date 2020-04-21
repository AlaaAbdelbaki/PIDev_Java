/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Events;

import tunisiagottalent.Entity.Ticket;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

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
import javafx.scene.control.Button;

import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import tunisiagottalent.Entity.Event;
import tunisiagottalent.services.TicketService;

/**
 * FXML Controller class
 *
 * @author hela
 */
public class AddTicketController implements Initializable {
@FXML
    private AnchorPane rootpane;
    @FXML
    private TextField tfprice;
    @FXML
    private TextField tfevent_id;
    @FXML
    private TableColumn<Ticket, String> col_idT;
    @FXML
    private TableColumn<Ticket, String> col_priceT;
    @FXML
    private TableColumn<Ticket, Void> col_eventId;
    @FXML
    private TableView<Ticket> textV;

    //zedt hedha
    public static ObservableList<Ticket> obTicket;
    TicketService ts = new TicketService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //set properties to tableview columns:
        col_idT.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_priceT.setCellValueFactory(new PropertyValueFactory<>("price"));
       
        Callback<TableColumn<Ticket, Void>, TableCell<Ticket, Void>> cellFactory1 = (param) -> {
            final TableCell<Ticket, Void> cell = new TableCell<Ticket, Void>() {
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        TicketService ts=new TicketService();
                         Button ticketButton = new Button("Details");
                         ticketButton.setStyle("-fx-background-color:orange;");
                        Ticket pa = getTableView().getItems().get(getIndex());
                        
                        ticketButton.setOnAction(event ->{
                            
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("EventDetails.fxml"));
                                Parent second = loader.load();
                                
                                EventDetailsController secondcontroller = loader.getController();
                                secondcontroller.setT(pa);
                                
                                Scene s = new Scene(second);
                                s.setFill(Color.TRANSPARENT);
                                Stage stageedit = new Stage();
                                stageedit.initStyle(StageStyle.TRANSPARENT);
                                stageedit.setOpacity(0.95);
                                stageedit.setTitle("Event Details");
                                stageedit.initOwner(rootpane.getScene().getWindow());
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
        col_eventId.setCellFactory(cellFactory1);
        //set data to tableview:
        textV.setItems(ts.afficher());
        //  edittableCols();

        //select multiple rows at once:
        textV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }


    @FXML
    private void deleteTicket(ActionEvent event) {

        ObservableList<Ticket> selectedRows, allRows;
        allRows = textV.getItems();
        selectedRows = textV.getSelectionModel().getSelectedItems();
        for (Ticket ticket : selectedRows) {
            allRows.remove(ticket);
            ts.cancelTicket(ticket.id);
        }
    }

    @FXML
    private void UpdateTicket(ActionEvent event) {
        //recuperer
        String price = tfprice.getText();
       

        
        float t_price = Float.valueOf(price);
        Ticket t = new Ticket(textV.getSelectionModel().getSelectedItem().getId(),t_price, textV.getSelectionModel().getSelectedItem().getEvent_id());

        //update event on the view:
        int l = textV.getSelectionModel().getSelectedIndex();
        textV.getItems().set(l, t);

        ts.updateTicket(t);

    }

    @FXML
    private void show_details(MouseEvent event) {

        Ticket t = textV.getSelectionModel().getSelectedItem();

        tfprice.setText(String.valueOf(t.getPrice()));
       

    }

}
