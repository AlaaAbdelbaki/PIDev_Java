/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Events;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class QrController implements Initializable {

    @FXML
    private AnchorPane root;
 private ImageView img;
    @FXML
    private VBox vbox;

    public void setImg(ImageView img) {
        this.img = img;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(()->{
        vbox.getChildren().add(img);});
    }    

    @FXML
    private void close(ActionEvent event) {
         Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
    
}
