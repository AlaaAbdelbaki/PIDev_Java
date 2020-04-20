/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.UI.Events;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXProgressBar;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import tunisiagottalent.Entity.Event;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import tunisiagottalent.Entity.Event_Participant;
import tunisiagottalent.services.EventService;
import tunisiagottalent.services.Event_ParticipantService;

import tunisiagottalent.util.UserSession;
import tunisiagottalent.util.sendEmailSMTP;

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
    @FXML
    private AnchorPane rootpane;
    @FXML
    private Button getTicket;
    private Event E;
    @FXML
    private JFXProgressBar progress;

    public void setE(Event E) {
        this.E = E;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {

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
            selectedFilmPoster.setImage(new Image("http://127.0.0.1:8000/assets/img/shop-img/" + E.getImg()));
            if ((E.getNb_places() == 0) || (E.getEnd_date().before(Date.from(Instant.now())))) {
                getTicket.setVisible(false);
                description.setText("The Event is Over Or The Tickets Sold Out");
            }

        });
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) rootpane.getScene().getWindow();
        stage.close();
    }
Task copyWorker;
    @FXML
    private void GetTicket(ActionEvent event) throws MessagingException, IOException, BadElementException, FileNotFoundException, DocumentException, SQLException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String myWeb = "http://127.0.0.1:8000/profile/" + UserSession.instance.getU().getId();
        int width = 300;
        int height = 300;
        String fileType = "png";

        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();

            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }


        } catch (WriterException ex) {
            Logger.getLogger(SelectedEventViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ImageView qrView = new ImageView();

        qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        com.itextpdf.text.Image iTextImage = com.itextpdf.text.Image.getInstance(baos.toByteArray());
        Event_ParticipantService eps=new Event_ParticipantService();
        Event_Participant ep=new Event_Participant(E.getId(), UserSession.instance.getU().getId(), java.sql.Date.valueOf(LocalDate.now()));
        eps.addEvent_Participant(ep);
        EventService es = new EventService();
        es.BuyTicket(E);
        TicketPDF(iTextImage);
      
          new Thread( ()->{
                         try {
            
                sendEmailSMTP.SendTicket(UserSession.instance.getU().getUsername(), UserSession.instance.getU().getEmail());
            } catch (MessagingException ex) {
                Logger.getLogger(SelectedEventViewController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SelectedEventViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
                    }).start();
        
        
       
        Stage stage1 = (Stage) rootpane.getScene().getWindow();
        stage1.close();
             try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("qr.fxml"));
            Parent third = loader.load();

            QrController controller = loader.<QrController>getController();
            controller.setImg(qrView);
            Scene s = new Scene(third);
            s.setFill(javafx.scene.paint.Color.TRANSPARENT);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setOpacity(0.9);
            stage.setTitle("QR");

            stage.setScene(s);

            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SelectedEventViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

    
   
    public void TicketPDF(com.itextpdf.text.Image qr) throws FileNotFoundException, DocumentException, BadElementException, IOException {

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("test.pdf"));
        document.open();

        com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance("http://127.0.0.1:8000/assets/img/core-img/logo.png");
        img.setBackgroundColor(BaseColor.DARK_GRAY);
        img.scaleAbsolute(250, 80);
        img.setAbsolutePosition(0, PageSize.A4.getHeight() - img.getScaledHeight());
        Font bold = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        bold.setColor(BaseColor.GRAY);
        Paragraph shareyourtalent = new Paragraph("Share Your Talent,\n     Move The World..", bold);
        shareyourtalent.setAlignment(Element.ALIGN_LEFT);
        Paragraph blank1 = new Paragraph("                              ");
        Paragraph blank2 = new Paragraph("                              ");
        Paragraph blank3 = new Paragraph("                              ");
        Paragraph blank4 = new Paragraph("                              ");
        Paragraph blank5 = new Paragraph("                              ");
        Paragraph blank6 = new Paragraph("                              ");
        Paragraph lign = new Paragraph("_______________________________________________________");
        Font sys = new Font(Font.FontFamily.HELVETICA, 23, Font.NORMAL);
        sys.setColor(BaseColor.RED);
        Paragraph thankyouforyourorder = new Paragraph("Thank You For Joining Our Event " + UserSession.instance.getU().getUsername(), sys);

        Font normal = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL);
        Paragraph datailsthankyou = new Paragraph("Dont Forget to bring your ticket to confirm your ID ", normal);

        document.add(img);
        document.add(blank1);
        document.add(blank2);
        document.add(shareyourtalent);
        document.add(blank2);
        document.add(thankyouforyourorder);
        document.add(datailsthankyou);
        document.add(qr);

        document.close();

    }
}
