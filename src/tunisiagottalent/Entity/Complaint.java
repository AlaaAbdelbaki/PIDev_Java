/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.Entity;

import javafx.scene.control.ChoiceBox;

/**
 *
 * @author sarah
 */
public class Complaint {
    private int id;
    private String subject;
    private String content;
    private User user_id;

 public Complaint() {
    }

    public Complaint(int id, String subject, String content, User user_id) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.user_id = user_id;
    }

    

    public Complaint(String subject, String content,User u) {
        this.subject = subject;
        this.content = content;
        this.user_id = u;
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "complaint{" + "id=" + id + ", subject=" + subject + ", content=" + content + '}';
    }
    
    
}
