/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.User;

import java.io.IOException;
import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import tunisiagottalent.Entity.User;
import tunisiagottalent.Entity.video;
import tunisiagottalent.UI.Base.MainController;
import tunisiagottalent.services.UserServices;
import tunisiagottalent.services.VideoServices;
import tunisiagottalent.util.UserSession;
import tunisiagottalent.util.sendEmailSMTP;

/**
 * FXML Controller class
 *
 * @author alaa
 */
public class ProfileController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ImageView profilePic;

    @FXML
    private Label username_profile;

    @FXML
    private Label nameLastName;

    @FXML
    private Label bio;

    @FXML
    private ScrollPane videos_Container;

    @FXML
    void initialize() {

        loadInfo();
        loadVideos();
        System.out.println("Profile loaded ! ");

    }

    @FXML
    void loadInfo() {

        UserServices us = new UserServices();
        UserSession s = UserSession.instance;
//        Image img=new Image("D:/Programming/Web/htdocs/annee_2019_2020/PIDev/web/assets/img/pics/unknown.jpg"); 
        


        User user = s.getU();
//        System.out.println(user);
        username_profile.setText(user.getUsername());
        System.out.println(us.getUser(s.getU().getUsername()).getProfilePic());
        profilePic.setImage(new Image("http://localhost/annee_2019_2020/PiDev/web/assets/uploads/" + us.getUser(s.getU().getUsername()).getProfilePic()));
        if (us.getUser(user.getUsername()).getName() == null || us.getUser(user.getUsername()).getLastName() == null) {
            nameLastName.setText("Complete your profile !!");
        } else {
            nameLastName.setText(us.getUser(user.getUsername()).getName() + " " + us.getUser(user.getUsername()).getLastName());
        }
        bio.setText(us.getUser(user.getUsername()).getBio());
    }

    @FXML
    void editProfile(ActionEvent event) {
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/User/editProfile.fxml"));
            rootPane.getChildren().setAll(p);

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void addVideo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tunisiagottalent/UI/Video/addVideo.fxml"));
            Parent third = loader.load();
            Scene s = new Scene(third);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setOpacity(1);
            stage.setTitle("Add Video");
            stage.initOwner(rootPane.getScene().getWindow());
            stage.setScene(s);

            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void loadVideos() {

        VideoServices vs = new VideoServices();
        UserServices us = new UserServices();
        List<video> videos;
        System.out.println("entered here");
//        System.out.println(us.getUser(username_profile.getText()).getId());
        videos = vs.getVideos(us.getUser(username_profile.getText()).getId());
//        System.out.println(videos.size());
        VBox root = new VBox();
        videos.forEach((v) -> {
//            System.out.println(v);
            Label title = new Label();
            title.getStyleClass().add("video_title");
            title.setText(v.getTitle());
            WebView video = new WebView();
            video.setPrefHeight(380);
            video.setPrefWidth(680);
            video.getEngine().loadContent("<iframe width=\"640\" height=\"360\" src=\"" + v.getUrl() + "\\ frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");

            root.getChildren().addAll(title, video);
            videos_Container.setContent(root);
        });
    }

}
