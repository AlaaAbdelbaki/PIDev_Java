package tunisiagottalent.UI.User;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;

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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import tunisiagottalent.Entity.User;
import tunisiagottalent.services.UserServices;
import tunisiagottalent.util.UserSession;

public class EditProfileController {

    @FXML
    private AnchorPane rootPane;

    private ImageView profilePicture;

    @FXML
    private TextField usernameInput;

    @FXML
    private TextField firstNameInput;

    @FXML
    private TextField lastNameInput;

    @FXML
    private Label imageName;

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

    private String imgp;
    private URI imguriUri;

    @FXML
    void initialize() {

        loadInfo();
        System.out.println("Edit Profile loaded ! ");
        genderInput.getItems().add("Male");
        genderInput.getItems().add("Female");
        usernameInput.setDisable(true);

    }

    void loadInfo() {
        UserServices us = new UserServices();
        UserSession s = UserSession.instance;

//        Image img=new Image("D:/Programming/Web/htdocs/annee_2019_2020/PIDev/web/assets/img/pics/unknown.jpg"); 
        Image img = new Image("tunisiagottalent/UI/User/img/unknown.jpg");

        usernameInput.setText(s.getU().getUsername());
        System.out.println(s.getU());
        if (s.getU().getName() != null) {
            firstNameInput.setText(s.getU().getName());
        }
        if (s.getU().getLastName() != null) {
            lastNameInput.setText(s.getU().getLastName());
        }
        if (s.getU().getPhone_number() != null) {
            phoneNumberInput.setText(s.getU().getPhone_number());
        }
        if (s.getU().getGender() != null) {
            //System.out.println(us.getUser(user).getGender());
            genderInput.setValue(s.getU().getGender().substring(0, 1).toUpperCase() + s.getU().getGender().substring(1));
        }
        if (s.getU().getBio() != null) {

            bioInput.setText(s.getU().getBio());
        }
        if (s.getU().getEmail() != null) {
            emailInput.setText(s.getU().getEmail());
        }
        if (s.getU().getAddress() != null) {
            addressInput.setText(s.getU().getAddress());
        }
        if (s.getU().getBirthday() != null) {
            birthdayInput.setValue(s.getU().getBirthday().toLocalDate());
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
        String profilePic = imageName.getText();
        if (phoneNumberInput.getText().matches("[0-9]+")) {
            phone_number = phoneNumberInput.getText();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Phone number field should only have numbers");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/tunisiagottalent/UI/Base/img/icon.png"));

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
        UserSession s = UserSession.instance;
        User user = new User(s.getU().getId(), username, email, password, gender, address, name, lastName, phone_number, bio, s.getU().getRole(), birthday, profilePic);
//        System.out.println(user);
        UserServices us = new UserServices();

        if (us.updateUser(user)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Information updated successfully !");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/tunisiagottalent/UI/Base/img/icon.png"));

//                    stage.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            try {
                AnchorPane p = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/User/profile.fxml"));
                rootPane.getChildren().setAll(p);

            } catch (IOException ex) {
                Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error processing the request try again later !");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/tunisiagottalent/UI/Base/img/icon.png"));
            //                    stage.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            try {
                AnchorPane p = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/User/profile.fxml"));
                rootPane.getChildren().setAll(p);

            } catch (IOException ex) {
                Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    void cancel(ActionEvent event) {
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/User/profile.fxml"));
            rootPane.getChildren().setAll(p);

        } catch (IOException ex) {
            Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void uploadProfilePic(ActionEvent event) {
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*png");

        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(imageFilter);
        Window stage = null;
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {

            imgp = file.getName();
            imguriUri = file.toURI();
            imageName.setText(imgp);

        }

    }

    @FXML
    void delete(ActionEvent event) {
        UserSession s = UserSession.instance;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("This action once done is irriversible are you sure you want to proceed ?");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/tunisiagottalent/UI/Base/img/icon.png"));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            String username = usernameInput.getText();
            UserServices us = new UserServices();
            if (us.delete(s.getU())) {
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Success !");
                alert2.setHeaderText(null);
                alert2.setContentText("Account deleted successfully !");
                Stage stage2 = (Stage) alert2.getDialogPane().getScene().getWindow();
                stage2.getIcons().add(new Image("/tunisiagottalent/UI/Base/img/icon.png"));
                alert2.showAndWait();
                try {
                    s.cleanUserSession();
                    AnchorPane p = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Base/main.fxml"));

                    rootPane.getChildren().setAll(p);

                } catch (IOException ex) {
                    Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Error");
                alert2.setHeaderText(null);
                alert2.setContentText("An error occured please try again later");
                Stage stage2 = (Stage) alert2.getDialogPane().getScene().getWindow();
                stage2.getIcons().add(new Image("/tunisiagottalent/UI/Base/img/icon.png"));
                alert2.showAndWait();
                try {
                    AnchorPane p = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/User/profile.fxml"));
                    rootPane.getChildren().setAll(p);

                } catch (IOException ex) {
                    Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } else {
            try {
                AnchorPane p = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/User/profile.fxml"));
                rootPane.getChildren().setAll(p);

            } catch (IOException ex) {
                Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
