/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Reviews;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Rating;
import tunisiagottalent.Entity.Cart;
import tunisiagottalent.Entity.Product;
import tunisiagottalent.Entity.Review;
import tunisiagottalent.UI.Shop.ShopController;
import tunisiagottalent.UI.Shop.ViewItemDetailsController;
import tunisiagottalent.services.ReviewService;
import tunisiagottalent.util.UserSession;

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class User_ReviewsController implements Initializable {

    @FXML
    private JFXButton delete;
    @FXML
    private AnchorPane root;
    ReviewService rs = new ReviewService();
    ObservableList<Review> or = FXCollections.observableArrayList(rs.getbyUser(UserSession.instance.getU()));
    @FXML
    private VBox vboxratings;
    @FXML
    private Pagination pagination;
    private final static int rowsPerPage = 4;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        pagination.setPageFactory(this::loadReviewsforuser);
    }

    public Node loadReviewsforuser(int pageIndex) {
        
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, or.size());
        if (fromIndex >= toIndex) {
            return null;
        }
        ObservableList<Review> rev = FXCollections.observableArrayList(or.subList(fromIndex, toIndex));

        vboxratings.getChildren().clear();
        rev.forEach((p) -> {

            HBox hbox = new HBox();
            CheckBox checkBox1 = new CheckBox("");
            Label tLabel = new Label();
            tLabel.setText(p.getTitle());
            tLabel.setFont(new Font("Bold", 30));
            tLabel.setTextFill(Color.web("#55b3f3"));
            Label CategoryLabel = new Label();
            CategoryLabel.setText(p.getTitle());
            CategoryLabel.setFont(new Font("Bold", 30));
            CategoryLabel.setTextFill(Color.GREENYELLOW);
            Label ContentLabel = new Label();
            ContentLabel.setText(p.getTitle());
            ContentLabel.setFont(new Font("Bold", 30));
            ContentLabel.setTextFill(Color.BLACK);

            Rating rate = new Rating(5, p.getRating());
            rate.setMouseTransparent(true);
            rate.setOpacity(1);
            hbox.setSpacing(10);

            checkBox1.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    delete.setOnAction((e) -> {

                        hbox.getChildren().clear();
                        rs.deleteReview(p);
                        or.remove(p);
                    });
                }
            });

            hbox.getChildren().addAll(checkBox1, tLabel, CategoryLabel, ContentLabel, rate);

            vboxratings.getChildren().add(hbox);
            vboxratings.setSpacing(30);
        });

        return vboxratings;
    }

    @FXML
    private void add(ActionEvent event) {
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/tunisiagottalent/UI/Reviews/AddReview.fxml"));
            root.getChildren().setAll(p);

        } catch (IOException ex) {
            Logger.getLogger(User_ReviewsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
