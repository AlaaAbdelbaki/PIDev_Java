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
import tunisiagottalent.Entity.User;
import tunisiagottalent.Entity.video;
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

    public List<video> getAll() {
        String req = "select * from video inner join user on(video.owner=user.id) ";
        List<video> list = new ArrayList<>();
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new video(rs.getInt("id"), rs.getString("url"), rs.getString("title"), rs.getTimestamp("publish_date"),
                        new User(rs.getInt("id"),
                                rs.getString("username"),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("sexe"),
                                rs.getString("adresse"),
                                rs.getString("name"),
                                rs.getString("first_name"),
                                rs.getString("telephone_number"),
                                rs.getString("bio"),
                                rs.getString("roles"),
                                rs.getDate("birthday"),
                                rs.getString("profile_pic"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VideoServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<video> getVideos(int owner) {
        String req = "select * from video inner join user on(video.owner=user.id) where owner=" + owner;
        List<video> list = new ArrayList<>();
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);

            while (rs.next()) {
                list.add(new video(rs.getInt("id"), rs.getString("url"), rs.getString("title"), rs.getTimestamp("publish_date"),
                        new User(rs.getInt("id"),
                                rs.getString("username"),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("sexe"),
                                rs.getString("adresse"),
                                rs.getString("name"),
                                rs.getString("first_name"),
                                rs.getString("telephone_number"),
                                rs.getString("bio"),
                                rs.getString("roles"),
                                rs.getDate("birthday"),
                                rs.getString("profile_pic"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VideoServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean AddVideo(video v) {
        String url = "";
        String find = "select url from video where url = '" + v.getUrl() + "'";

        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(find);
            while (rs.next()) {
                url = rs.getString("url");
            }
        } catch (SQLException ex) {
            Logger.getLogger(VideoServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (v.getUrl().equals(url)) {
            System.out.println("Video exitst !");
            return false;
        } else {
            String sql = "insert into video(url,title,publish_date,owner) values('" + v.getUrl() + "','" + v.getTitle() + "','" + v.getPublish_date() + "','" + v.getOwner().getId() + "')";
            try {
                ste = cnx.createStatement();
                ste.executeUpdate(sql);

                return true;
            } catch (SQLException ex) {
                Logger.getLogger(VideoServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }
     public void delete(video c){
        String req="delete from video where id=?";
        try {
            pst=cnx.prepareStatement(req);
            pst.setInt(1,c.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
