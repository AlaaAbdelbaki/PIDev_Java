/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.Entity;

import java.sql.Timestamp;

/**
 *
 * @author Anis
 */
public class Comments {
    private int id;
    private User author;
    private String body;

    public Comments(int id, User author, String body, Timestamp created_at) {
        this.id = id;
        this.author = author;
        this.body = body;
        this.created_at = created_at;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    private Timestamp created_at;

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    
}
