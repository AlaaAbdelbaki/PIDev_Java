/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisigottalentViews;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author sarah
 */
public class Avis extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("AddReview.fxml"));
        Scene scene=new Scene(root);
        primaryStage.setTitle("Your Review");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    catch(IOException ex){
        System.out.println(ex.getMessage());
    
    }
    
    }
    
   public static void main(String[] args) {
       launch(args);
     
     }
    
    
}
