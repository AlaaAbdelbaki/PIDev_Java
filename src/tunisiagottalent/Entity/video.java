package tunisiagottalent.Entity;


import java.sql.Timestamp;

public class video {
    private int id;
    private String url;
    private String title;
    private Timestamp publish_date;
    private User owner;

    public video(int id, String url, String title, Timestamp publish_date, User owner) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.publish_date = publish_date;
        this.owner = owner;
    }

    public video(String url, String title, Timestamp publish_date, User owner) {
        this.url = url;
        this.title = title;
        this.publish_date = publish_date;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(Timestamp publish_date) {
        this.publish_date = publish_date;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "video{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", publish_date=" + publish_date +
                ", owner=" + owner +
                '}';
    }
}
