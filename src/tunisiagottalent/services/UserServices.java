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
import org.mindrot.jbcrypt.BCrypt;

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
    
    public boolean checkPassword(String passwordText, String DbHash) {
    boolean password_verified = false;
    if (null == DbHash || !DbHash.startsWith("$2a$")) {
        throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
    }
    password_verified = BCrypt.checkpw(passwordText, DbHash);
    return (password_verified);
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
//                System.out.println(pass);
                pass = pass.substring(3);
                pass = "$2a"+pass;
            }
            
//            System.out.println(pass);
        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(pass!=""){
            if(checkPassword(pwd, pass)){
            System.out.println("Passwords Match !");
            return true;
        }
            
        }
        else{
                System.out.println("User not found !");
                return false;
            }
        System.out.println("Passwords Mismatch !!");
        return false;
    }
    
    public boolean signup(String user,String pwd){
        String username="";
        cnx = DataSource.getInstance().getCnx();
        
        String find = "select username from user where user.username ='"+user+"'";
        try {
            ste= cnx.createStatement();
            rs = ste.executeQuery(find);
            if(rs.next())
            {
                 username = rs.getString("username");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(username);
        if(username.equals(user)){
            System.out.println("Username taken !");
            return false;
        }
        else{
            pwd = BCrypt.hashpw(pwd, BCrypt.gensalt(13));
            pwd = pwd.substring(3);
            pwd = "$2y"+pwd;
            //System.out.println(pwd);
            String req = "insert into user(username,password,username_canonical,email,email_canonical,enabled,roles) values('"+user+"','"+pwd+"','"+user+"','"+user+"','"+user+"',1,'a:0:{}')";
            try {
                ste = cnx.createStatement();
                ste.executeUpdate(req);
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        
        
        return false;
    }
}
