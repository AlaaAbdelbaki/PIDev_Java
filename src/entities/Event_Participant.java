/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author hela
 */
public class Event_Participant {
    public int id;
    public int event_id;
    public int user_id;
    public Date participation_date;

    public Event_Participant() {
    }

    public Event_Participant(int event_id, int user_id, Date participation_date) {
        this.event_id = event_id;
        this.user_id = user_id;
        this.participation_date = participation_date;
    }

    public Event_Participant(int id, int event_id, int user_id, Date participation_date) {
        this.id = id;
        this.event_id = event_id;
        this.user_id = user_id;
        this.participation_date = participation_date;
    }

    public Event_Participant(int event_id, int user_id) {
        this.event_id = event_id;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public Date getParticipation_date() {
        return participation_date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setParticipation_date(Date participation_date) {
        this.participation_date = participation_date;
    }

   public String getInfo()
    {
        return this.event_id+","+this.user_id;
             
    }

    @Override
    public String toString() {
        return "Event_Participant{" + "id=" + id + ", event_id=" + event_id + ", user_id=" + user_id + ", participation_date=" + participation_date + '}';
    }
    
    
}
