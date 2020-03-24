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
import tunisiagottalent.util.DataSource;

/**
 *
 * @author alaa
 */
public class UserServices {
    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public UserServices() {
    }
    
    
    public boolean login(String user,String pwd){
        String pass="";
        cnx = DataSource.getInstance().getCnx();
        String req = "select password from user where user.username='"+user+"'";
        try {
            ste= cnx.createStatement();
            rs = ste.executeQuery(req);
            if(rs.next()){
                pass = rs.getString("password");
            }
            System.out.print(pass);
        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    
}
