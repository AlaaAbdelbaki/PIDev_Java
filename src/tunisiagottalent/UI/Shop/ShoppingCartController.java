/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Shop;

import tunisiagottalent.Entity.Order;
import tunisiagottalent.Entity.Product;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Callback;
import tunisiagottalent.Entity.Cart;
import tunisiagottalent.services.OrderServices;
import tunisiagottalent.util.DataSource;

/**
 * FXML Controller class
 *
 * @author paspo
 */
public class ShoppingCartController implements Initializable {
    private Connection cnx;
    private Statement ste;
@FXML
    private AnchorPane anchorpane;
    @FXML
    private TableView<Product> tableProduct;
    @FXML
    private TableColumn table_id;
    @FXML
    private TableColumn<Product, String> table_productname;

    @FXML
    private TableColumn<Product, Integer> table_stock;
    @FXML
    private TableColumn<Product, Double> table_price;
    @FXML
    private TableColumn<Product, Integer> table_quantity;
    @FXML
    private TableColumn table_adddel;
    @FXML
    private TableColumn table_delete;
    @FXML
    private Rectangle rectange;
    @FXML
    private Button gotoorder;

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
    void cancel(ActionEvent event) {
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tunisiagottalent/UI/Shop/Shopview.fxml"));
        Parent third = loader.load();
        Stage stage = (Stage) anchorpane.getScene().getWindow();
        stage.close();
        
        
             System.out.println(Cart.instance);}
         catch (IOException ex) {
            Logger.getLogger(ShoppingCartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void affichershoppingcart() {
        shipping_address.setVisible(false);
        address.setVisible(false);
        city.setVisible(false);
        country.setVisible(false);
        postal_code.setVisible(false);
        commander.setVisible(false);
        rectange.setVisible(false);

      
        
        
        
            
            
            
                //table_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
                table_productname.setCellValueFactory(new PropertyValueFactory<>("Product_name"));
                table_stock.setCellValueFactory(new PropertyValueFactory<>("Stock"));
                table_price.setCellValueFactory(new PropertyValueFactory<>("Price"));
                table_quantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
                
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
                            p.setQuantity(1);
                            Cart.instance.RemoveProduct(p);
                        });

                        
                        setGraphic(deletebutton);
                        setText(null);
                        
                    }
                    
                };
                
            };
               return cell;         
        };
        Callback<TableColumn<Product, String>, TableCell<Product, String>> cellFactory1 = (param) -> {
                final TableCell<Product, String> cell = new TableCell<Product, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final HBox hbox = new HBox();
                        final Button addquantity = new Button("+");
                        final Button deletequantity = new Button("-");
                        hbox.getChildren().addAll(addquantity,deletequantity);

                        addquantity.setOnAction(event -> {
                            Product p = getTableView().getItems().get(getIndex());

                            if(p.getQuantity()>=p.getStock()){
                               addquantity.setDisable(true);
                            }
                            else{
                            int quantity = p.getQuantity()+1;
                            p.setQuantity(quantity);
                            tableProduct.refresh();
                            }
                        });
                        
                        deletequantity.setOnAction(event -> {
                            Product p = getTableView().getItems().get(getIndex());  

                            if(p.getQuantity()<=1){
                                deletequantity.setDisable(true);
                                //tableProduct.refresh();
                            }
                            else{
                            int quantity = p.getQuantity()-1;
                            p.setQuantity(quantity);
                            tableProduct.refresh();
                            }
                        });
                        setGraphic(hbox);
                        setText(null);
                        
                    }
                    
                };
                
            };
               return cell;         
        };
                
            Callback<TableColumn<Product, String>, TableCell<Product, String>> cellFactory2 = (param) -> {
            final TableCell<Product, String> cell = new TableCell<Product, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                            Product pa = getTableView().getItems().get(getIndex());
                          Label lb = new Label();
                          ImageView product_image_view = new ImageView();
                          product_image_view.setImage(new javafx.scene.image.Image("http://127.0.0.1:8000/assets/img/shop-img/"+pa.getImg()));
                          product_image_view.setFitHeight(80);
                          product_image_view.setFitWidth(80);
                          lb.setGraphic(product_image_view);
                           setGraphic(lb);
                           setText(null);
                    }
                    
                };
                
            };
            
            return cell;
        };       
                
                
                
                
                table_id.setCellFactory(cellFactory2);
                table_delete.setCellFactory(cellFactory);
                table_adddel.setCellFactory(cellFactory1);
                
                tableProduct.setItems(Cart.instance.getC());
                shipping_address.setVisible(true);
                address.setVisible(true);
                city.setVisible(true);
                country.setVisible(true);
                postal_code.setVisible(true);
                commander.setVisible(true);
                rectange.setVisible(true);
                
        
        
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
                    total = total + (product.getPrice()*product.getQuantity());
                }
                String adr = address.getText()+" "+city.getText()+" "+country.getText()+" "+postal_code.getText();
                Order o = new Order(total,adr);
                OrderServices os = new OrderServices();
                cnx = DataSource.getInstance().getCnx();
                os.addOrder(o, Cart.instance.getC());
                for (Product pro : tableProduct.getItems()){
                    int new_stock = pro.getStock() - pro.getQuantity();
                    String req = "update product set stock='"+new_stock+"' where id='"+pro.getId()+"' ;";
                        try {
                            ste=cnx.createStatement();
                            ste.executeUpdate(req);
                            pro.setStock(new_stock);
                        } catch (SQLException ex) {
                            Logger.getLogger(ShoppingCartController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                }
                tableProduct.refresh();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Order Palced !");
                alert.setContentText(" Do you want to make a pdf copy ??");
                alert.setHeaderText("Your Order Has Been Successfully Placed !");
                ImageView icon = new ImageView("/tunisiagottalent/UI/Shop/img/pdf.png");
                icon.setFitHeight(100);
                icon.setFitWidth(100);
                alert.setGraphic(icon);
                Optional<ButtonType> result = alert.showAndWait();
                if(result.isPresent() && result.get() == ButtonType.OK){
                        try {
                            testpdf(Cart.instance.getC());    
                        } catch (DocumentException | IOException ex) {
                            Logger.getLogger(ShoppingCartController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
                
                }
                
                
        });

    }

    public void setTestsc(ObservableList<Product> testsc) {
        this.testsc = testsc;
    }
    
    public void testpdf(ObservableList<Product> list) throws FileNotFoundException, DocumentException, BadElementException, IOException{
        
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("testpdf.pdf"));
        document.open();
        
        
        
        
        
        
        Image img = Image.getInstance("http://127.0.0.1:8000/assets/img/core-img/logo.png");
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
        Paragraph lign = new Paragraph("_______________________________________________________");
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
        document.add(new Image(img) {});
        document.add(blank1);
        document.add(blank2);
        document.add(blank1);
        document.add(blank2);
        document.add(blank1);
        document.add(blank2);
        document.add(lign);
        double totale=0;
        for(Product p : list){
            Paragraph product_name_par = new Paragraph(p.getProduct_name()+" : "+p.getPrice()+"$                                           Quantity : "+p.getQuantity());
            Image product_img_par = Image.getInstance("http://127.0.0.1:8000/assets/img/shop-img/"+p.getImg());
            product_img_par.scaleAbsolute(100, 100);
            Paragraph ligne = new Paragraph("_______________________________________________________");
            document.add(product_name_par);
            document.add(new Image(product_img_par) {});
            document.add(blank1);
            document.add(blank2);
            document.add(ligne);
            totale = totale+(p.getPrice()*p.getQuantity());
        }
        Paragraph total = new Paragraph("Total : "+Double.toString(totale)+"$");
        document.add(blank1);
        document.add(blank2);
        document.add(total);
        document.close();
    }
   
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(()->{
           
    affichershoppingcart();
    });
        
    }

}
