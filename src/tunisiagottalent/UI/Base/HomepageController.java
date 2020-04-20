/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Base;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
import de.jensd.fx.glyphs.GlyphIcon;
import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.glyphfont.FontAwesome;
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
    List<video> or = voteS.OrderedByNewest();

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
    @FXML
    private JFXButton PostVideo;
    @FXML
    private Label Warning;
    @FXML
    private Label Compete;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (UserSession.instance.getU().getRole().contains("ROLE_TALENTED")) {
            PostVideo.setVisible(true);
        } else {
            Warning.setVisible(true);
            Compete.setVisible(true);
        }
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
            VBox details = new VBox();
            Label Title = new Label(vid.getTitle());
            Title.setTextFill(javafx.scene.paint.Color.WHITE);
            Title.setFont(Font.font("Cambria", 22));

            Label VoteNum = new Label(Integer.toString(votesserv.getVotes(vid)));
            VoteNum.setTextFill(javafx.scene.paint.Color.WHITE);
            VoteNum.setFont(Font.font("Cambria", 24));

            Label pubDate = new Label(vid.getPublish_date().toString());
            pubDate.setTextFill(javafx.scene.paint.Color.WHITE);
            pubDate.setFont(Font.font("Cambria", 16));
            WebView preview = new WebView();

            preview.getEngine().load(vid.getUrl());
            
            
            Label labelheart = new Label();
            Label labelunheart = new Label();

            labelheart.setGraphic(new FontAwesome().create(FontAwesome.Glyph.HEART).color(Color.RED).size(20));
            labelunheart.setGraphic(new FontAwesome().create(FontAwesome.Glyph.HEART).color(Color.WHITE).size(20));
            VoteNum.setGraphic(new FontAwesome().create(FontAwesome.Glyph.THUMBS_UP).color(Color.GREEN).size(20));
            VoteNum.setAlignment(Pos.CENTER);
            JFXToggleButton Voting = new JFXToggleButton();
            Voting.setToggleLineColor(Color.RED);
            Voting.setToggleColor(Color.RED);
            HBox hboxvoting = new HBox();
            hboxvoting.setAlignment(Pos.CENTER);
            UserSession s = UserSession.instance;

            if (votesserv.find(vid, s.getU())) {
                Voting.setSelected(true);
                labelheart.setVisible(true);
                labelunheart.setVisible(false);
            } else {
                Voting.setSelected(false);
                labelunheart.setVisible(true);
                labelheart.setVisible(false);
            }
            Voting.setOnAction((e) -> {
                if (Voting.isSelected()) {

                    votesserv.Add(s.getU(), vid);
                    updateRanks();
                    Platform.runLater(() -> {
                        VoteNum.setText(Integer.toString(Integer.parseInt(VoteNum.getText()) + 1));
                        labelunheart.setVisible(false);
                        labelheart.setVisible(true);

                    });

                } else {

                    votesserv.delete(vid, s.getU());
                    updateRanks();
                    Platform.runLater(() -> {
                        labelheart.setVisible(false);
                        labelunheart.setVisible(true);
                        VoteNum.setText(Integer.toString(Integer.parseInt(VoteNum.getText()) - 1));

                    });
                }
            });
            hboxvoting.getChildren().addAll(labelunheart, Voting, labelheart);
            details.getChildren().addAll(Title, pubDate, hboxvoting, VoteNum);
            
            details.setStyle("-fx-background-color: #0c0527");
            details.setPadding(new Insets(15, 15, 15, 15));
           preview.setPrefHeight(300);
           details.setSpacing(30);
           details.setPrefWidth(300);
            vboxvids.setSpacing(25);
             video_grid.getChildren().addAll(preview,details);
             vboxvids.getChildren().addAll(video_grid);
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

    @FXML
    private void PostVideo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("postVideo.fxml"));
            Parent third = loader.load();

            Scene s = new Scene(third);
            s.setFill(Color.TRANSPARENT);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setOpacity(1);
            stage.initOwner(rootpane.getScene().getWindow());
            stage.setTitle("Post Video");

            stage.setScene(s);

            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void competeInCompetition(MouseEvent event) {
        rootpane.getChildren().clear();

        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Competitions/User_Competitions.fxml"));

            rootpane.getChildren().setAll(p);

            rootpane.getScene().getRoot().setStyle("-fx-background-image: url('/tunisiagottalent/UI/Competitions/img/bg-9.jpg')");

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
