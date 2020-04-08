package tunisiagottalent.UI;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import tunisiagottalent.Entity.Competition;
import tunisiagottalent.services.CompetitionServices;


public class User_CompetitionsController {

    @FXML
    private GridPane competitions;
  
    @FXML
    private AnchorPane Competitions_list;

    @FXML
    void initialize() {
        CompetitionServices cs=new CompetitionServices();
      

        ObservableList<Competition> tab=cs.getAll();
        tab.forEach((comp) -> { 
            
           Label start=  new Label(comp.getCompetition_date().toString());
           start.setTextFill(javafx.scene.paint.Color.WHITE);
           start.setFont(Font.font("Cambria", 20));
           Label end= new Label(comp.getCompetition_end_date().toString()); 
           end.setTextFill(javafx.scene.paint.Color.WHITE);
           end.setFont(Font.font("Cambria", 20));
           Label sub= new Label(comp.getSubject());
           sub.setFont(Font.font("Cambria", 16));
           sub.setTextFill(javafx.scene.paint.Color.WHITE);
            JFXButton v=new JFXButton("View");
            JFXButton p=new JFXButton("Participate");
            v.resize(175, 45);
            v.setStyle("-fx-text-fill: white;-fx-font-size:20px;");
            v.setRipplerFill(javafx.scene.paint.Color.GREEN);
            p.setStyle("-fx-text-fill: white;-fx-font-size:20px; -fx-background-color:#F39C12");
            p.setRipplerFill(javafx.scene.paint.Color.BLUE);
            p.resize(175, 45);
            v.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                               try {
                               Competition c = comp;
                               FXMLLoader loader = new FXMLLoader(getClass().getResource("View_Competition.fxml"));
                               Parent root = loader.load();
                               View_CompetitionController controller = loader.<View_CompetitionController>getController();
                                controller.setCompetition(c);
                              
                                Competitions_list.getChildren().setAll(root);

                                } catch (IOException ex) {
                                    Logger.getLogger(User_CompetitionsController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
    });
                    p.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent event) {
                                  try {
                            Competition c = comp;
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("Competition_Participate.fxml"));
                            Parent root = loader.load();
                            Competition_ParticipateController controller = loader.<Competition_ParticipateController>getController();
                                controller.setCompetition(c);
                            Competitions_list.getChildren().setAll(root);
                           
                                } catch (IOException ex) {
                                    Logger.getLogger(User_CompetitionsController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                   
                                }
    });
            
            competitions.addRow(tab.indexOf(comp), start,end,sub,v,p);
            
            RowConstraints row = new RowConstraints(90);
            
            competitions.getRowConstraints().add(row);
                            
            
            
                });
        competitions.setPadding(new Insets(10, 10, 10, 10)); 
        competitions.setStyle("-fx-background-color: #0c0527");
              
   
       
        


    }

}

 /* 
        Subject.setCellValueFactory(new PropertyValueFactory<Competition,String>("subject"));
        startDate.setCellValueFactory(new PropertyValueFactory<Competition,Timestamp>("competition_date"));
        endDate.setCellValueFactory(new PropertyValueFactory<Competition,Timestamp>("competition_end_date"));
        Action.setCellFactory(param -> new ListCell<Competition, Void>() {
                    private final Button participateButton = new Button("Participate");
                    private final Button ViewButton = new Button("View");
                    private final HBox pane = new HBox(ViewButton,participateButton);

                    {
                        participateButton.setOnAction(event -> {
                            try {
                            Competition c = getTableView().getItems().get(getIndex());
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("Competition_Participate.fxml"));
                            Parent root = loader.load();
                            Competition_ParticipateController controller = loader.<Competition_ParticipateController>getController();
                                controller.setCompetition(c);
                            Competitions_list.getChildren().setAll(root);
                           
                                } catch (IOException ex) {
                                    Logger.getLogger(User_CompetitionsController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            
                        });

                        ViewButton.setOnAction(event -> {
                            try {
                               Competition c = getTableView().getItems().get(getIndex());
                               FXMLLoader loader = new FXMLLoader(getClass().getResource("View_Competition.fxml"));
                               Parent root = loader.load();
                               View_CompetitionController controller = loader.<View_CompetitionController>getController();
                                controller.setCompetition(c);
                              
                                Competitions_list.getChildren().setAll(root);

                                } catch (IOException ex) {
                                    Logger.getLogger(User_CompetitionsController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            
                                
                        });
                    }
             @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                setGraphic(empty ? null : pane);
            }
                });*/
