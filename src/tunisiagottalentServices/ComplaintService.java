/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalentServices;

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
import tunisiagottalentEntities.Complaint;

/**
 *
 * @author sarah
 */
public class ComplaintService {
     private Connection connexion;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    

    public ComplaintService(){
        connexion=DataSource.getInstance().getCnx();
    }
    
    public void insertComplaint(Complaint cp){
        String req="insert into complaints(subject,content) values("+cp.getSubject()+","+cp.getContent()+")";
      
         
         try {
             ste=connexion.createStatement();
              ste.executeUpdate(req);
         } catch (SQLException ex) {
             Logger.getLogger(ComplaintService.class.getName()).log(Level.SEVERE, null, ex);
         }
                 
         
       
    
    }
     public void insertComplaintPST(Complaint cp ){
         String req="insert into complaint(subject,content) values(?,?)";
        try {
            pst=connexion.prepareStatement(req);
            pst.setString(1, cp.getSubject());
            pst.setString(2,cp.getContent());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ComplaintService.class.getName()).log(Level.SEVERE, null, ex);
        }
     }   
     public  void deleteComplaint(Complaint c)
     {
     String req ="delete from complaint where id= ?";
         try {
             pst=connexion.prepareStatement(req);
             int id = c.getId();
             pst.setInt(1,id);
             pst.executeUpdate();
             
         } catch (SQLException ex) {
             Logger.getLogger(ComplaintService.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
     public void SearchSubject(String subject){
         String req ="select * from complaint where subject='"+subject+"'";
         try {
             pst=connexion.prepareStatement(req);
             rs=pst.executeQuery(req);
             rs.last();
             int nbrRow= rs.getRow();
             if(nbrRow==1){
             System.out.println("il existe "+nbrRow+" pour"+subject);
             }
             else{System.out.println("Il n'existe aucun "+subject+" par ce nom");}
         } catch (SQLException ex) {
             Logger.getLogger(ComplaintService.class.getName()).log(Level.SEVERE, null, ex);
         }
     
     }
      public void EditComplaint(Complaint c){
        String req ="UPDATE   complaint SET subject=?,content=? WHERE id=? ";
        
         try {
             pst=connexion.prepareStatement(req,PreparedStatement.RETURN_GENERATED_KEYS);
             pst.setString(1, c.getSubject());
             pst.setString(2, c.getContent());
             pst.setInt(3, c.getId());
             pst.execute();
             System.out.println("update done!");
         } catch (SQLException ex) {
             Logger.getLogger(ComplaintService.class.getName()).log(Level.SEVERE, null, ex);
         }
      
      }       
             
             
    public   List<Complaint>  getAll(){
      String req= "select * from complaint " ;
     List<Complaint> list= new ArrayList<>();
        
         try {
             ste=connexion.createStatement();
             rs= ste.executeQuery(req);
             while(rs.next()){
                list.add(new Complaint(rs.getInt("id"),rs.getString(2),rs.getString("content")));
             }
         } catch (SQLException ex) {
             Logger.getLogger(ComplaintService.class.getName()).log(Level.SEVERE, null, ex);
         }
return list;
 }
       public List<Complaint> SearchSub(String subject) {
 String requete = "SELECT * FROM review where subject = '"+subject ;
 List<Complaint> liste= new ArrayList<>();
         try {
             ste=connexion.createStatement();
             rs = ste.executeQuery(requete);
             while(rs.next())
            {
             liste.add(new Complaint(rs.getInt("id"), rs.getString(2), rs.getString("content")));
            }
         } catch (SQLException ex) {
             Logger.getLogger(ReviewService.class.getName()).log(Level.SEVERE, null, ex);
         }
 
 return liste;
 }
}
