/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entity.Order;
import Entity.Product;
import Entity.ShoppingCart;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import static com.itextpdf.text.Chunk.COLOR;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.OrderServices;

/**
 * FXML Controller class
 *
 * @author paspo
 */
public class ShoppingCartController implements Initializable {

    private PreparedStatement pset;
    private Connection cnx;
    private ResultSet rez;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private TableView<Product> tableProduct;
    @FXML
    private TableColumn<Product, Integer> table_id;
    @FXML
    private TableColumn<Product, String> table_productname;
    @FXML
    private TableColumn<Product, String> table_image;
    @FXML
    private TableColumn<Product, Integer> table_stock;
    @FXML
    private TableColumn<Product, Double> table_price;
    @FXML
    private TableColumn table_delete;
    @FXML
    private Rectangle rectange;
    @FXML
    private Button gotoorder;
    @FXML
    private Button button_shopping_cart_test;
    @FXML
    private Label shipping_address;
    @FXML
    private TextField address;
    @FXML
    private TextField city;
    @FXML
    private TextField country;
    @FXML
    private TextField postal_code;
    @FXML
    private Button commander;

    ObservableList<Product> testsc;

    public static ObservableList<Product> product_list;

    @FXML
    public void affichershoppingcart() {
        shipping_address.setVisible(false);
        address.setVisible(false);
        city.setVisible(false);
        country.setVisible(false);
        postal_code.setVisible(false);
        commander.setVisible(false);
        rectange.setVisible(false);

        ObservableList<Product> list;
        list = FXCollections.observableArrayList();
        gotoorder.setOnAction(event -> {
 
            
            for(Product ppp : testsc){
            list.add(new Product(ppp.getId(),ppp.getProduct_name(),ppp.getImg(),ppp.getStock(),ppp.getPrice()));
        }
            System.out.println(list);
                table_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
                table_productname.setCellValueFactory(new PropertyValueFactory<>("Product_name"));
                table_image.setCellValueFactory(new PropertyValueFactory<>("Img"));
                table_stock.setCellValueFactory(new PropertyValueFactory<>("Stock"));
                table_price.setCellValueFactory(new PropertyValueFactory<>("Price"));
                
                Callback<TableColumn<Product, String>, TableCell<Product, String>> cellFactory = (param) -> {
                final TableCell<Product, String> cell = new TableCell<Product, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button deletebutton = new Button("Delete");
                            deletebutton.setOnAction(event -> {
                            Product p = getTableView().getItems().get(getIndex());
                            ShoppingCart sc = new ShoppingCart();
                            sc.removeItem(p);
                            list.remove(p);
                            
                        });

                        
                        setGraphic(deletebutton);
                        setText(null);
                        
                    }
                    
                };
                
            };
               return cell;         
        };
                table_delete.setCellFactory(cellFactory);
                tableProduct.setItems(list);
                shipping_address.setVisible(true);
                address.setVisible(true);
                city.setVisible(true);
                country.setVisible(true);
                postal_code.setVisible(true);
                commander.setVisible(true);
                rectange.setVisible(true);
                gotoorder.setDisable(true);
        });
        
        commander.setOnAction(event -> {
                try{
                    Integer i = Integer.parseInt(postal_code.getText());
                    }
                 catch(NumberFormatException e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Enter a valide postal code");
                    alert.showAndWait();
        }
            
            
                if(address.getText().trim().isEmpty() || city.getText().trim().isEmpty() || country.getText().trim().isEmpty() || postal_code.getText().trim().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Please fill all fields");
                    alert.showAndWait();
                }
                
                else{
                    double total = 0;
                for(Product product : tableProduct.getItems()){
                    total = total + product.getPrice();
                }
                String adr = address.getText()+" "+city.getText()+" "+country.getText()+" "+postal_code.getText();
                Order o = new Order(total,adr);
                OrderServices os = new OrderServices();
                os.addOrder(o, list);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Order Palced !");
                alert.setContentText(" Do you want to make a pdf copy ??");
                alert.setHeaderText("Your Order Has Been Successfully Placed !");
                ImageView icon = new ImageView("/img/pdf.png");
                icon.setFitHeight(100);
                icon.setFitWidth(100);
                alert.setGraphic(icon);
                Optional<ButtonType> result = alert.showAndWait();
                if(result.isPresent() && result.get() == ButtonType.OK){
                        try {
                            testpdf(list);    
                        } catch (DocumentException ex) {
                            Logger.getLogger(ShoppingCartController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(ShoppingCartController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
                }
                
                
        });

    }
    
    public void testpdf(ObservableList<Product> list) throws FileNotFoundException, DocumentException, BadElementException, IOException{
        
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("testpdf.pdf"));
        document.open();
        
        
        
        
        
        
        Image img = Image.getInstance("C:\\Users\\paspo\\Desktop\\PIDev_java\\PIDev_Java\\src\\img\\logo.png");
        img.scaleAbsolute(250, 80);
        img.setAbsolutePosition(0, PageSize.A4.getHeight() - img.getScaledHeight());
        Font bold=new Font(FontFamily.HELVETICA, 12, Font.BOLD);
        bold.setColor(BaseColor.GRAY);
        Paragraph shareyourtalent = new Paragraph("Share Your Talent,\n     Move The World..",bold);
        shareyourtalent.setAlignment(Element.ALIGN_LEFT);
        Paragraph blank1 = new Paragraph("                              ");
        Paragraph blank2 = new Paragraph("                              ");
        Paragraph blank3 = new Paragraph("                              ");
        Paragraph blank4 = new Paragraph("                              ");
        Paragraph blank5 = new Paragraph("                              ");
        Paragraph blank6 = new Paragraph("                              ");
        Font sys=new Font(FontFamily.HELVETICA, 23,Font.NORMAL);
        sys.setColor(BaseColor.RED);
        Paragraph thankyouforyourorder = new Paragraph("Thank You For Your Order",sys);

        PdfContentByte canvas = writer.getDirectContent();
        canvas.rectangle(50, 600, 500, 40);
        canvas.setColorFill(BaseColor.BLACK);
        canvas.fill();
        Font normal = new Font(FontFamily.HELVETICA , 9,Font.NORMAL);
        Paragraph datailsthankyou = new Paragraph("Thank you for ordering. We received your order and will begin processing it soon.\n Your order information appears bellow :",normal);
              
        
        
        document.add(blank1);
        document.add(blank2);
        document.add(shareyourtalent);
        document.add(blank2);
        document.add(thankyouforyourorder);
        document.add(datailsthankyou);
        document.add(new Image(img) {});;
        document.add(blank1);
        document.add(blank2);
        document.add(blank1);
        document.add(blank2);
        document.add(blank1);
        document.add(blank2);
        for(Product p : list){
            Paragraph product_name_par = new Paragraph(p.getProduct_name()+" : "+p.getPrice());
            Image product_img_par = Image.getInstance(p.getImg());
            product_img_par.scaleAbsolute(100, 100);
            document.add(product_name_par);
            document.add(new Image(product_img_par) {});
            
        }
        
        document.close();
    }
   
    @Override

    public void initialize(URL url, ResourceBundle rb) {

        affichershoppingcart();
    }

}
