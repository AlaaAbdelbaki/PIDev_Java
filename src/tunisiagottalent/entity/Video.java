/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.entity;

import java.sql.Date;

/**
 *
 * @author alaa
 */
public class Video {
    
    private String url;
    private String title;
    private Date date;
    private int userId;

    public Video(String url, String title, Date date, int userId) {
        this.url = url;
        this.title = title;
        this.date = date;
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Video{" + "url=" + url + ", title=" + title + ", date=" + date + ", userId=" + userId + '}';
    }
    
    
    
    
}
