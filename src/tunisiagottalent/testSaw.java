/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent;

import java.sql.Date;
import tunisiagottalent.entity.Article;
import tunisiagottalent.entity.Updates;
import tunisiagottalent.services.ServiceArticle;
import tunisiagottalent.services.ServiceUpdates;
import tunisiagottalent.util.DataSource;

/**
 *
 * @author 
 */
public class testSaw {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
      // Article a = new Article(5,"Siwar","barhoumi","laa");
      //  ServiceArticle p = new ServiceArticle();
         //p.ajouter(a);
       // p.supprimer(a);
        //p.modifier(a);
        //p.afficher().forEach(System.out::println);
       Date publish_date = new Date(1998, 23, 05);
        Updates u = new Updates(2,"saw","kaa","laa",publish_date,"gaa");
        ServiceUpdates p = new ServiceUpdates();
        p.ajouter(u);
      // p.supprimer(u);
       // p.modifier(u);
        p.afficher().forEach(System.out::println); 
    } 
    
    
    
}
