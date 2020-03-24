/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent;

import tunisiagottalent.entity.Article;
/*import com.hypocampus.models.Participant; */
import tunisiagottalent.services.ServiceArticle;
/*import tunisiagottalent.services.ServiceUpdates;*/
import java.sql.Date;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
public class Test {
    
    
    public static void main(String[] args) {
        
      

        //************Article********************
        Article a = new Article(2,"SawSaw","kaa","laa");
        ServiceArticle a = new ServiceArticle();
          a.ajouter(a);
        //a.supprimer(a);
        //a.modifier(a);
        a.afficher().forEach(System.out::println);
        
       

    }


}
