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
import java.util.HashMap;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import tunisiagottalent.Entity.Competition;
import tunisiagottalent.Entity.User;
import tunisiagottalent.Entity.video;

import tunisiagottalent.util.DataSource;

/**
 *
 * @author Anis
 */
public class VoteServices {

    private Connection connection;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public VoteServices() {
        connection = DataSource.getInstance().getCnx();
    }

    public void Add(User u, video v) {

        String req = "insert into votes values((select id from video where id=?),(select id from user where id=?))";
        try {
            
            pst = connection.prepareStatement(req);

            pst.setInt(1, v.getId());
            pst.setInt(2, u.getId());
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(video v, User u) {
        String req = "delete from votes where video_id=? and user_id=?";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, v.getId());
            pst.setInt(2, u.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Boolean find(video v, User u) {
        String req = "select * from votes where video_id=? and user_id=?";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, v.getId());
            pst.setInt(2, u.getId());

            rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*   public ObservableList<User> getAll(video v){
      String req="select * from votes inner join user on(votes.user_id=user.id) where votes.video_id=?";
      ObservableList<User> list=FXCollections.observableArrayList();
      try {
             pst=connection.prepareStatement(req);
            pst.setInt(1,v.getId());
            rs=pst.executeQuery();
            while(rs.next()){   
            list.add(new User(rs.getInt("user.id"),rs.getString("username"),rs.getString("email"), rs.getString("adresse"),rs.getString("sexe"), rs.getString("name"), rs.getString("first_name"),rs.getString("telephone_number"),rs.getString("roles")));
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
     }*/
    public List<video> ranks(Competition c) {
        String req = "SELECT  *   FROM votes v "
                + "inner join video  on(video.id=v.video_id) "
                + "inner join user on(video.owner=user.id)"
                + " WHERE v.video_id IN ( SELECT video_id FROM competition_participant c WHERE c.competition_id = ? )"
                + " GROUP by v.video_id "
                + "ORDER by count(v.video_id)"
                + " DESC ";
        List<video> l = new ArrayList<>();
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, c.getId());
            rs = pst.executeQuery();
            while (rs.next()) {
                l.add(new video(rs.getInt("video.id"), rs.getString("url"), rs.getString("title"), rs.getTimestamp("publish_date"),
                        new User(rs.getInt("user.id"),
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

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return l;
    }

    public int getVotes(video v) {
        int i = 0;
        String req = "select Count(video_id) from votes where video_id=?";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, v.getId());
            rs = pst.executeQuery();
            if (rs.next()) {
                i = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    public List<video> OrderedByVotes() {
        String req = "SELECT  * FROM votes v "
                + "inner join video  on(video.id=v.video_id) "
                + "inner join user on(video.owner=user.id)"
                + " GROUP by v.video_id "
                + "ORDER by count(v.video_id)"
                + " DESC ";
        List<video> l = new ArrayList<>();
        try {
           ste = connection.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                l.add(new video(rs.getInt("video.id"), rs.getString("url"), rs.getString("title"), rs.getTimestamp("publish_date"),
                        new User(rs.getInt("user.id"),
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

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return l;
    }
    public List<video> OrderedByNewest() {
        String req = "SELECT  * FROM video "
                 
                + "inner join user on(video.owner=user.id)"
                
                + "order by video.publish_date"
                + " DESC ";
        List<video> l = new ArrayList<>();
        try {
           ste = connection.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                l.add(new video(rs.getInt("id"), rs.getString("url"), rs.getString("title"), rs.getTimestamp("publish_date"),
                        new User(rs.getInt("user.id"),
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

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return l;
    }
    
}
