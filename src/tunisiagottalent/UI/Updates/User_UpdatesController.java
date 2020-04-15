/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Updates;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Text;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import static javafx.scene.layout.FlowPane.setMargin;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import tunisiagottalent.Entity.Updates;
import tunisiagottalent.services.ServiceUpdates;

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class User_UpdatesController implements Initializable {

    @FXML
    private VBox vbox;
    ServiceUpdates su = new ServiceUpdates();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Updates> list = FXCollections.observableArrayList(su.afficher());
        list.forEach((u) -> {

            Label TitlLabel = new Label(u.getTitle());
            TitlLabel.setTextFill(javafx.scene.paint.Color.VIOLET);
            TitlLabel.setFont(Font.font("Broadway", 50));
            Label CategoryLabel = new Label("Category: "+u.getCategory());
            CategoryLabel.setTextFill(javafx.scene.paint.Color.ORANGERED);
            CategoryLabel.setFont(Font.font("Book Antiqua", 21));
            Label PubDateLabel = new Label("Publish Date: "+u.getPublish_date().toString());
            PubDateLabel.setTextFill(javafx.scene.paint.Color.GREENYELLOW);
            PubDateLabel.setFont(Font.font("Book Antiqua",21));
            FlowPane flow = new FlowPane();
            flow.setStyle("-fx-background-radius: 25 25 25 25;-fx-border-radius: 0 0 18 18;-fx-background-color:white;");
            Text ContentLabel = new Text(u.getContent());
            ContentLabel.setWrappingWidth(600);
            flow.setOpacity(0.6);
            flow.setAlignment(Pos.CENTER);
            setMargin(flow, new Insets(20, 0, 0, 40));
            ContentLabel.setFont(Font.font("Book Antiqua", 20));
            flow.getChildren().add(ContentLabel);
            ImageView UpdateImage = new ImageView();
            VBox vboximg=new VBox();
            UpdateImage.setImage(new Image("http://127.0.0.1:8000/assets/img/shop-img/" + u.getImg()));
            UpdateImage.setFitHeight(500);
            UpdateImage.setFitWidth(450);
            vboximg.getChildren().addAll(TitlLabel,UpdateImage);
             VBox vboxContent = new VBox();
            vboxContent.setSpacing(10);
            vboximg.setAlignment(Pos. CENTER);
            vboxContent.getChildren().addAll(vboximg,CategoryLabel,PubDateLabel,flow);
            vbox.setSpacing(20);
            vbox.getChildren().addAll(vboxContent);

        });
    }

}
