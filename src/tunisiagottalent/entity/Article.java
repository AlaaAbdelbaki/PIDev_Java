/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalent.entity;

/**
 *
 * @author ghassen
 */

public class Article {
    
  private int id ;
  private String title;
  private String img ;
  private String content ;

  

    public Article() {
    }

    public Article(int id) {
        this.id = id;
    }

    public Article(int id, String title, String content, String img) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.img = img;
    }

    public Article(String title, String img, String content) {
        this.title = title;
        this.img = img;
        this.content = content;
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
    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", title=" + title + ", content=" + content + ", img=" + img + '}';
    }

    public String getTitleArticle() {
        return title;
    }

    public String getContentArticle() {
        return content;
    }

    public String getImgArticle() {
        return img;
    }
    
    
}
