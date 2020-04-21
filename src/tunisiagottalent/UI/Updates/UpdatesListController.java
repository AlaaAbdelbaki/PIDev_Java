/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Updates;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import tunisiagottalent.Entity.Updates;
import tunisiagottalent.services.ServiceArticle;
import tunisiagottalent.services.ServiceUpdates;

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class UpdatesListController implements Initializable {
    
    @FXML
    private AnchorPane ContentPane;
    @FXML
    private TableView<Updates> ListArticle;
    @FXML
    private TableColumn<Updates, String> colTitle;
    @FXML
    private TableColumn<Updates, Void> colimg;
    
    @FXML
    private Pagination pagination;
    @FXML
    private JFXButton PostBlog;
    @FXML
    private JFXTextField search;
    
    @FXML
    private TableColumn<Updates, Void> Edit;
    @FXML
    private TableColumn<Updates, Void> Delete;
    
    ServiceUpdates as = new ServiceUpdates();
    List<Updates> list = as.afficher();
    private final static int rowsPerPage = 3;    
    @FXML
    private TableColumn<?, ?> pubdate;
    @FXML
    private TableColumn<?, ?> category;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        search.setStyle("-fx-text-fill:white;");
        pagination.setPageFactory(this::loadTable);
    }    
    
    public Node loadTable(Integer pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, list.size());
        if (fromIndex >= toIndex) {
            return null;
        }
        List<Updates> page = list.subList(fromIndex, toIndex);
        
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        pubdate.setCellValueFactory(new PropertyValueFactory<>("publish_date"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        
        Callback<TableColumn<Updates, Void>, TableCell<Updates, Void>> cellfactorydelete = (param) -> {
            final TableCell<Updates, Void> cell = new TableCell<Updates, Void>() {
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button deletebutton = new Button("Delete");
                        deletebutton.setOnAction(event -> {
                            Updates p = getTableView().getItems().get(getIndex());
                            as.supprimer(p);
                            list.remove(p);
                            
                            pagination.setPageFactory((e) -> {
                                return loadTable(pagination.getCurrentPageIndex());
                            });
                            
                        });
                        
                        setGraphic(deletebutton);
                        setText(null);
                        
                    }
                    
                }
            ;
            
            };
            
            return cell;
        };
        Callback<TableColumn<Updates, Void>, TableCell<Updates, Void>> cellfactoryedit = (param) -> {
            final TableCell<Updates, Void> cell = new TableCell<Updates, Void>() {
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button editbutton = new Button("Edit");
                        editbutton.setOnAction(event -> {
                            Updates pa = getTableView().getItems().get(getIndex());
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("EditUpdate.fxml"));
                                Parent second = loader.load();
                                
                                EditUpdateController secondcontroller = loader.<EditUpdateController>getController();
                                secondcontroller.setA(pa);
                                
                                Scene s = new Scene(second);
                                s.setFill(Color.TRANSPARENT);
                                Stage stageedit = new Stage();
                                stageedit.initStyle(StageStyle.TRANSPARENT);
                                stageedit.setOpacity(0.95);
                                stageedit.setTitle("Edit Update n° : " + pa.getId());
                                stageedit.initOwner(ContentPane.getScene().getWindow());
                                stageedit.setScene(s);
                                
                                stageedit.show();
                                
                            } catch (IOException ex) {
                                Logger.getLogger(UpdatesListController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                        });
                        
                        setGraphic(editbutton);
                        setText(null);
                        
                    }
                    
                }
            ;
            
            };
            
            return cell;
        };
        Callback<TableColumn<Updates, Void>, TableCell<Updates, Void>> cellfactoryimage = (param) -> {
            final TableCell<Updates, Void> cell = new TableCell<Updates, Void>() {
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Updates pa = getTableView().getItems().get(getIndex());
                        Label lb = new Label();
                        ImageView product_image_view = new ImageView();
                        product_image_view.setImage(new Image("http://127.0.0.1:8000/assets/img/shop-img/" + pa.getImg()));
                        product_image_view.setFitHeight(150);
                        product_image_view.setFitWidth(150);
                        lb.setGraphic(product_image_view);
                        setGraphic(lb);
                        setText(null);
                    }
                    
                }
            ;
            
            };
            
            return cell;
        };
        
        colimg.setCellFactory(cellfactoryimage);
        
        Edit.setCellFactory(cellfactoryedit);
        Delete.setCellFactory(cellfactorydelete);
        
        FilteredList<Updates> filteredArticles = new FilteredList<>(FXCollections.observableArrayList(page), b -> true);
        
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredArticles.setPredicate((Predicate<? super Updates>) Article -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (Article.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
                
            });
        });
        SortedList<Updates> sortedArticles = new SortedList<>(filteredArticles);
        
        sortedArticles.comparatorProperty().bind(ListArticle.comparatorProperty());
        
        ListArticle.setItems(sortedArticles);
        
        return (ListArticle);
    }
    
    @FXML
    private void AddPostPopup(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Updates.fxml"));
            Parent third = loader.load();
            
            Scene s = new Scene(third);
            s.setFill(Color.TRANSPARENT);
            third.setTranslateY(s.getHeight());
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setOpacity(1);
            stage.setTitle("Update");
            stage.initOwner(ContentPane.getScene().getWindow());
            stage.setScene(s);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(UpdatesListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
