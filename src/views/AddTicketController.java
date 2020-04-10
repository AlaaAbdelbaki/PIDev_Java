/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Ticket;
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
import javafx.scene.Scene;

import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javafx.stage.Stage;
import services.TicketService;

/**
 * FXML Controller class
 *
 * @author hela
 */
public class AddTicketController implements Initializable {

    @FXML
    private TextField tfprice;
    @FXML
    private TextField tfevent_id;
    @FXML
    private TableColumn<Ticket, String> col_idT;
    @FXML
    private TableColumn<Ticket, String> col_priceT;
    @FXML
    private TableColumn<Ticket, String> col_eventId;
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
        col_eventId.setCellValueFactory(new PropertyValueFactory<>("event_id"));
        

        //set data to tableview:
        textV.setItems(ts.afficher());
        //  edittableCols();

        //select multiple rows at once:
        textV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

   
    @FXML
    private void AddTicket(ActionEvent event) {

        String price = tfprice.getText();
        String event_id = tfevent_id.getText();

        TicketService ts = new TicketService();
        Float p = Float.parseFloat(price);
        int id = Integer.parseInt(event_id);

        Ticket t = new Ticket(p, id);
        ts.addTicket(t);

        javafx.scene.Parent tableview = null;
        try {
            tableview = FXMLLoader.load(getClass().getResource("AddTicket.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AddTicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
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
        String event_id = tfevent_id.getText();
        

        //creer instance
        
        int e_id = Integer.parseInt(event_id);
        float t_price=Float.valueOf(price);
        Ticket t=new Ticket(t_price,e_id);

        //update event on the view:
        int l = textV.getSelectionModel().getSelectedIndex();
        textV.getItems().set(l, t);

            ts.updateTicket(t);
       
    }

    @FXML
    private void show_details(MouseEvent event) {
       
        
        Ticket t=textV.getSelectionModel().getSelectedItem();
        
        tfprice.setText(String.valueOf(t.getPrice()));
        tfevent_id.setText(String.valueOf(t.getEvent_id()));
        
        
    }

 
}
