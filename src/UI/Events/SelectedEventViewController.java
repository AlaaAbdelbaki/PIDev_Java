/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Events;

import tunisiagottalent.Entity.Event;
import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author hela
 */
public class SelectedEventViewController implements Initializable {

    private String IDE;
    @FXML
    private ImageView selectedFilmPoster;
    @FXML
    private Text title;
    @FXML
    private Text description;
    @FXML
    private Text startDate;
    @FXML
    private Text endDate;
    @FXML
    private Text location;
    @FXML
    private Text nbPlaces1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void showDetails(Event E) {

        IDE = String.valueOf(E.getId());
        title.setText(E.getTitle());
        description.setText(E.getDescription());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
       String strDate = dateFormat.format(E.getStart_date()); 
       String strDate2 = dateFormat.format(E.getEnd_date());  
         startDate.setText(strDate);
         endDate.setText(strDate2);
        location.setText(E.getLocation());
        nbPlaces1.setText(String.valueOf(E.getNb_places()));
        selectedFilmPoster.setImage(new Image("/album/"+E.getImg()));
    }

}
