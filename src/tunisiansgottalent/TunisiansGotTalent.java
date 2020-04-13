/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiansgottalent;

import Entity.Product;
import Entity.ShoppingCart;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ProductServices;

/**
 *
 * @author paspo
 */
public class TunisiansGotTalent extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane mainpane = FXMLLoader.load(TunisiansGotTalent.class.getResource("/UI/Shop/Shop.fxml"));
        Scene scene = new Scene(mainpane);
        
        primaryStage.setTitle("test test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
