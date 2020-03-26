/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiagottalentEntities;

/**
 *
 * @author sarah
 */
public class Review {
      private int id;
     private String category;
    private int rating;
    private String content;
   

    public Review() {
    }

    public Review(int id, String category ,int rating, String content) {
        this.id = id;
        this.category = category;
        this.rating = rating;
        this.content = content;
       
    }

    public Review( String category,int rating,String content ) {
        this.category = category;
        this.rating = rating;
        this.content = content;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "review{" + "id=" + id + ", category=" + category + ", rating=" + rating + ", content=" + content + '}';
    }
}
