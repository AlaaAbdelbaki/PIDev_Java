/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Base;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;
import tunisiagottalent.Entity.Review;
import tunisiagottalent.Entity.video;
import tunisiagottalent.services.VideoServices;
import tunisiagottalent.services.VoteServices;
import tunisiagottalent.util.UserSession;

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class HomepageController implements Initializable {
private final static int rowsPerPage = 3;
    @FXML
    private VBox vboxvids;
    VideoServices vs=new VideoServices();
    List<video> or = vs.getAll();
    @FXML
    private Pagination pagination;
    @FXML
    private ScrollPane scrollvids;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pagination.setPageFactory(this::loadVideos);
    }    
    
    
     Node loadVideos( Integer pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, or.size());
        if (fromIndex >= toIndex) {
            return null;
        }
        ObservableList<video> tabs = FXCollections.observableArrayList(or.subList(fromIndex, toIndex));

        vboxvids.getChildren().clear();
        
        VoteServices votesserv = new VoteServices();
        tabs.forEach((vid) -> {
                HBox video_grid=new HBox();
                
            GridPane details = new GridPane();

            Label Title = new Label(vid.getTitle());
            Title.setTextFill(javafx.scene.paint.Color.WHITE);
            Title.setFont(Font.font("Cambria", 22));

            Label VoteNum = new Label(Integer.toString(votesserv.getVotes(vid)));
            VoteNum.setTextFill(javafx.scene.paint.Color.WHITE);
            VoteNum.setFont(Font.font("Cambria", 24));

            Label pubDate = new Label(vid.getPublish_date().toString());
            pubDate.setTextFill(javafx.scene.paint.Color.WHITE);
            pubDate.setFont(Font.font("Cambria", 16));

            JFXButton Delete = new JFXButton("Delete");
            JFXButton Vote = new JFXButton("Vote");
            JFXButton Unvote = new JFXButton("Unvote");

            Delete.resize(150, 250);
            Vote.resize(150, 250);
            Unvote.resize(150, 250);

            Delete.setStyle("-fx-text-fill: white;-fx-font-size:18px;-fx-background-color:#49111C");
            Vote.setStyle("-fx-text-fill: white;-fx-font-size:18px;-fx-background-color:#ACEB98");
            Unvote.setStyle("-fx-text-fill: white;-fx-font-size:18px;-fx-background-color:#92140C");

            WebView preview = new WebView();
            
            preview.getEngine().load(vid.getUrl());

            UserSession s = UserSession.instance;
            if (s.getU().getUsername().equals(vid.getOwner().getUsername())) {

                details.add(Delete, 0, 10);
                
            }
            if (votesserv.find(vid, s.getU())) {
                Unvote.setVisible(true);
                Vote.setVisible(false);
            } else {
                Unvote.setVisible(false);
                Vote.setVisible(true);
            }
            Vote.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    votesserv.Add(s.getU(), vid);
                    Unvote.setVisible(true);
                    Vote.setVisible(false);
                    VoteNum.setText(Integer.toString(Integer.parseInt(VoteNum.getText()) + 1));

                }
            });
            Unvote.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    votesserv.delete(vid, s.getU());
                    Unvote.setVisible(false);
                    Vote.setVisible(true);
                    VoteNum.setText(Integer.toString(Integer.parseInt(VoteNum.getText()) - 1));

                }
            });
            Delete.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Delete Video");
                    alert.setHeaderText("Are you sure ?");
                    alert.setContentText("You will lose all your votes !");

                    alert.showAndWait();
                    //  vs.delete(vid);
                    video_grid.getChildren().removeAll(preview, details);
                    video_grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == tabs.indexOf(vid));
                    
                }
                
            });
            RowConstraints row = new RowConstraints(50);
            details.addRow(0,Title);
            details.getRowConstraints().add(row);
            details.addRow(1,pubDate);
            details.getRowConstraints().add(row);
            details.add(Unvote, 0, 2);
            details.getRowConstraints().add(row);
            details.add(Vote, 0, 2);
            details.getRowConstraints().add(row);
            details.add(VoteNum, 1, 2);
            details.getRowConstraints().add(row);
            
            details.setStyle("-fx-background-color: #0c0527");
            details.setPadding(new Insets(15, 15, 15, 15));
            

            
            
            video_grid.getChildren().addAll(  preview,details);
            video_grid.setPrefHeight(350);
             vboxvids.getChildren().addAll(video_grid);
             vboxvids.setSpacing(25);
             scrollvids.setContent(vboxvids);
             
             
             
        });
        return  scrollvids;
    }

}
