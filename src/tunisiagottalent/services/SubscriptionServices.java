/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import tunisiagottalent.Entity.Subscription;
import tunisiagottalent.Entity.User;
import tunisiagottalent.util.DataSource;

/**
 *
 * @author alaa
 */
public class SubscriptionServices {
    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public SubscriptionServices() {
         cnx = DataSource.getInstance().getCnx();
    }

    
    public Boolean exists(int subedto ,int sub ){
            String sql="select * from subscription where subetto_id = "+subedto+" and  sub_id="+sub;
            System.out.println(sql);
            Subscription subInfo = new Subscription();
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(sql);
            if(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean subscribe(Subscription s){
        String sql = "insert into subscription(subscription_date,subetto_id,sub_id) values('"+s.getSubscription_date()+"',"+s.getSubetto_id()+","+s.getSub_id()+")";
        
        try {
            ste = cnx.createStatement();
            ste.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean unsubscribe(Subscription s){
        String sql="delete from subscription where subetto_id="+s.getSubetto_id()+" and sub_id="+s.getSub_id();
        
        try {
            ste=cnx.createStatement();
            ste.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public int getsubs(User u)
    {
        String sql="select COUNT(id) from subscription where subetto_id="+u.getId();
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(sql);
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
