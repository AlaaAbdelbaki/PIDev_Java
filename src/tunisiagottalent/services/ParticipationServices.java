package tunisiagottalent.services;

import tunisiagottalent.Entity.competition_participant;
import tunisiagottalent.Entity.video;
import tunisiagottalent.util.DataSource;

import java.sql.*;

public class ParticipationServices {
    private Connection connection;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public ParticipationServices() {
        connection= DataSource.getInstance().getCnx();}

    public void create(competition_participant c, video v){


        String req="insert into video(url,title,publish_date,owner) values(?,?,?,?)";
        try {
            pst=connection.prepareStatement(req);
            pst.setString(1,v.getUrl());
            pst.setString(2,v.getTitle());
            pst.setTimestamp(3,v.getPublish_date());
            pst.setObject(4,v.getOwner());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String req2="insert into competition_participant(competition_id,user_id,participation_date,video_id) values(?,?,?,?)";
        try {
            pst=connection.prepareStatement(req2);
            pst.setObject(1,c.getCompetition_id());
            pst.setObject(2,c.getUser_id());
            pst.setTimestamp(3,c.getParticipation_date());
            pst.setObject(4,c.getVideo_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
