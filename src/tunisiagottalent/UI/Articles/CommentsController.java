/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Articles;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import tunisiagottalent.Entity.Article;
import tunisiagottalent.Entity.Comments;
import tunisiagottalent.services.CommentsServices;

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class CommentsController implements Initializable {

   private Article a;
       @FXML
    private VBox com;
 @FXML
    private AnchorPane root;
    public void setA(Article a) {
        this.a = a;
    }
    @FXML
    void close(ActionEvent event) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(()->{
            CommentsServices cs=new CommentsServices();
            ObservableList<Comments> list = FXCollections.observableArrayList(cs.getAll(a));
            Label TitlLabel = new Label(a.getTitle()+" :Comments");
            TitlLabel.setTextFill(javafx.scene.paint.Color.VIOLET);
            TitlLabel.setFont(Font.font("Broadway", 22));
            TitlLabel.setPadding(new Insets(10));
            root.getChildren().add(TitlLabel);
            list.forEach((c) -> {
            Label body = new Label(c.getBody());
            body.setTextFill(javafx.scene.paint.Color.WHITE);
            body.setFont(Font.font("Broadway", 18));
            Label author = new Label("By: "+c.getAuthor().getUsername());
            author.setTextFill(javafx.scene.paint.Color.BLUEVIOLET);
            author.setFont(Font.font("Broadway", 20));
            Label dateLabel = new Label("On : "+c.getCreated_at().toString());
            dateLabel.setTextFill(javafx.scene.paint.Color.ROYALBLUE);
            dateLabel.setFont(Font.font("Broadway", 20));
            VBox vbox=new VBox();
            com.setSpacing(20);
            vbox.getChildren().addAll(author,dateLabel,body);
            
            com.getChildren().addAll(vbox);
            
            
            
            });
        });
    }    
    
}
