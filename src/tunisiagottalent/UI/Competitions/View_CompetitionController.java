/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Competitions;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
import java.sql.Timestamp;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import java.util.concurrent.TimeUnit;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javafx.scene.web.WebView;
import javafx.util.Duration;
import org.controlsfx.glyphfont.FontAwesome;
import tunisiagottalent.Entity.Competition;

import tunisiagottalent.Entity.video;
import tunisiagottalent.services.ParticipationServices;
import tunisiagottalent.services.VideoServices;
import tunisiagottalent.services.VoteServices;
import tunisiagottalent.util.UserSession;

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class View_CompetitionController {

    /**
     * Initializes the controller class.
     */
    public static Timeline oneSecond;
    @FXML
    private ScrollPane scroll;
    private Competition c;
    @FXML
    private AnchorPane video_list;

    @FXML
    private Label comp;
    @FXML
    private Label time;
    @FXML
    private Label ranks;
    @FXML
    private JFXComboBox<String> orderCombo;


    private final static int rowsPerPage = 3;
    VoteServices voteServices = new VoteServices();
    VideoServices videoServices = new VideoServices();
    ParticipationServices ps = new ParticipationServices();
    @FXML
    private VBox vboxvids;
    @FXML
    private Pagination pagination;
    ObservableList<video> tabs ;

    public void initialize() {
        
        Platform.runLater(() -> {
            tabs = ps.getAll(c);
            comp.setText(c.getSubject());

            if (c.getCompetition_end_date().before(new Timestamp(System.currentTimeMillis()))) {
                time.setText("Competition is Over");

            } else {
                updateRanks();

                timer();
                String order[]
                        = {"Votes", "Newest"};
                orderCombo.setItems(FXCollections.observableArrayList(order));

                orderCombo.setOnAction((e) -> {
                    if (orderCombo.getValue().equals("Votes")) {

                        ObservableList<video> ordered = FXCollections.observableArrayList(voteServices.ranks(c).keySet());
                        ps.getAll(c).forEach((vid) -> {
                            if (voteServices.getVotes(vid) == 0) {
                                ordered.add(vid);
                            }
                        });
                        vboxvids.getChildren().clear();
                        tabs = ordered;
                        pagination.setPageFactory(this::NotOver);

                    } else if (orderCombo.getValue().equals("Newest")) {
                        vboxvids.getChildren().clear();
                        tabs =ps.getAllOrdered(c);
                        pagination.setPageFactory(this::NotOver);
                    }

                });
                pagination.setPageFactory(this::NotOver);
            }

        });

    }

    public void timer() {
        oneSecond = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            Timestamp t = new Timestamp(System.currentTimeMillis());
            long counter = getDateDiff(t, c.getCompetition_end_date(), TimeUnit.SECONDS);

            @Override
            public void handle(ActionEvent event) {

                long h = TimeUnit.SECONDS.toHours(counter);
                long m = TimeUnit.SECONDS.toMinutes(counter - h * 3600);
                long s = TimeUnit.SECONDS.toSeconds(counter - h * 3600 - m * 60);
                counter = counter - 1;
                time.setText(String.format("%02d:%02d:%02d", h, m, s));

            }
        }));
        oneSecond.setCycleCount(60);
        oneSecond.play();
    }

    public static long getDateDiff(Timestamp oldTs, Timestamp newTs, TimeUnit timeUnit) {
        long diffInMS = newTs.getTime() - oldTs.getTime();
        return timeUnit.convert(diffInMS, TimeUnit.MILLISECONDS);
    }

    public Node NotOver(Integer pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, tabs.size());
        if (fromIndex >= toIndex) {
            return null;
        }
        ObservableList<video> min = FXCollections.observableArrayList(tabs.subList(fromIndex, toIndex));

        vboxvids.getChildren().clear();
        min.forEach((vid) -> {
            HBox video_grid = new HBox();
            VBox details = new VBox();

            Label Title = new Label(vid.getTitle());
            Title.setTextFill(javafx.scene.paint.Color.WHITE);
            Title.setFont(Font.font("Cambria", 22));

            Label user = new Label(vid.getOwner().getUsername());
            user.setTextFill(javafx.scene.paint.Color.WHITE);
            user.setFont(Font.font("Cambria", 18));

            Label VoteNum = new Label(Integer.toString(voteServices.getVotes(vid)));
            VoteNum.setTextFill(javafx.scene.paint.Color.WHITE);
            VoteNum.setFont(Font.font("Cambria", 24));

            Label pubDate = new Label(vid.getPublish_date().toString());
            pubDate.setTextFill(javafx.scene.paint.Color.WHITE);
            pubDate.setFont(Font.font("Cambria", 16));

            JFXButton Delete = new JFXButton("Delete");
            Delete.setVisible(false);
            Delete.resize(150, 250);
            Delete.setStyle("-fx-text-fill: white;-fx-font-size:18px;-fx-background-color:#49111C");

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
            if (s.getU().getUsername().equals(vid.getOwner().getUsername())) {

                Delete.setVisible(true);
            }
            if (voteServices.find(vid, s.getU())) {
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

                    voteServices.Add(s.getU(), vid);

                    Platform.runLater(() -> {
                        VoteNum.setText(Integer.toString(Integer.parseInt(VoteNum.getText()) + 1));
                        labelunheart.setVisible(false);
                        labelheart.setVisible(true);
                        updateRanks();
                    });

                } else {

                    voteServices.delete(vid, s.getU());

                    Platform.runLater(() -> {
                        labelheart.setVisible(false);
                        labelunheart.setVisible(true);
                        VoteNum.setText(Integer.toString(Integer.parseInt(VoteNum.getText()) - 1));
                        updateRanks();
                    });
                }
            });
            Delete.setOnAction((ActionEvent event1) -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete Participation");
                alert.setHeaderText("Are you sure ?");
                alert.setContentText("You will lose all your votes !");

                alert.showAndWait();
                ps.delete(vid);
                video_grid.getChildren().removeAll(preview, details);
                video_grid.getChildren().removeIf(node -> GridPane.getRowIndex(node) == tabs.indexOf(vid));
            });

            details.setStyle("-fx-background-color: #0c0527");
            details.setPadding(new Insets(15, 15, 15, 15));
            preview.setPrefHeight(300);
            details.setSpacing(30);
            details.setPrefWidth(250);
            vboxvids.setSpacing(25);
            hboxvoting.getChildren().addAll(labelunheart, Voting, labelheart);
            details.getChildren().addAll(Title, pubDate, Delete, hboxvoting, VoteNum);
            video_grid.getChildren().addAll(preview, details);
            vboxvids.getChildren().addAll(video_grid);
            scroll.setContent(vboxvids);
        });

        return scroll;
    }

    public void updateRanks() {
        VoteServices vs = new VoteServices();
        String rank = "";
        int i = 1;
        Iterator<Entry<video, Integer>> it = vs.ranks(c).entrySet().iterator();
        while (it.hasNext() && i < 4) {
            Map.Entry<video, Integer> entry = (Map.Entry<video, Integer>) it.next();
            rank = rank + "RANK " + i + ": \n" + entry.getKey().getOwner().getUsername() + " " + entry.getValue() + " Votes\n";
            i++;
        }

        ranks.setText(rank);

    }

    public void setCompetition(Competition c) {
        this.c = c;
    }
}
