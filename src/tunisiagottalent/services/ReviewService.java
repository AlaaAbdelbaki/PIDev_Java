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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tunisiagottalent.util.DataSource;
import tunisiagottalent.Entity.Review;
import tunisiagottalent.Entity.User;
import tunisiagottalent.util.UserSession;

/**
 *
 * @author sarah
 */
public class ReviewService {
        private Connection connexion;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public ReviewService() {
         connexion=DataSource.getInstance().getCnx();
    }
    public void insertReview(Review r){
       String req="insert into complaints(rating,subject,content) values("+r.getRating()+","+r.getCategory()+","+r.getContent()+")";
      
       
         try {
             ste=connexion.createStatement();
         } catch (SQLException ex) {
             Logger.getLogger(ReviewService.class.getName()).log(Level.SEVERE, null, ex);
         }   
}
 public void insertReviewPST(Review r ){
         String req="insert into review(rating,title,content,user_id,category) values(?,?,?,?,?)";
        
         try {
             pst=connexion.prepareStatement(req);
             pst.setString(5,r.getCategory());
             pst.setInt(1,r.getRating());
             pst.setObject(4,r.getUser_id().getId());
             pst.setString(3,r.getContent());
             pst.setString(2,r.getTitle());
             pst.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(ReviewService.class.getName()).log(Level.SEVERE, null, ex);
         }
}
 public  void deleteReview(Review r){
         String req ="delete from review where id= ?";
         try {
             pst=connexion.prepareStatement(req);
             int id = r.getId();
             pst.setInt(1,id);
             pst.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(ReviewService.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
 public void SearchRating(int rating){
 String req= "select * from review where rating='"+rating+"'" ;
         try {
             ste=connexion.createStatement();
             rs= ste.executeQuery(req);
             rs.last();
             int nbrRow= rs.getRow();
             if(nbrRow!=0){
             System.out.println("il existe "+nbrRow+" pour"+rating);
                          }
             else{System.out.println("Il n'existe aucun "+rating);}
         } catch (SQLException ex) {
             Logger.getLogger(ReviewService.class.getName()).log(Level.SEVERE, null, ex);
         }
          }
 public void EditReview(Review r){
 String req = "UPDATE review SET category=?,rating=?,content=? WHERE id=? ";
         try {
             pst=connexion.prepareStatement(req,PreparedStatement.RETURN_GENERATED_KEYS);
             pst.setString(1, r.getCategory());
             pst.setString(2, r.getContent());
             pst.setInt(3, r.getRating());
             pst.setInt(4, r.getId());
             pst.executeUpdate();
             System.out.println("update done!");
         } catch (SQLException ex) {
             Logger.getLogger(ReviewService.class.getName()).log(Level.SEVERE, null, ex);
         }

 
 }
 public List<Review> SearchRates(int rating) {
 String requete = "SELECT * FROM review where rating = '"+rating+"' " ;
 List<Review> liste= new ArrayList<>();
         try {
             ste=connexion.createStatement();
             rs = ste.executeQuery(requete);
             while(rs.next())
            {
                
         liste.add(new Review(rs.getInt("id"),rs.getString(2),rs.getInt(3),rs.getString("content")));
            }
         } catch (SQLException ex) {
             Logger.getLogger(ReviewService.class.getName()).log(Level.SEVERE, null, ex);
         }
 return liste;}
         public List<Review> SearchCategory(String category) {
 String requete = "SELECT * FROM review where category = '"+category+"' " ;
 List<Review> liste= new ArrayList<>();
         try {
             ste=connexion.createStatement();
             rs = ste.executeQuery(requete);
             while(rs.next())
            {
             liste.add(new Review(rs.getInt("id"),rs.getString(2),rs.getInt(3),rs.getString("content")));
            }
         } catch (SQLException ex) {
             Logger.getLogger(ReviewService.class.getName()).log(Level.SEVERE, null, ex);
         }
 
 return liste;
 }
public   List<Review>  getAll(){
      String req= "select * from review  INNER JOIN user where review.user_id = user.id";
      
     List<Review> list= new ArrayList<>();
         try {
             ste=connexion.createStatement();
              rs= ste.executeQuery(req);
             while(rs.next()){
              list.add(new Review(rs.getInt("id"),
                      new User(rs.getInt("id"),
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
                                rs.getString("profile_pic")),
                      rs.getString("category"),rs.getInt("rating"),rs.getString("content"),rs.getString("title")));
                
             }
         } catch (SQLException ex) {
             Logger.getLogger(ReviewService.class.getName()).log(Level.SEVERE, null, ex);
         }
        
return list;
 }
public   List<Review>  getbyUser(User u){
      String req= "select * from review where review.user_id = "+u.getId();
      
     List<Review> list= new ArrayList<>();
         try {
             ste=connexion.createStatement();
              rs= ste.executeQuery(req);
             while(rs.next()){
              list.add(new Review(rs.getInt("id"),UserSession.instance.getU(),
                      
                      rs.getString("category"),rs.getInt("rating"),rs.getString("content"),rs.getString("title")));
                
             }
         } catch (SQLException ex) {
             Logger.getLogger(ReviewService.class.getName()).log(Level.SEVERE, null, ex);
         }
        
return list;
 }
    
}
