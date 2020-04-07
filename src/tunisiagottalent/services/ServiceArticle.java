/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.services;

import tunisiagottalent.entity.Article;
import tunisiagottalent.util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ServiceArticle implements IService<Article>{
    
    Connection cnx = DataSource.getInstance().getCnx();
    
        @Override
    public void ajouter(Article a) {
        try {
           
            Statement stm = cnx.createStatement();
            String query = "INSERT INTO article (id,title,img,content) VALUES (NULL, '"+a.getTitle()+"', '"+a.getImg()+"', '"+a.getContent()+"')";
            stm.executeUpdate(query);
            System.out.println("article ajouté !");

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



    

