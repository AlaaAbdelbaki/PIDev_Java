/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.User;

import java.io.IOException;
import java.sql.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import tunisiagottalent.Entity.Review;

import tunisiagottalent.Entity.User;
import tunisiagottalent.UI.Competitions.AdminCompetitions;
import tunisiagottalent.UI.Reviews.User_detailsController;
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

        loadTreeView();

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
        

        Callback<TableColumn<User, String>, TableCell<User, String>> cellFactory = (param) -> {
            final TableCell<User, String> cell = new TableCell<User, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button deletebutton = new Button("Delete");
                        deletebutton.getStyleClass().add("delete");
                        deletebutton.setOnAction(event -> {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Warning");
                            alert.setHeaderText(null);
                            alert.setContentText("This action once done is irriversible are you sure you want to proceed ?");
                            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                            stage.getIcons().add(new Image("/tunisiagottalent/UI/Base/img/icon.png"));
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK) {
                                User u = getTableView().getItems().get(getIndex());
                                usersList.remove(u);
                                us.delete(u);
                            }
                        });

                        setGraphic(deletebutton);
                        setText(null);

                    }

                }
            ;

            };
            
            return cell; //To change body of generated lambdas, choose Tools | Templates.
        };
            Callback<TableColumn<User, String>, TableCell<User, String>> cellFactory2 = (param) -> {
            final TableCell<User, String> cell = new TableCell<User, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button ViewButton = new Button("View Videos");
                        ViewButton.setStyle("-fx-background-color:green;");
                        ViewButton.setOnAction(event -> {
                             try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("View_Videos.fxml"));
            Parent third = loader.load();
            User u = getTableView().getItems().get(getIndex());
            View_VideosController controller = loader.<View_VideosController>getController();
            controller.setUser(u);
            Scene s = new Scene(third);
            s.setFill(Color.TRANSPARENT);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setOpacity(0.9);
            stage.setTitle("Video Details");
            
            stage.setScene(s);

            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DashboardUsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
                          
                        });

                        setGraphic(ViewButton);
                        setText(null);

                    }

                }
            ;

            };
            
            return cell; //To change body of generated lambdas, choose Tools | Templates.
        };
        videos.setCellFactory(cellFactory2);
        userId.setSortType(TableColumn.SortType.ASCENDING);
        userId.setSortable(true);
        delete.setCellFactory(cellFactory);
        usersInfo.setItems(usersList);
        usersInfo.setRowFactory( tv -> {
    TableRow<User> row = new TableRow<>();
    row.setOnMouseClicked(event -> {
        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
            User rowData = row.getItem();
             try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tunisiagottalent/UI/Reviews/User_details.fxml"));
            Parent third = loader.load();
            
            User_detailsController controller = loader.<User_detailsController>getController();
            controller.setU(rowData);
            Scene s = new Scene(third);
            s.setFill(Color.TRANSPARENT);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setOpacity(0.8);
            stage.setTitle("User Details");
            
            stage.setScene(s);

            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DashboardUsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    });
    return row ;
});

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

        Callback<TableColumn<User, String>, TableCell<User, String>> cellFactory = (param) -> {
            final TableCell<User, String> cell = new TableCell<User, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button deletebutton = new Button("Delete");
                        deletebutton.getStyleClass().add("delete");
                        deletebutton.setOnAction(event -> {
                            User u = getTableView().getItems().get(getIndex());
                            usersList.remove(u);
                            us.delete(u);
                        });

                        setGraphic(deletebutton);
                        setText(null);

                    }

                }
            ;

            };
            
            return cell;
        };
        userId.setSortType(TableColumn.SortType.ASCENDING);
        userId.setSortable(true);
        delete.setCellFactory(cellFactory);

        usersInfo.setItems(usersList);

    }

}
