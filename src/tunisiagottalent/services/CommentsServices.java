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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tunisiagottalent.Entity.Article;
import tunisiagottalent.Entity.Comments;
import tunisiagottalent.Entity.Competition;
import tunisiagottalent.Entity.User;
import tunisiagottalent.util.DataSource;

/**
 *
 * @author Anis
 */
public class CommentsServices {
     private Connection connection;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public CommentsServices() {
        connection= DataSource.getInstance().getCnx();}
    
    
    public ObservableList<Comments> getAll(Article a){
        String req="select * from comment inner join user on(author_id=user.id)where thread_id=?";
        ObservableList<Comments> list=FXCollections.observableArrayList();
        try {
            pst=connection.prepareStatement(req);
            pst.setInt(1,a.getId());
            rs=pst.executeQuery();
            while(rs.next()){
                list.add(new Comments(rs.getInt("id"),new User(rs.getInt("user.id"),
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
                                rs.getString("profile_pic")) ,
                        rs.getString("body"),
                        rs.getTimestamp("created_at")));
            }
    ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
