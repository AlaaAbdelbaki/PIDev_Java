/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent;

import entities.Event;
import entities.Event_Participant;
import entities.Ticket;
import java.sql.SQLException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import static javafx.application.Application.launch;
import services.EventService;
import services.Event_ParticipantService;
import services.TicketService;
import tunisiagottalent.util.DataSource;

/**
 *
 * @author alaa
 */
public class TunisiaGotTalent {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here

        DataSource ds1 = DataSource.getInstance();
        System.out.println(ds1);

        EventService es = new EventService();
        TicketService ts = new TicketService();
        Event_ParticipantService eps = new Event_ParticipantService();
        
        
        
        System.out.println(es.castingType());
        System.out.println(es.concertType());
        System.out.println(es.offreType());
        System.out.println(es.auditionType());
        System.out.println(java.time.LocalDate.now());

        /////////////////////////// to test add methode /////////////////////////// 
        /*
         String dte = "2007-07-07";
         Date dt =Date.valueOf(dte);
         String dte2 = "2008-08-08";
         Date dt2 =Date.valueOf(dte2);
         Event e1 =new Event("aaaaa",dt,dt2,"image","Jendouba",50,"bbb","casting");
         es.addEvent(e1);
         */
        //////////////////////////to test get all methode /////////////////////////// 
        /* 
         es.getAll().forEach(System.out::println);
         
         for (int i =0;i<es.getAll().size();i++)
            System.out.println(es.getAll().get(i).getInfo());
         */
        /////////////////////////to test update methode /////////////////////////// 
        /*
         String dte = "2010-10-10";
         Date dt =Date.valueOf(dte);
         String dte2 = "2011-11-11";
         Date dt2 =Date.valueOf(dte2);
         Event e2 = new Event("bbb",dt,dt2,"test","test",50,"test","test");
         e2.setId(20);
         es.updateEvent(e2);
         */
        ///////////////////////// to test cancel methode /////////////////////////
        /*
         es.cancelEvent(20);
        */
        ///////////////////////// to test add methode /////////////////////////
        /*
         Ticket t1 = new Ticket(100,14);
         ts.addTicket(t1);
         */
        ///////////////////////// to test getAll methode /////////////////////////
        /*
        for (int i =0;i<ts.getAll().size();i++)
            System.out.println(ts.getAll().get(i).getInfo());
         */
        ///////////////////////// to test update methode /////////////////////////
        /*
           Ticket t2 = new Ticket(200,14);
           t2.setId(34);
           ts.updateTicket(t2);
         */
        ///////////////////////// to test delete methode ///////////////////////// 
        /*
          ts.cancelTicket(34);
         */
        ///////////////////////// to test add methode /////////////////////////
        /*
         String dte = "2019-05-21";
         Date dt =Date.valueOf(dte);
         Event_Participant ep1 = new Event_Participant(12, 1, dt);
         eps.addEvent_Participant(ep1);
        */
        
         ///////////////////////// to test getAll methode /////////////////////////
        /*
         for (int i =0;i<eps.getAll().size();i++)
            System.out.println(eps.getAll().get(i).getInfo());
        */
        
         ///////////////////////// to test update methode /////////////////////////
        /*
         String dte = "2007-07-07";
         Date dt =Date.valueOf(dte);
         Event_Participant ep1 = new Event_Participant(12, 1, dt);
           ep1.setId(2);
           eps.updateEvent_participant(ep1);
        */
        
        ///////////////////////// to test delete methode ///////////////////////// 
        
         /* eps.cancelEvent_Participation(2);*/
       
        
    }

}
