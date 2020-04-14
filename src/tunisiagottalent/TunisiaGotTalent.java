/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent;

import tunisiagottalent.util.DataSource;
import entity.Complaint;
import services.ComplaintService;
import services.ReviewService;
import entity.Review;
/**
 *
 * @author alaa
 */
public class TunisiaGotTalent {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        DataSource ds1 = DataSource.getInstance();
        System.out.println(ds1);
        Complaint c= new Complaint("connexion","lente");
    ComplaintService cs= new ComplaintService();
    //cs.insertComplaintPST(c);
    Review rev= new Review();
    ReviewService rs= new ReviewService();
    //rs.insertReviewPST(rev);
    //cs.getAll().forEach(System.out::println);
    //cs.deleteComplaint(19);
    //System.out.println("Suppresion en cours");
    //cs.getAll().forEach(System.out::println);
    //cs.SearchSubject("bad design");
    //System.out.println("-------------------");
    //cs.EditComplaint(11, "hello","world");
     //cs.getAll().forEach(System.out::println);
   rs.getAll().forEach(System.out::println);
      System.out.println("-------------------");
     rs.EditReview(rev);
    //rs.deleteReview(23);
    rs.getAll().forEach(System.out::println);
    // rs.SearchRating(3);
    //rs.SearchRates(3).forEach(System.out::println);
      System.out.println("-------------------");
   // rs.SearchCategory("orders").forEach(System.out::println);
        
    }
    
}
