/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Articles;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import static javafx.scene.layout.FlowPane.setMargin;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tunisiagottalent.Entity.Article;
import tunisiagottalent.UI.Base.MainController;
import tunisiagottalent.services.ServiceArticle;


/**
 * FXML Controller class
 *
 * @author Anis
 */
public class User_ArticlesController implements Initializable {

      @FXML
    private VBox vbox;
    ServiceArticle sa = new ServiceArticle();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Article> list = FXCollections.observableArrayList(sa.afficher());
        list.forEach((u) -> {

            Label TitlLabel = new Label(u.getTitle());
            TitlLabel.setTextFill(javafx.scene.paint.Color.VIOLET);
            TitlLabel.setFont(Font.font("Broadway", 50));
            JFXButton commentsButton=new JFXButton("View Comments");
            commentsButton.resize(175, 45);
            commentsButton.setStyle("-fx-text-fill: orange;-fx-font-size:20px;");
            commentsButton.setRipplerFill(javafx.scene.paint.Color.GREEN);
            commentsButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("comments.fxml"));
            Parent third = loader.load();
             CommentsController controller = loader.<CommentsController>getController();
                        controller.setA(u);
            Scene s = new Scene(third);
            s.setFill(Color.TRANSPARENT);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setOpacity(0.9);
            stage.setTitle("Comments");
            stage.initOwner(vbox.getScene().getWindow());
            stage.setScene(s);

            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
                }
            });
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
            vboximg.setSpacing(20);
            UpdateImage.setImage(new Image("http://127.0.0.1:8000/assets/img/shop-img/" + u.getImg()));
            UpdateImage.setFitHeight(500);
            UpdateImage.setFitWidth(450);
            vboximg.getChildren().addAll(TitlLabel,UpdateImage,commentsButton);
             VBox vboxContent = new VBox();
            vboxContent.setSpacing(10);
            vboximg.setAlignment(Pos. CENTER);
            vboxContent.getChildren().addAll(vboximg,flow);
            vbox.setSpacing(20);
            vbox.getChildren().addAll(vboxContent);

        });
    }    
    
}
