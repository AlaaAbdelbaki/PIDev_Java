/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;
import tunisiagottalent.entity.User;
import tunisiagottalent.entity.Video;
import tunisiagottalent.services.UserServices;
import tunisiagottalent.services.VideoServices;

/**
 * FXML Controller class
 *
 * @author alaa
 */
public class ProfileController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Rectangle navbar1;

    @FXML
    private Rectangle navbar;

    @FXML
    private Label username;

    @FXML
    private ImageView profilePicture;

    @FXML
    private ImageView dashboard_icon;

    @FXML
    private ImageView comp_icon;

    @FXML
    private ImageView shop_icon;

    @FXML
    private ImageView event_icon;

    @FXML
    private ImageView blog_icon;

    @FXML
    private ImageView news_icon;

    @FXML
    private ImageView rate_icon;

    @FXML
    private Label dashboard_label;

    @FXML
    private Label comp_label;

    @FXML
    private Label event_label;

    @FXML
    private Label shop_label;

    @FXML
    private Label blog_label;

    @FXML
    private Label news_label;

    @FXML
    private Label rate_label;

    @FXML
    private ImageView logout_icon;

    @FXML
    private Label logout_label;
    
    @FXML 
    private AnchorPane profile_container;
    
    @FXML
    private ImageView profilePic;
    
    @FXML
    private Label username_profile;
    
    @FXML
    private Label nameLastName;
    
    @FXML
    private Label bio;
    
    @FXML
    private Button edit;
    
    @FXML
    private ScrollPane videos_Container;
    
    
    
    

    @FXML
    void initialize() {

        rootPane.setOpacity(0);
        fadein();
        loadInfo();
        loadVideos();
        System.out.println("Profile loaded ! ");

    }

    void fadein() {
        FadeTransition ft = new FadeTransition();
        ft.setDuration(Duration.millis(500));
        ft.setNode(rootPane);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    void loadInfo() {
        dashboard_icon.setVisible(false);
        dashboard_label.setVisible(false);

//        Image img=new Image("D:/Programming/Web/htdocs/annee_2019_2020/PIDev/web/assets/img/pics/unknown.jpg"); 
        Image img = new Image("tunisiagottalent/ui/img/unknown.jpg");
        profilePicture.setImage(img);
        try {

            File f = new File("info.dat");
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String data = s.nextLine();
//                System.out.println(data);
                String user = data.substring(0, data.indexOf(":"));
                username.setText(user);
//                System.out.println(user.length());
                String previlege = data.substring(data.indexOf(":") + 4, data.indexOf(":") + 5);
//                System.out.println(previlege);
                if (previlege.equals("1")) {
                    dashboard_icon.setVisible(true);
                    dashboard_label.setVisible(true);
                }
                UserServices us = new UserServices();
                
                username_profile.setText(user);
                profilePic.setImage(img);
                if (us.getUser(user).getName() == null || us.getUser(user).getLastName() == null) {
                    nameLastName.setText("Complete your profile !!");
                } else {
                    nameLastName.setText(us.getUser(user).getName() + " " + us.getUser(user).getLastName());
                }
                bio.setText(us.getUser(user).getBio());

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void disconnect(MouseEvent event){
        try {
            FileWriter f = new FileWriter("info.dat");
            f.write("");
            f.close();
            fadeTransition("login");
        } catch (IOException ex) {
            Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @FXML
    void dashboard(MouseEvent event){
        try {
            fadeTransition("dashboard");
        } catch (IOException ex) {
            Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    void profile(MouseEvent event) {
        try {
            fadeTransition("profile");
        } catch (IOException ex) {
            Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void editProfile(ActionEvent event){
        try {
            fadeTransition("editProfile");
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
    
    
    void loadVideos(){
        
        VideoServices vs = new VideoServices();
        UserServices us = new UserServices();
        List<Video> videos;
        System.out.println("entered here");
//        System.out.println(us.getUser(username_profile.getText()).getId());
        videos = vs.getVideos(us.getUser(username_profile.getText()).getId());
//        System.out.println(videos.size());
        VBox root = new VBox();
        videos.forEach((v)->{
//            System.out.println(v);
            Label title = new Label();
            title.getStyleClass().add("video_title");
            title.setText(v.getTitle());
            WebView video = new WebView();
            video.setPrefHeight(380);
            video.setPrefWidth(680);
            video.getEngine().loadContent("<iframe width=\"640\" height=\"360\" src=\""+v.getUrl()+"\\ frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>");
            
            root.getChildren().addAll(title,video);
            videos_Container.setContent(root);
        });
    }
            
            
            
    void fadeTransition(String scene) throws IOException {

        FadeTransition ft = new FadeTransition();
        ft.setDuration(Duration.millis(500));
        ft.setNode(rootPane);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent second;
                    if (scene.equals("login")) {
                        second = (StackPane) FXMLLoader.load(getClass().getResource("/tunisiagottalent/ui/" + scene + ".fxml"));
                    } else {
                        second = (AnchorPane) FXMLLoader.load(getClass().getResource("/tunisiagottalent/ui/" + scene + ".fxml"));
                    }
                    Scene s = new Scene(second);
                    Stage current = (Stage) rootPane.getScene().getWindow();
                    current.setScene(s);
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        ft.play();

    }
       
}
