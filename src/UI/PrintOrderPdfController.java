/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entity.Product;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author paspo
 */
public class PrintOrderPdfController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    ScrollPane scroll;
    @FXML
    Label pdf_order_id;
    @FXML
    AnchorPane anchorepane;
    @FXML
    Button button_pdf;
    public void showorderpdf(ObservableList<Product> list){

        VBox vbox = new VBox();
        vbox.setSpacing(10);
        list.forEach((p) -> {
            HBox hbox = new HBox();
            hbox.setSpacing(10);
            ImageView product_image= new ImageView("/img/t-shirt.jpg");
            product_image.setFitHeight(100);
            product_image.setFitWidth(100);
            
            Label name_label = new Label(p.getProduct_name()+" : ");
            name_label.setStyle("-fx-font-weight: bold;");
            name_label.setTextFill(Color.web("#c31010"));
            
            Label price_label = new Label(Double.toString(p.getPrice()));
            
            Label qqt_label = new Label("quantité : 4");
            
            
            VBox name_price_qqt = new VBox();
            
            name_price_qqt.getChildren().addAll(name_label,price_label,qqt_label);
            
            hbox.getChildren().addAll(product_image,name_price_qqt);
            vbox.getChildren().addAll(hbox);
            rootPane.getChildren().setAll(vbox);
            //scroll.setContent(vbox);
        });
        
        button_pdf.setOnAction(event -> {
        WritableImage snapshot = anchorepane.snapshot(new SnapshotParameters(), null);
        
            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream("testpdf.pdf"));
                document.open();
                document.add((Element) snapshot);
                document.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PrintOrderPdfController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(PrintOrderPdfController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        });

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}
