/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.services;

import tunisiagottalent.Entity.Article;
import tunisiagottalent.util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.controlsfx.control.Notifications;


/**
 *
 * @author ASUS
 */
public class ServiceArticle implements IService<Article>{
    
    Connection cnx = DataSource.getInstance().getCnx();
    
        @Override
    public void ajouter(Article a) {
        try {
           String query = "INSERT INTO article (title,img,content) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setString(1,a.getTitle());
            pst.setString(2,a.getImg());
            pst.setString(3,a.getContent());
            pst.executeUpdate();
            
            //System.out.println("article ajouté !");
            Image img = new Image("http://127.0.0.1:8000/assets/img/shop-img/"+a.getImg(), 50, 50, false, false);
			Notifications notificationBuilder;
            notificationBuilder = Notifications.create().title("download completed").text("saved")
                    .graphic(new ImageView(img)).hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT);
			notificationBuilder.show();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
    }

    @Override
    public void supprimer(Article t) {
        try {
            String requete = "DELETE FROM article WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,t.getId());
            pst.executeUpdate();
            System.out.println("article supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    
    }

    @Override
    public void modifier(Article t) {
        
            try {
            String requete = "UPDATE article SET Title=?,img=?,content=? WHERE id=?";
                   
            PreparedStatement pst = cnx.prepareStatement(requete);   
            pst.setString(1, t.getTitleArticle());
            pst.setString(2, t.getImgArticle());
            pst.setString(3, t.getContentArticle());
            pst.setInt(4, t.getId());
            pst.executeUpdate();
            System.out.println("Article modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Article> afficher() {
        
        List<Article> list = new  ArrayList<>();
        String req = "select * from article";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs =  pst.executeQuery();
            while(rs.next()){
                Article a = new Article(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                list.add(a);
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        } 
        return list;
    }    

    public void ajouter(ServiceArticle a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    



}



    

