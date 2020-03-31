/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tunisiagottalent.entity.Video;
import tunisiagottalent.util.DataSource;

/**
 *
 * @author alaa
 */
public class VideoServices {

    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public VideoServices() {
        cnx = DataSource.getInstance().getCnx();
    }

    public List<Video> getAll() {
        String req = "select * from video";
        List<Video> list = new ArrayList<>();
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Video(rs.getInt("id"), rs.getString("url"), rs.getString("title"), rs.getDate("publish_date"), rs.getInt("owner")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VideoServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<Video> getVideos(int owner) {
        String req = "select * from video where owner="+owner;
        List<Video> list = new ArrayList<>();
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);
            
            
            
            while (rs.next()) {
                list.add(new Video(rs.getInt("id"), rs.getString("url"), rs.getString("title"), rs.getDate("publish_date"), rs.getInt("owner")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VideoServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
