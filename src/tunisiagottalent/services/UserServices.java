
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
import tunisiagottalent.Entity.User;
import java.util.List;
import java.util.Random;
import org.mindrot.jbcrypt.BCrypt;
import tunisiagottalent.util.UserSession;

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
        cnx = DataSource.getInstance().getCnx();
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

    public User getUser(String username) {
        User u = new User();

        String req = "select * from user where username='" + username + "'";
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);

            while (rs.next()) {
                u.setId(rs.getInt("id"));
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
                u.setRole(rs.getString("roles"));

                return u;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return u;
    }

    public boolean delete(User u) {

        UserServices us = new UserServices();

        String deleteVideos = "delete from video where owner = " + u.getId();
        String req = "delete from user where username='" + u.getUsername() + "'";

        try {
            cnx.createStatement().executeUpdate(deleteVideos);
            System.out.println("Videos of user '" + u.getUsername() + "' successfully deleted !");
            cnx.createStatement().executeUpdate(req);
            System.out.println("User '" + u.getUsername() + "' successfully deleted !");

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    public List<User> getAll(String q) {

        String req;
        if (q.equals("")) {
            req = "select * from user ";
        } else {

            req = "select * from user where username like '" + q + "%'";
        }

        List<User> list = new ArrayList<>();

        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new User(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("sexe"),
                        rs.getString("adresse"),
                        rs.getString("name"),
                        rs.getString("first_name"),
                        rs.getString("telephone_number"),
                        rs.getString("bio"),
                        rs.getString("roles"),
                        rs.getDate("birthday"),
                        rs.getString("profile_pic")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public List<User> getAll() {
        String req = "select * from user";
        List<User> list = new ArrayList<>();

        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new User(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("sexe"),
                        rs.getString("adresse"),
                        rs.getString("name"),
                        rs.getString("first_name"),
                        rs.getString("telephone_number"),
                        rs.getString("bio"),
                        rs.getString("roles"),
                        rs.getDate("birthday"),
                        rs.getString("profile_pic")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

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

    public User getByUsername(String username) {
        User u = null;
        cnx = DataSource.getInstance().getCnx();
        String req = "Select * from user where username='" + username + "' ";
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                u = (new User(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("sexe"),
                        rs.getString("adresse"),
                        rs.getString("name"),
                        rs.getString("first_name"),
                        rs.getString("telephone_number"),
                        rs.getString("bio"),
                        rs.getString("roles"),
                        rs.getDate("birthday"),
                        rs.getString("profile_pic")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return u;
    }

    public String tokenGenerator() {
        Random rnd = new Random();
        String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String token = "";
        for (int i = 0; i < 5; i++) {
            token += alphanumeric.charAt(rnd.nextInt(alphanumeric.length()));
        }
//        System.out.println(token);
        return token;
    }

    public void updateToken(String username, String token) {
        String req = "update user set passwordToken =? where username =?";
        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1, token);
            pst.setString(2, username);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String encryptPassword(String pwd) {
        String pwd2 = BCrypt.hashpw(pwd, BCrypt.gensalt(13));
        pwd2 = pwd2.substring(3);
        pwd2 = "$2y" + pwd2;
//        System.out.println(pwd2);
        return pwd2;
    }

    public String getToken(String username) {
        String sql = "select passwordToken from user where username = '" + username + "'";
        String token = "";
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(sql);
            while (rs.next()) {
                token = rs.getString("passwordToken");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return token;
    }

    public boolean upadtePassword(String username, String pwd) {
        String req = "update user set password=? where username =?";
        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1, encryptPassword(pwd));
            pst.setString(2, username);
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean updateUser(User u) {
        String req = "update user set email=? , email_canonical=? , password=? , Birthday=? , sexe=? , telephone_number=? , adresse=? , name=? , first_name=? , bio=?, profile_pic=? where username='" + u.getUsername() + "'";
        try {
            UserServices us = new UserServices();
            UserSession s = UserSession.instance;
            pst = cnx.prepareStatement(req);
            //if email field is empty is null
            if (u.getEmail() == null) {
                //use value stored in database
                pst.setString(1, s.getU().getEmail());
                pst.setString(2, s.getU().getEmail());
            } else {
                //use new value
                pst.setString(1, u.getEmail());
                pst.setString(2, u.getEmail());
            }
            if (u.getPassword().equals("")) {
                pst.setString(3, s.getU().getPassword());
            } else {
                pst.setString(3, encryptPassword(u.getPassword()));
            }
            if (u.getBirthday() == null) {
                pst.setDate(4, s.getU().getBirthday());
            } else {
                pst.setDate(4, u.getBirthday());
            }
            if (u.getGender() == null) {
                if (s.getU().getGender() == null) {
                    pst.setString(5, null);
                } else {

                    pst.setString(5, s.getU().getGender().substring(0, 1).toLowerCase() + s.getU().getGender().substring(1));
                }
            } else {
                pst.setString(5, u.getGender().substring(0, 1).toLowerCase() + u.getGender().substring(1));
            }
            if (u.getPhone_number().equals("")) {
                pst.setString(6, s.getU().getPhone_number());
            } else {
                pst.setString(6, u.getPhone_number());
            }
            if (u.getAddress().equals("")) {
                pst.setString(7, s.getU().getAddress());
            } else {
                pst.setString(7, u.getAddress());
            }
            if (u.getLastName().equals("")) {
                pst.setString(8, s.getU().getLastName());
            } else {
                pst.setString(8, u.getLastName());
            }
            if (u.getName().equals("")) {
                pst.setString(9, s.getU().getName());
            } else {
                pst.setString(9, u.getName());
            }
            if (u.getBio().equals("")) {
                pst.setString(10, s.getU().getBio());
            } else {
                pst.setString(10, u.getBio());
            }
            if(u.getProfilePic().equals("")){
                pst.setString(11,s.getU().getProfilePic());
            }else{
                pst.setString(11, u.getProfilePic());
            }
            if (u.getUsername().equals("")) {
                System.out.println("User not found !");
                return false;
            } else {

                pst.executeUpdate();
            }

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
