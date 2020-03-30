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
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import tunisiagottalent.entity.User;
import tunisiagottalent.services.UserServices;

/**
 * FXML Controller class
 *
 * @author alaa
 */
public class DashboardUsersController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Rectangle navbar1;

    @FXML
    private Rectangle navbar;

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
    private Label username;

    @FXML
    private TextField usernameInput;

    @FXML
    private ImageView home_icon;

    @FXML
    private Label home_label;

    @FXML
    private TableView<User> usersInfo;

    @FXML
    private TableColumn<User, Integer> userId;

    @FXML
    private TableColumn<User, String> userUsername;

    @FXML
    private TableColumn<User, String> userLastName;

    @FXML
    private TableColumn<User, String> userFirstName;

    @FXML
    private TableColumn<User, Date> userBirthday;

    @FXML
    private TableColumn<User, String> userEmail;

    @FXML
    private TableColumn<User, String> userPhoneNumber;

    @FXML
    private TableColumn<User, String> userGender;

    @FXML
    private TableColumn videos;

    @FXML
    private TableColumn delete;

    private ObservableList<User> usersList;

    @FXML
    void initialize() {

        rootPane.setOpacity(0);
        fadein();
        loadInfo();
        loadTreeView();
        System.out.println("Dashboard users loaded ! ");

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

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void disconnect(MouseEvent event) {
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
    void profile(MouseEvent event) {
        try {
            fadeTransition("profile");
        } catch (IOException ex) {
            Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void home(MouseEvent event) {
        try {
            fadeTransition("homepage");
        } catch (IOException ex) {
            Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void dashboard(MouseEvent event) {
        try {
            fadeTransition("dashboard");
        } catch (IOException ex) {
            Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void searchUser(KeyEvent event) {
        String query = usernameInput.getText();
        loadTreeView(query);
    }

    void loadTreeView() {
        UserServices us = new UserServices();

        usersList = FXCollections.observableArrayList(us.getAll());

//        usersList.forEach(System.out::println);
        userId.setCellValueFactory(new PropertyValueFactory<>("id"));
        userUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        userEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        userGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        userLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        userFirstName.setCellValueFactory(new PropertyValueFactory<>("name"));
        userPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        userBirthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));

        userId.setSortType(TableColumn.SortType.ASCENDING);
        userId.setSortable(true);

        usersInfo.setItems(usersList);

    }

    void loadTreeView(String q) {
        UserServices us = new UserServices();

        usersList = FXCollections.observableArrayList(us.getAll(q));

//        usersList.forEach(System.out::println);
        userId.setCellValueFactory(new PropertyValueFactory<>("id"));
        userUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        userEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        userGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        userLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        userFirstName.setCellValueFactory(new PropertyValueFactory<>("name"));
        userPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        userBirthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));

        userId.setSortType(TableColumn.SortType.ASCENDING);
        userId.setSortable(true);

        usersInfo.setItems(usersList);

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
