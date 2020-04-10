/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Event;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tunisiagottalent.util.DataSource;
import views.AddEventController;



/*
 *
 * @author hela
 */
public class EventService {
    private Connection cnx;
    private Statement ste;
    private ResultSet rs;
    private int nb1,nb2,nb3,nb4;
    private PreparedStatement pst;
    public EventService() {
    cnx=DataSource.getInstance().getCnx();
    }
    
    
   
     public void addEvent(Event e)
    {
        String req="insert into event (title,start_date,end_date,img,location,nb_places,description,type)"+" values('"+e.getTitle()+"','"+e.getStart_date()+"','"+e.getEnd_date()+"','"+e.getImg()+"','"+e.getLocation()+"',"+e.getNb_places()+",'"+e.getDescription()+"','"+e.getType()+"')";
        try {                                                                                                                                                                                              
            ste=cnx.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     
     public ObservableList<Event>afficherEvent(){
        String req = "select * from event";
        ObservableList<Event> oblist = FXCollections.observableArrayList();
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                oblist.add(new Event(rs.getInt("id"),rs.getString("title"),rs.getDate("start_date"),rs.getDate("end_date"),rs.getString("img"),rs.getString("location"),rs.getInt("nb_places"),rs.getString("description"),rs.getString("type")));
                                     
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return oblist;
     }
     
     public Event findEvent(int eid)
{
	Event E = null;
	String q = "SELECT * FROM event WHERE id="+eid;
	
        try {
           ResultSet R = ste.executeQuery(q);
           if (R.next())
	{
		new Event(rs.getString(2),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getString(8),rs.getString(9));
	}
	
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
     return E;	
}
     
     
     
     
     public int castingType() throws SQLException
     {
        String req="SELECT count(id) from event where type ='casting';";
           ste=cnx.createStatement();
          rs= ste.executeQuery(req);
         
          while(rs.next())
          {
         nb1=rs.getInt(1);
          }
         return nb1;
     } 
     
      public int concertType() throws SQLException
     {
        String req="SELECT count(id) from event where type ='concert';";
           ste=cnx.createStatement();
          rs= ste.executeQuery(req);
         
          while(rs.next())
          {
         nb2=rs.getInt(1);
          }
         return nb2;
     } 
      
       public int auditionType() throws SQLException
     {
        String req="SELECT count(id) from event where type ='audition';";
           ste=cnx.createStatement();
          rs= ste.executeQuery(req);
         
          while(rs.next())
          {
         nb3=rs.getInt(1);
          }
         return nb3;
     }
       
        public int offreType() throws SQLException
     {
        String req="SELECT count(id) from event where type ='offre emploi';";
           ste=cnx.createStatement();
          rs= ste.executeQuery(req);
         
          while(rs.next())
          {
         nb4=rs.getInt(1);
          }
         return nb4;
     } 
     
     
     
   
     
    
     public List<Event> getAll()
    {
        String req="select * from event";
        List<Event> le =null;
     
        try {
            ste=cnx.createStatement();
            rs=ste.executeQuery(req);
            le = new ArrayList<>();
            while(rs.next())
            {
                /*rs.getDate(2),rs.getDate(3),*/
                //String title, String img, String location, int nb_places, String description, String type
                le.add(new Event(rs.getString(2),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getString(8),rs.getString(9)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return le;
    }
     
     
   
    public void updateEvent(Event E) throws SQLException
{
  
      String q ="update event set title='"+E.getTitle()+"',start_date='"+E.getStart_date()+"',end_date ='"+E.getEnd_date()+"', img = '"+E.getImg()+"',location ='"+E.getLocation()+"',nb_places="+E.getNb_places()+",description ='"+E.getDescription()+"', type='"+E.getType()+"' where id ="+E.getId()+";";
       
      try {
            ste=cnx.createStatement();
            ste.executeUpdate(q);
          }
      catch (SQLException ex)
          {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
          }    
            
}
  
    
 
 public void cancelEvent(int id) throws SQLException
{
     String Q = "DELETE FROM Event WHERE id="+id;
     try {
            ste=cnx.createStatement();
            ste.executeUpdate(Q);
          }
      catch (SQLException ex)
          {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
          }    
}
 

  
}
