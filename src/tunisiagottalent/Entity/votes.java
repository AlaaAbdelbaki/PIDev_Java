package tunisiagottalent.Entity;

public class votes {
    private User user_id;
    private video video_id;

    public votes(User user_id, video video_id) {
        this.user_id = user_id;
        this.video_id = video_id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public video getVideo_id() {
        return video_id;
    }

    public void setVideo_id(video video_id) {
        this.video_id = video_id;
    }
}
