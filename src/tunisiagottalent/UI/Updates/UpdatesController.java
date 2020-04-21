/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Updates;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import tunisiagottalent.Entity.Updates;
import tunisiagottalent.UI.Shop.AddProductController;
import tunisiagottalent.services.ServiceUpdates;

/**
 * FXML Controller class
 *
 * @author
 */
public class UpdatesController implements Initializable {
@FXML
    private AnchorPane ContentPane;
    @FXML
    private TextField title;
    private TextField img;
    @FXML
    private ComboBox<String> category;
    @FXML
    private JFXTextArea content;
    @FXML
    private DatePicker datepub;
    @FXML
    private Button ValiderAction;
    @FXML
    private Button upload;
    private String imgp;
    URI imguriUri;
    @FXML
    private ImageView preview;
    @FXML
    private JFXTextField imgurl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        content.setStyle("-fx-text-fill:white;");
        title.setStyle("-fx-text-fill:white;");
        imgurl.setStyle("-fx-text-fill:white;");
        category.setStyle("-fx-text-fill:white;");
        datepub.setValue(LocalDate.now());
        datepub.setStyle("-fx-text-fill:white;");
        category.getItems().addAll("sport", "entertainment");
    }

    @FXML
    private void btnValiderAction(ActionEvent event) throws IOException {

        boolean test = false;

        if (title.getText().equals("")) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText("Le champs Title est vide");
            alert.showAndWait();
            test = false;
        }

        if (imgurl.getText().equals("")) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText("Le champs Image est vide");
            alert.showAndWait();
            test = false;
        }

        if (content.getText().equals("")) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText("Le champs Content est vide");
            alert.showAndWait();
            test = false;
        } else {
            try {

                Files.copy(Paths.get(imguriUri), Paths.get("D:\\Projects\\PIDev\\web\\assets\\img\\shop-img\\" + imgp),StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Updates u = new Updates(title.getText(), imgurl.getText(), category.getSelectionModel().getSelectedItem(), new Date(System.currentTimeMillis()), content.getText());
            ServiceUpdates sp = new ServiceUpdates();
            sp.ajouter(u);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Done ");
            alert.setHeaderText("Updates bien ajouté  ");
            alert.showAndWait();
            test = false;

            Stage stage = (Stage) ContentPane.getScene().getWindow();
            try {
                Scene p1 = (Scene) stage.getOwner().getScene();
                HBox cAnchorPane = (HBox) p1.lookup("#content");
                AnchorPane p2 = FXMLLoader.load(getClass().getResource("UpdatesList.fxml"));
                cAnchorPane.getChildren().setAll(p2);

            } catch (IOException ex) {
                Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
            }

            stage.close();
        }

    }

    @FXML
    private void uploadimage(ActionEvent event) {
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*png");

        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(imageFilter);
        Window stage = null;
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {

            imgp = file.getName();
            imguriUri = file.toURI();
            imgurl.setText(imgp);
            preview.setImage(new Image(file.toURI().toString()));

        }
    }

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) ContentPane.getScene().getWindow();
        stage.close();
    }

}
