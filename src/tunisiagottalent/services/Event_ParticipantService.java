/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.services;

import tunisiagottalent.Entity.Event_Participant;
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
import javafx.scene.chart.PieChart;
import tunisiagottalent.util.DataSource;

/**
 *
 * @author hela
 */
public class Event_ParticipantService {
    
    private Connection connexion;
    private Statement ste;
    private ResultSet rs;
            

    public Event_ParticipantService() {
        connexion=DataSource.getInstance().getCnx();
        try {
            ste=connexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Event_ParticipantService.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    public void addEvent_Participant(Event_Participant ep) 
    {
        String req ="insert into event_participant (event_id,user_id,participation_date) values("+ep.getEvent_id()+","+ep.getUser_id()+",'"+ep.getParticipation_date()+"')";
          try{  
            ste=connexion.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
     public List<Event_Participant> getAll()
    {
        String req="select * from event_participant ";
               req+="inner join event where event.id=event_participant.event_id";
               req+="inner join user where user.id = event_participant.user.id";
        
        List<Event_Participant> l_ep =new ArrayList<>();
        try {
            ste=connexion.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next())
            {
           
                l_ep.add(new Event_Participant(rs.getInt("event_id"),rs.getInt("user_id"),rs.getDate("participation_date")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Event_ParticipantService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return l_ep;
    }
     
     
     
      public void updateEvent_participant(Event_Participant ep) 
{
    
     String req="update Event_Participant set event_id = "+ep.getEvent_id()+",user_id = "+ep.getUser_id()+",participation_date = '"+ep.getParticipation_date()+"' where id="+ep.getId()+";";
      try {
            ste=connexion.createStatement();
            ste.executeUpdate(req);
            
          }
      catch (SQLException ex)
          {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
          }    

   
}
      
      
       public void cancelEvent_Participation(int id) 
{
     String req ="delete from event_participant where id="+id;         
     try {
            ste=connexion.createStatement();
            ste.executeUpdate(req);
            
          }
      catch (SQLException ex)
          {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
          }    
}
    public ObservableList<PieChart.Data> pieChartEvent(){
         
      String req = "select  event.title ,COUNT(event_participant.id) from event_participant inner join event on (event.id=event_participant.event_id) group by event_id ";
      
        ObservableList<PieChart.Data> list;
        list = FXCollections.observableArrayList();
        
        try {
            ste = connexion.createStatement();
            rs = ste.executeQuery(req);
            

            while (rs.next()) {
                list.add(new PieChart.Data(rs.getString(1),rs.getInt(2)));
            }
           
            
        } catch (SQLException ex) {
            Logger.getLogger(OrderServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
     }
}
