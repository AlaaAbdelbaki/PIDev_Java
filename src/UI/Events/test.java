/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Events;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author hela
 */
public class test extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
 
            
           
            
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Events/AddEvent.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("AdminDashboard");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        
    }

}
