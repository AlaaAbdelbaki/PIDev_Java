/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tunisiagottalent.entity.User;
import tunisiagottalent.entity.Video;
import tunisiagottalent.services.UserServices;
import tunisiagottalent.services.VideoServices;
import tunisiagottalent.util.DataSource;

/**
 *
 * @author alaa
 */
public class TunisiaGotTalent extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws Exception {
        Font.loadFont(TunisiaGotTalent.class.getResource("/tunisiagottalent/ui/fonts/Roboto-Bold.ttf").toExternalForm(), 10);
        stage.getIcons().add(new Image("/tunisiagottalent/ui/img/icon.png"));
        stage.setTitle("Tunisia Got Talent");

//        Parent root = FXMLLoader.load(getClass().getResource("/tunisiagottalent/ui/profile.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/tunisiagottalent/ui/login.fxml"));

        Scene scene = new Scene(root, 1280, 720);
//        stage.initStyle(StageStyle.UNDECORATED);

        root.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        stage.setScene(scene);
//        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        // TODO code application logic here

        Application.launch(args);
//       
//        Console test part
//        User u = new User("azerty", "azerty@az.com", "azerty123");
//        UserServices us = new UserServices();
//        for(int i=0;i<100;i++){
//            us.tokenGenerator();
//        }
//        if(us.signup(u)){
//            System.out.println("Signup successful");
//        }
//        
//        us.login("azerty", "azerty123");
//        User u2 = new User(u.getUsername(),null, "", "male", "Adr2", "Test","user", "Test user bio", "123456789",Date.valueOf("2019-05-02"));
//        if(us.updateUser(u2)){
//            System.out.println("User updated !");
//        }
//        
//        us.delete("azerty");

    }

}
