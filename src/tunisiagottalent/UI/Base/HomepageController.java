/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Base;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;
import tunisiagottalent.Entity.video;
import tunisiagottalent.UI.User.ProfileController;
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
    VideoServices vs = new VideoServices();
    VoteServices voteS = new VoteServices();
    List<video> or = vs.getAll();
    @FXML
    private Pagination pagination;
    @FXML
    private ScrollPane scrollvids;
    @FXML
    private JFXComboBox<String> orderCombo;
    @FXML
    private HBox vboxranks;
    @FXML
    private AnchorPane rootpane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String order[]
                = {"Votes", "Newest"};
        orderCombo.setItems(FXCollections.observableArrayList(order));

        updateRanks();
        pagination.setPageFactory(this::loadVideos);
        orderCombo.setOnAction((e) -> {
            if (orderCombo.getValue().equals("Votes")) {

                ObservableList<video> ordered = FXCollections.observableArrayList(voteS.OrderedByVotes());
                vs.getAll().forEach((vid) -> {
                    if (voteS.getVotes(vid) == 0) {
                        ordered.add(vid);
                    }
                });
                vboxvids.getChildren().clear();
                or = ordered;
                pagination.setPageFactory(this::loadVideos);

            } else if (orderCombo.getValue().equals("Newest")) {
                vboxvids.getChildren().clear();
                or = voteS.OrderedByNewest();
                pagination.setPageFactory(this::loadVideos);
            }

        });

    }

    Node loadVideos(Integer pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, or.size());
        if (fromIndex >= toIndex) {
            return null;
        }
        ObservableList<video> tabs = FXCollections.observableArrayList(or.subList(fromIndex, toIndex));

        vboxvids.getChildren().clear();

        VoteServices votesserv = new VoteServices();
        tabs.forEach((vid) -> {
            HBox video_grid = new HBox();

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

            JFXButton Vote = new JFXButton("Vote");
            JFXButton Unvote = new JFXButton("Unvote");

            Vote.resize(150, 250);
            Unvote.resize(150, 250);

            Vote.setStyle("-fx-text-fill: white;-fx-font-size:18px;-fx-background-color:#ACEB98");
            Unvote.setStyle("-fx-text-fill: white;-fx-font-size:18px;-fx-background-color:#92140C");

            WebView preview = new WebView();

            preview.getEngine().load(vid.getUrl());

            UserSession s = UserSession.instance;

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
                    updateRanks();

                }
            });
            Unvote.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    votesserv.delete(vid, s.getU());
                    Unvote.setVisible(false);
                    Vote.setVisible(true);
                    VoteNum.setText(Integer.toString(Integer.parseInt(VoteNum.getText()) - 1));
                    updateRanks();

                }
            });

            RowConstraints row = new RowConstraints(50);
            details.addRow(0, Title);
            details.getRowConstraints().add(row);
            details.addRow(1, pubDate);
            details.getRowConstraints().add(row);
            details.add(Unvote, 0, 2);
            details.getRowConstraints().add(row);
            details.add(Vote, 0, 2);
            details.getRowConstraints().add(row);
            details.add(VoteNum, 1, 2);
            details.getRowConstraints().add(row);

            details.setStyle("-fx-background-color: #0c0527");
            details.setPadding(new Insets(15, 15, 15, 15));

            video_grid.getChildren().addAll(preview, details);
            video_grid.setPrefHeight(350);
            vboxvids.getChildren().addAll(video_grid);
            vboxvids.setSpacing(25);
            scrollvids.setContent(vboxvids);

        });
        return scrollvids;
    }

    public void updateRanks() {
        ObservableList<video> l = FXCollections.observableArrayList(voteS.OrderedByVotes().subList(0, 3));
vboxranks.getChildren().clear();
        l.forEach((vid) -> {
            ImageView profilePic = new ImageView();
            profilePic.setFitHeight(100);
            profilePic.setFitWidth(100);
            Rectangle clip = new Rectangle(
                    profilePic.getFitWidth(), profilePic.getFitHeight()
            );
            clip.setArcWidth(100);
            clip.setArcHeight(100);

            profilePic.setEffect(new DropShadow(20, Color.BLACK));
            profilePic.setClip(clip);
            profilePic.setStyle("-fx-border-color:white;");
            // snapshot the rounded image.
            SnapshotParameters parameters = new SnapshotParameters();
            parameters.setFill(Color.TRANSPARENT);

            profilePic.setImage(new Image("http://127.0.0.1:8000/assets/uploads/" + vid.getOwner().getProfilePic()));
            profilePic.setCursor(Cursor.HAND);
            profilePic.setOnMouseClicked((e) -> {

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/tunisiagottalent/UI/User/profile.fxml"));
                    Parent root = loader.load();
                    ProfileController controller = loader.<ProfileController>getController();
                    controller.setUser(vid.getOwner());
                    System.out.println(vid.getOwner());
                    rootpane.getChildren().clear();
                    rootpane.getChildren().addAll(root);

                    // mainAnchor.setStyle("-fx-background-image: url('/tunisiagottalent/UI/Base/img/bg-4.jpg')");
                } catch (IOException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }

            });
            vboxranks.getChildren().add(profilePic);

        });

    }
}
