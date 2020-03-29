package tunisiagottalent.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tunisiagottalent.Entity.Competition;
import tunisiagottalent.util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompetitionServices {
    private Connection connection;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public CompetitionServices() {
        connection= DataSource.getInstance().getCnx();}
    public void create(Competition c){


            String req="insert into competition(subject,competition_date,competition_end_date) values(?,?,?)";
        try {
            pst=connection.prepareStatement(req);
            pst.setString(1,c.getSubject());
            pst.setTimestamp(2,c.getCompetition_date());
            pst.setTimestamp(3,c.getCompetition_end_date());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public void update( Competition c){
        String req="update competition set subject=?,competition_date=?,competition_end_date=? where id=?";
        try {
            pst=connection.prepareStatement(req);
            pst.setString(1,c.getSubject());
            pst.setTimestamp(2,c.getCompetition_date());
            pst.setTimestamp(3,c.getCompetition_end_date());
            pst.setInt(4,c.getId());
            System.out.println(pst);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void delete(Competition c){
        String req="delete from competition where id=?";
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1,c.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public Competition get(int id){
        Competition c=null;
        String req="select * from competition where id=?";
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1,id);
            rs=pst.executeQuery();
            if (rs.next()){
            c= new Competition(rs.getInt("id"),rs.getString("subject"),rs.getTimestamp("competition_date"),rs.getTimestamp("competition_end_date"));}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
    public ObservableList<Competition> getAll(){
        String req="select * from competition";
        ObservableList<Competition> list=FXCollections.observableArrayList();
        try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next()){
                list.add(new Competition(rs.getInt("id"),rs.getString("subject"),rs.getTimestamp("competition_date"),rs.getTimestamp("competition_end_date")));
            }
    ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
