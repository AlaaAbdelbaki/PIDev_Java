/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.entity;

import java.sql.Date;

/**
 *
 * @author 
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

    public Updates(String title, String img, String category, Date publish_date, String content) {
        this.title = title;
        this.img = img;
        this.content = content;
        this.category = category;
        this.publish_date = publish_date;
    }
    
    

    public Updates (int id, String title, String content, String img, Date publish_date,String category ) {
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getPublish_date() {
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



