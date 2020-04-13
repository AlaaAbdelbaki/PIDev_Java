package tunisiagottalent.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import tunisiagottalent.entity.User;
import tunisiagottalent.services.UserServices;
import tunisiagottalent.util.UserSession;

public class EditProfileController {

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
    private TextField usernameInput;

    @FXML
    private TextField firstNameInput;

    @FXML
    private TextField lastNameInput;

    @FXML
    private TextField phoneNumberInput;

    @FXML
    private TextArea bioInput;

    @FXML
    private TextField emailInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private TextField addressInput;

    @FXML
    private ChoiceBox<String> genderInput;

    @FXML
    private DatePicker birthdayInput;

    @FXML
    void initialize() {

        rootPane.setOpacity(0);
        fadein();
        loadInfo();
        System.out.println("Edit Profile loaded ! ");
        genderInput.getItems().add("Male");
        genderInput.getItems().add("Female");
        usernameInput.setDisable(true);

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
        UserServices us = new UserServices();
        UserSession s = UserSession.instance;
        dashboard_icon.setVisible(false);
        dashboard_label.setVisible(false);
        username.setText(s.getU().getUsername());
        if (us.getRole(s.getU().getUsername()).contains("ROLE_ADMIN")) {
            dashboard_icon.setVisible(true);
            dashboard_label.setVisible(true);
        }
//        Image img=new Image("D:/Programming/Web/htdocs/annee_2019_2020/PIDev/web/assets/img/pics/unknown.jpg"); 
        Image img = new Image("tunisiagottalent/ui/img/unknown.jpg");
        profilePicture.setImage(img);
        usernameInput.setText(s.getU().getUsername());
//        System.out.println(s.getU());
        if (us.getUser(s.getU().getUsername()).getName() != null) {
            firstNameInput.setText(us.getUser(s.getU().getUsername()).getName());
        }
        if (us.getUser(s.getU().getUsername()).getLastName() != null) {
            lastNameInput.setText(us.getUser(s.getU().getUsername()).getLastName());
        }
        if (us.getUser(s.getU().getUsername()).getPhone_number() != null) {
            phoneNumberInput.setText(us.getUser(s.getU().getUsername()).getPhone_number());
        }
        if (us.getUser(s.getU().getUsername()).getGender() != null) {
            //System.out.println(us.getUser(user).getGender());
            genderInput.setValue(us.getUser(s.getU().getUsername()).getGender().substring(0, 1).toUpperCase() + us.getUser(s.getU().getUsername()).getGender().substring(1));
        }
        if (us.getUser(s.getU().getUsername()).getBio() != null) {

            bioInput.setText(us.getUser(s.getU().getUsername()).getBio());
        }
        if (us.getUser(s.getU().getUsername()).getEmail() != null) {
            emailInput.setText(us.getUser(s.getU().getUsername()).getEmail());
        }
        if (us.getUser(s.getU().getUsername()).getAddress() != null) {
            addressInput.setText(us.getUser(s.getU().getUsername()).getAddress());
        }
        if (us.getUser(s.getU().getUsername()).getBirthday() != null) {
            birthdayInput.setValue(us.getUser(s.getU().getUsername()).getBirthday().toLocalDate());
        }

    }

    @FXML
    void disconnect(MouseEvent event) {
        try {
            UserSession.instance.cleanUserSession();
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
    void dashboard(MouseEvent event) {
        try {
            fadeTransition("dashboard");
        } catch (IOException ex) {
            Logger.getLogger(HomepageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void submit(ActionEvent event) {
        String username = usernameInput.getText();
        String email = emailInput.getText();
        String password = passwordInput.getText();

        System.out.println(password);
        String gender;
        if (genderInput.getValue() != null) {
            gender = genderInput.getValue();
        } else {
            gender = null;
        }
        String name = firstNameInput.getText();
        String lastName = lastNameInput.getText();
        String bio = bioInput.getText();
        String phone_number = "";
        if (phoneNumberInput.getText().matches("[0-9]+")) {
            phone_number = phoneNumberInput.getText();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Password field contains characters !");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/tunisiagottalent/ui/img/icon.png"));

            alert.showAndWait();
            return;
        }
//        System.out.println(phone_number);
        Date birthday;
        if (birthdayInput.getValue() != null) {
            birthday = Date.valueOf(birthdayInput.getValue());
        } else {
            birthday = null;
        }

        String address = addressInput.getText();

        User user = new User(username, email, password, gender, address, name, lastName, bio, phone_number, birthday);
        UserServices us = new UserServices();

        if (us.updateUser(user)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Information updated successfully !");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/tunisiagottalent/ui/img/icon.png"));

//                    stage.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            try {
                fadeTransition("profile");
            } catch (IOException ex) {
                Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error processing the request try again later !");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/tunisiagottalent/ui/img/icon.png"));
            //                    stage.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            try {
                fadeTransition("profile");
            } catch (IOException ex) {
                Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    void cancel(ActionEvent event) {
        try {
            fadeTransition("profile");
        } catch (IOException ex) {
            Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void delete(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("This action once done is irriversible are you sure you want to proceed ?");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/tunisiagottalent/ui/img/icon.png"));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            String username = usernameInput.getText();
            UserServices us = new UserServices();
            if (us.delete(username)) {
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Success !");
                alert2.setHeaderText(null);
                alert2.setContentText("Account deleted successfully !");
                Stage stage2 = (Stage) alert2.getDialogPane().getScene().getWindow();
                stage2.getIcons().add(new Image("/tunisiagottalent/ui/img/icon.png"));
                alert2.showAndWait();
                try {
                    fadeTransition("login");
                } catch (IOException ex) {
                    Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Error");
                alert2.setHeaderText(null);
                alert2.setContentText("An error occured please try again later");
                Stage stage2 = (Stage) alert2.getDialogPane().getScene().getWindow();
                stage2.getIcons().add(new Image("/tunisiagottalent/ui/img/icon.png"));
                alert2.showAndWait();
                try {
                    fadeTransition("login");
                } catch (IOException ex) {
                    Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            try {
                fadeTransition("profile");
            } catch (IOException ex) {
                Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

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
