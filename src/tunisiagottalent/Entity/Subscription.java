/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.Entity;

import java.sql.Date;

/**
 *
 * @author alaa
 */
public class Subscription {
    int id;
    Date subscription_date;
    int subetto_id;
    int sub_id;

    public Subscription() {
    }
    
    public Subscription(int id, Date subscription_date, int subetto_id, int sub_id) {
        this.id = id;
        this.subscription_date = subscription_date;
        this.subetto_id = subetto_id;
        this.sub_id = sub_id;
    }

    public int getId() {
        return id;
    }

    public Date getSubscription_date() {
        return subscription_date;
    }

    public int getSubetto_id() {
        return subetto_id;
    }

    public int getSub_id() {
        return sub_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSubscription_date(Date subscription_date) {
        this.subscription_date = subscription_date;
    }

    public void setSubetto_id(int subetto_id) {
        this.subetto_id = subetto_id;
    }

    public void setSub_id(int sub_id) {
        this.sub_id = sub_id;
    }

    
    
    
}
