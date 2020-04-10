/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Ticket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import tunisiagottalent.util.DataSource;
import views.AddTicketController;

/**
 *
 * @author hela
 */
public class TicketService {

    private Connection connexion;
    private Statement ste;
    private ResultSet rs;

    public TicketService() {
        connexion = DataSource.getInstance().getCnx();
    }

    public void addTicket(Ticket t) {
        String req = "insert into ticket (price,event_id) values(" + t.getPrice() + "," + t.getEvent_id() + ")";
        try {
            ste = connexion.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Ticket> getAll() {
        String req = "select * from ticket";
        List<Ticket> lt = new ArrayList<>();
        try {
            ste = connexion.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {

                lt.add(new Ticket(rs.getFloat(1), rs.getInt(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lt;
    }
    
    public ObservableList<Ticket> afficher(){
       
        String req = "select * from ticket";
        ObservableList<Ticket> oblist = FXCollections.observableArrayList();
        try {
            ste = connexion.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                oblist.add(new Ticket(rs.getInt("id"), rs.getFloat("price"), rs.getInt("event_id")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(AddTicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return oblist;
    }
    
    
    

    public void updateTicket(Ticket t) {

        String req = "update ticket set price = " + t.getPrice() + ",event_id = " + t.getEvent_id() + " where id=" + t.getId() + ";";
        try {
            ste = connexion.createStatement();
            ste.executeUpdate(req);

        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cancelTicket(int id) {
        String req = "delete from Ticket where id=" + id;
        try {
            ste = connexion.createStatement();
            ste.executeUpdate(req);

        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
