/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javafx.scene.control.ChoiceBox;

/**
 *
 * @author sarah
 */
public class Complaint {
    private int id;
    private String subject;
    private String content;
    private int user_id;

 public Complaint() {
    }

    public Complaint(int id, String subject, String content, int user_id) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.user_id = user_id;
    }

    public Complaint(int id, String subject, String content) {
        this.id = id;
        this.subject = subject;
        this.content = content;
    }

    public Complaint(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    public Complaint(ChoiceBox Subject, String Contentc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Complaint(int aInt, String string, int aInt0, String string0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
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
