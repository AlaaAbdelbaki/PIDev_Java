package tunisiagottalent.services;

import tunisiagottalent.Entity.competition_participant;
import tunisiagottalent.Entity.video;
import tunisiagottalent.util.DataSource;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tunisiagottalent.Entity.Competition;
import tunisiagottalent.Entity.User;

public class ParticipationServices {

    private Connection connection;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public ParticipationServices() {
        connection = DataSource.getInstance().getCnx();
    }

    public void create(competition_participant c, video v) {

        long id = 0;
        String req = "insert into video(url,title,publish_date,owner) values(?,?,?,(select id from user where username=?))";
        try {
            pst = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, v.getUrl());
            pst.setString(2, v.getTitle());
            pst.setTimestamp(3, v.getPublish_date());
            pst.setObject(4, v.getOwner().getUsername());
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String req2 = "insert into competition_participant(competition_id,user_id,participation_date,video_id) values((select id from competition where id=?),(select id from user where username=?),?,(select id from video where id=?))";
        try {
            pst = connection.prepareStatement(req2);
            pst.setObject(1, c.getCompetition_id());
            pst.setObject(2, c.getUser_id());
            pst.setTimestamp(3, c.getParticipation_date());
            pst.setObject(4, id);

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(video v) {
        String req = "delete from competition_participant where video_id=?";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, v.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String req2 = "delete from video where id=?";
        try {
            pst = connection.prepareStatement(req2);
            pst.setInt(1, v.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(video v) {
        String req = "update video set url=?,title=? where id=?";
        try {
            pst = connection.prepareStatement(req);
            pst.setString(1, v.getUrl());
            pst.setString(2, v.getTitle());
            pst.setInt(3, v.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ObservableList<video> getAll(Competition c) {
        String req = "select * from video inner join user on(video.owner=user.id) where video.id in(select video_id from competition_participant where competition_id=?)";
        ObservableList<video> list = FXCollections.observableArrayList();
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, c.getId());
            rs = pst.executeQuery();

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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public competition_participant getWinners(int vid_id) {
        String req = "select * from competition_participant "
                + "inner join competition on(competition.id=competition_participant.competition_id) "
                + "inner join user on(competition_participant.user_id=user.id) "
                + "inner join video on(competition_participant.video_id=video.id) "
                + " where competition_participant.video_id = ?";

        competition_participant cp = null;
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, vid_id);
            rs = pst.executeQuery();

            if (rs.next()) {

                cp = new competition_participant(new Competition(rs.getInt("competition.id"), rs.getString("subject"), rs.getTimestamp("competition_date"), rs.getTimestamp("competition_end_date")),
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
                                rs.getString("profile_pic")), rs.getTimestamp("participation_date"),
                        new video(rs.getInt("id"), rs.getString("url"), rs.getString("title"), rs.getTimestamp("publish_date"),
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

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cp;
    }

    public Boolean findParticipation(Competition c, User u) {
        String req = "select * from competition_participant where competition_id=? and user_id=?";
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, c.getId());
            pst.setInt(2, u.getId());

            rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ObservableList<video> getAllOrdered(Competition c) {
        String req = "select * from video inner join user on(video.owner=user.id) where video.id in(select video_id from competition_participant where competition_id=?) order by video.publish_date";
        ObservableList<video> list = FXCollections.observableArrayList();
        try {
            pst = connection.prepareStatement(req);
            pst.setInt(1, c.getId());
            rs = pst.executeQuery();

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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
