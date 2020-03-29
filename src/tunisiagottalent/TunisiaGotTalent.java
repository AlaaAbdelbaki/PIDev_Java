/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent;

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
import tunisiagottalent.services.CompetitionServices;
import tunisiagottalent.util.DataSource;

import static javafx.application.Application.launch;

/**
 *
 * @author alaa
 */
public class TunisiaGotTalent extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Application.launch(args);
    }

    private double xOffset = 0;
    private double yOffset = 0;
    @Override
    public void start(Stage stage) throws Exception {
        Font.loadFont(TunisiaGotTalent.class.getResource("/tunisiagottalent/UI/fonts/Roboto-Bold.ttf").toExternalForm(), 10);
        stage.getIcons().add(new Image("/tunisiagottalent/UI/img/icon.png"));
        stage.setTitle("Tunisia Got Talnet");

        Parent root = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/login.fxml"));

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
}
