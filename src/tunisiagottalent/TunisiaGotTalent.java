/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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

    @Override
    public void start(Stage stage) throws Exception {
        stage.getIcons().add(new Image("/tunisiagottalent/UI/Base/img/icon.png"));
        stage.setTitle("Tunisia Got Talent");

        Parent root = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Base/Main.fxml"));

        Scene scene = new Scene(root, 1280, 720);
        //stage.initStyle(StageStyle.UNDECORATED);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
