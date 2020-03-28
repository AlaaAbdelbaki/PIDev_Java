/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent;

import java.io.IOException;
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
import tunisiagottalent.services.UserServices;
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

//        Parent root = FXMLLoader.load(getClass().getResource("/tunisiagottalent/ui/homepage.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/tunisiagottalent/ui/login.fxml"));

        Scene scene = new Scene(root, 1280, 720);
        stage.initStyle(StageStyle.UNDECORATED);

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });

        stage.setScene(scene);
//        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        // TODO code application logic here
//        User u = new User("lololol", "faress@f.f", "male", "ariana", "fares", "amir", "45698521");
//        System.out.println(u.getPhone_number());
//        UserServices x = new UserServices();
//        x.login("memeguy", "alaa");
//        x.signup(u);
//
//        x.getAll().forEach(System.out::println);

        Application.launch(args);
//        User u = new User("alaa","alaa","alaa");

//        System.out.println(x.getRole(u));
    }

}
