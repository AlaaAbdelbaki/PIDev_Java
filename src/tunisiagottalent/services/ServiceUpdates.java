/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.services;

import tunisiagottalent.Entity.Updates;
import tunisiagottalent.util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;


/**
 *
 * @author 
 */
public class ServiceUpdates implements IService<Updates>{
    
    Connection cnx = DataSource.getInstance().getCnx();
    
        @Override
    public void ajouter(Updates a) {
        try {
           
            Statement stm = cnx.createStatement();
            String query = "INSERT INTO updates (id,title,img,content,category,publish_date) VALUES (NULL, '"+a.getTitle()+"', '"+a.getImg()+"', '"+a.getContent()+"','"+a.getCategory()+"','"+a.getPublish_date()+"')";
            stm.executeUpdate(query);
           // System.out.println("updates ajouté !");

            Image img = new Image("img/vrai.jpg", 50, 50, false, false);
			Notifications notificationBuilder;
            notificationBuilder = Notifications.create().title("download completed").text("saved")
                    .graphic(new ImageView(img)).hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT);
			notificationBuilder.show();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        
        }
        
    }

    @Override
    public void supprimer(Updates t) {
        try {
            String requete = "DELETE FROM updates WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,t.getId());
            pst.executeUpdate();
            System.out.println("updates supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    
    }

    @Override
    public void modifier(Updates t) {
        
            try {
            String requete = "UPDATE updates SET Title=?,img=?,category=?,publish_date=?,content=? WHERE id=?";
                   
            PreparedStatement pst = cnx.prepareStatement(requete);   
            pst.setString(1, t.getTitle());
            pst.setString(2, t.getImg());
            pst.setString(3, t.getCategory());
            pst.setDate(4,t.getPublish_date());
            pst.setString(5, t.getContent());
            pst.setInt(6,t.getId());
            pst.executeUpdate();
            System.out.println("Updates modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Updates> afficher() {
        
        List<Updates> list = new  ArrayList<>();
        String req = "select * from updates";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs =  pst.executeQuery();
            while(rs.next()){
                Updates  a = new Updates(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getDate(5),rs.getString(6));
                list.add(a);
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        } 
        return list;
    }    

    

    
}



    

