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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import tunisiagottalent.util.DataSource;
import tunisiagottalent.entity.User;
import java.util.List;
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

    //Verify entered password with the hashed password inside the database
    public boolean checkPassword(String passwordText, String DbHash) {
        boolean password_verified = false;
        if (null == DbHash || !DbHash.startsWith("$2a$")) {
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        }
        password_verified = BCrypt.checkpw(passwordText, DbHash);
        return (password_verified);
    }

    //Encrypt entered password to the required value in the database
    public String encryptPassword(String pwd) {
        String pwd2 = BCrypt.hashpw(pwd, BCrypt.gensalt(13));
        pwd2 = pwd2.substring(3);
        pwd2 = "$2y" + pwd2;
        System.out.println(pwd2);
        return pwd2;
    }

    //login
    public int login(String user, String pwd) {
        String pass = "";

        cnx = DataSource.getInstance().getCnx();
        String req = "select password from user where user.username='" + user + "'";
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);
            if (rs.next()) {
                pass = rs.getString("password");
//                System.out.println(pass);
                pass = pass.substring(3);
                pass = "$2a" + pass;
            }

//            System.out.println(pass);
        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!pass.equals("")) {
            if (checkPassword(pwd, pass)) {
                System.out.println("Passwords Match !");
                return 1;
            }

        } else {
            System.out.println("User not found !");
            return -1;
        }
        System.out.println("Passwords Mismatch !!");
        return 0;
    }

    //signup
    public boolean signup(User u) {
        String username = "";
        String pwd = u.getPassword();
        cnx = DataSource.getInstance().getCnx();

        String find = "select username from user where user.username ='" + u.getUsername() + "'";
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(find);
            if (rs.next()) {
                username = rs.getString("username");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(username);
        if (username.equals(u.getUsername())) {
            System.out.println("Username taken !");
            return false;
        } else {
            pwd = BCrypt.hashpw(pwd, BCrypt.gensalt(13));
            pwd = pwd.substring(3);
            pwd = "$2y" + pwd;
            u.setPassword(pwd);
            //System.out.println(pwd);
            String req = "insert into user(username,password,username_canonical,email,email_canonical,enabled,roles) values('" + u.getUsername() + "','" + u.getPassword() + "','" + u.getUsername() + "','" + u.getEmail() + "','" + u.getEmail() + "',1,'a:0:{}')";
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

    //Delete user
    public boolean delete(String username) {

        cnx = DataSource.getInstance().getCnx();

        String req = "delete from user where username='" + username + "'";

        try {
            cnx.createStatement().executeUpdate(req);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    //Returns list of all users
    public List<User> getAll() {
        String req = "select * from user";
        List<User> list = new ArrayList<>();

        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new User(rs.getString("username"), rs.getString("email"), rs.getString("sexe"), rs.getString("adresse"), rs.getString("name"), rs.getString("first_name"), rs.getString("telephone_number")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    //Returns user role
    public String getRole(String username) {
        cnx = DataSource.getInstance().getCnx();
        String ch = "";
        String req = "Select roles from user where username='" + username + "' ";
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                ch = rs.getString("roles");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ch;
    }

    //Returns Current User
    public User getUser(String username) {
        User u = new User();
        cnx = DataSource.getInstance().getCnx();
        String req = "select * from user where username='" + username + "'";
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);

            while (rs.next()) {
                u.setUsername(rs.getString("username"));
                u.setName(rs.getString("first_name"));
                u.setLastName(rs.getString("name"));
                u.setBio(rs.getString("bio"));
                u.setAddress(rs.getString("adresse"));
                u.setGender(rs.getString("sexe"));
                u.setEmail(rs.getString("email"));
                u.setPhone_number(rs.getString("telephone_number"));
                u.setName(rs.getString("first_name"));
                u.setLastName(rs.getString("name"));
                u.setBirthday(rs.getDate("Birthday"));
                u.setPassword(rs.getString("password"));
                return u;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    //Updates Users
    public boolean updateUser(User u) {
        cnx = DataSource.getInstance().getCnx();
        String req = "update user set email=? , email_canonical=? , password=? , Birthday=? , sexe=? , telephone_number=? , adresse=? , name=? , first_name=? , bio=? where username='" + u.getUsername() + "'";
        try {
            UserServices us = new UserServices();
            pst = cnx.prepareStatement(req);
            //if email field is empty is null
            if (u.getEmail() == null) {
                //use value stored in database
                pst.setString(1, us.getUser(u.getUsername()).getEmail());
                pst.setString(2, us.getUser(u.getUsername()).getEmail());
            } else {
                //use new value
                pst.setString(1, u.getEmail());
                pst.setString(2, u.getEmail());
            }
            if (u.getPassword().equals("")) {
                pst.setString(3, us.getUser(u.getUsername()).getPassword());
            } else {
                pst.setString(3, encryptPassword(u.getPassword()));
            }
            if (u.getBirthday() == null) {
                pst.setDate(4, us.getUser(u.getUsername()).getBirthday());
            } else {
                pst.setDate(4, u.getBirthday());
            }
            if (u.getGender() == null) {
                if (us.getUser(u.getUsername()).getGender() == null) {
                    pst.setString(5, null);
                } else {

                    pst.setString(5, us.getUser(u.getUsername()).getGender().substring(0, 1).toLowerCase() + us.getUser(u.getUsername()).getGender().substring(1));
                }
            } else {
                pst.setString(5, u.getGender().substring(0, 1).toLowerCase() + u.getGender().substring(1));
            }
            if (u.getPhone_number().equals("")) {
                pst.setString(6, us.getUser(u.getUsername()).getPhone_number());
            } else {
                pst.setString(6, u.getPhone_number());
            }
            if (u.getAddress().equals("")) {
                pst.setString(7, us.getUser(u.getUsername()).getAddress());
            } else {
                pst.setString(7, u.getAddress());
            }
            if (u.getLastName().equals("")) {
                pst.setString(8, us.getUser(u.getUsername()).getLastName());
            } else {
                pst.setString(8, u.getLastName());
            }
            if (u.getName().equals("")) {
                pst.setString(9, us.getUser(u.getUsername()).getName());
            } else {
                pst.setString(9, u.getName());
            }
            if (u.getBio().equals("")) {
                pst.setString(10, us.getUser(u.getUsername()).getBio());
            } else {
                pst.setString(10, u.getBio());
            }
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
