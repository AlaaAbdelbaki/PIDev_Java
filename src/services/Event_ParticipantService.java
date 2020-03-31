/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Event_Participant;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    }
    
    public void addEvent_Participant(Event_Participant ep) 
    {
        String req ="insert into event_participant (event_id,user_id,participation_date	) values("+ep.getEvent_id()+","+ep.getUser_id()+",'"+ep.getParticipation_date()+"')";
          try{  
            ste=connexion.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
     public List<Event_Participant> getAll()
    {
        String req="select * from event_participant";
        
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
    
}
