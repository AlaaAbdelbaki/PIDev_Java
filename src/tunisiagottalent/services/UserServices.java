
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

    //login
    public int login(String user,String pwd){
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
        if(!pass.equals("")){
            if(checkPassword(pwd, pass)){
                System.out.println("Passwords Match !");
                return 1;
            }

        }
        else{
            System.out.println("User not found !");
            return -1;
        }
        System.out.println("Passwords Mismatch !!");
        return 0;
    }

    //signup
    public boolean signup(User u){
        String username="";
        String pwd = u.getPassword();
        cnx = DataSource.getInstance().getCnx();

        String find = "select username from user where user.username ='"+u.getUsername()+"'";
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
        if(username.equals(u.getUsername())){
            System.out.println("Username taken !");
            return false;
        }
        else{
            pwd = BCrypt.hashpw(pwd, BCrypt.gensalt(13));
            pwd = pwd.substring(3);
            pwd = "$2y"+pwd;
            u.setPassword(pwd);
           
            String req = "insert into user(username,password,username_canonical,email,email_canonical,enabled,roles) values('"+u.getUsername()+"','"+u.getPassword()+"','"+u.getUsername()+"','"+u.getEmail()+"','"+u.getEmail()+"',1,'a:0:{}')";
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

    public boolean delete(User u){
        return false;
    }

    public List<User> getAll(){
        String req = "select * from user";
        List<User> list = new ArrayList<>();

        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);
            while(rs.next()){
                list.add(new User(rs.getInt("id"),rs.getString("username"),rs.getString("email"),rs.getString("sexe"),rs.getString("adresse"),rs.getString("name"),rs.getString("first_name"),rs.getString("telephone_number"),rs.getString("roles")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public String getRole(String username){
        cnx = DataSource.getInstance().getCnx();
        String ch="";
        String req="Select roles from user where username='"+username+"' ";
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);
            while(rs.next()){
                ch=rs.getString("roles");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ch;
    }
    public User getByUsername(String username){
        User u=null;
        cnx = DataSource.getInstance().getCnx();
        String req="Select * from user where username='"+username+"' ";
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);
            while(rs.next()){
                u=new User(rs.getInt("id"),rs.getString("username"),rs.getString("email"),rs.getString("sexe"),rs.getString("adresse"),rs.getString("name"),rs.getString("first_name"),rs.getString("telephone_number"),rs.getString("roles"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return u;
    }
}

