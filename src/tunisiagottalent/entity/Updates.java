/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.entity;

import java.sql.Date;

/**
 *
 * @author ghassen
 */


public class Updates {
    
  private int id ;
  private String title;
  private String img ;
  private String content ;
  private String category ;
  private Date publish_date ; 

  

    public Updates() {
    }

    public Updates(int id, String title, String content, String img, String, String category, Date publish_date ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.img = img;
        this.category= category;
        this.publish_date= publish_date;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
     public String getCategory() {
        return category;
    }

    public void setCategory(String categoryy) {
        this.category = category;
    }
    
     public Date getDate() {
        return publish_date;
    }

    public void setPublish_date(Date publish_date) {
        this.publish_date = publish_date;
    }

    @Override
    public String toString() {
        return "Updates{" + "id=" + id + ", title=" + title + ", img=" + img + ", content=" + content + ", category=" + category + ", publish_date=" + publish_date + '}';
    }
    
    
    
}
