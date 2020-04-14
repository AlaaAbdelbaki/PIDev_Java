/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.Entity;




/**
 *
 * @author hela
 */
public class Ticket {

    public int id;
    public float price;
    public int event_id;
   
    
    

    public Ticket() {
    }

  

    public Ticket(int id, float price, int event_id) {
        this.id = id;
        this.price = price;
        this.event_id = event_id;
    }

    public Ticket(float price, int event_id) {
        this.price = price;
        this.event_id = event_id;
    }

    public int getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getInfo() {
        return this.price + ", " + this.event_id;
    }

}
