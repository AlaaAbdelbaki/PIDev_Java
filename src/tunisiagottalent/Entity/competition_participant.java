package tunisiagottalent.Entity;


import java.sql.Timestamp;

public class competition_participant {
    private int id;
    private Competition competition_id;
    private User user_id;
    private Timestamp participation_date;
    private video video_id;

    public competition_participant(int id, Competition competition_id, User user_id, Timestamp participation_date, video video_id) {
        this.id = id;
        this.competition_id = competition_id;
        this.user_id = user_id;
        this.participation_date = participation_date;
        this.video_id = video_id;
    }

    public competition_participant(Competition competition_id, User user_id, Timestamp participation_date, video video_id) {
        this.competition_id = competition_id;
        this.user_id = user_id;
        this.participation_date = participation_date;
        this.video_id = video_id;
    }

    public competition_participant() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCompetition_id() {
        return competition_id.getId();
    }

    public void setCompetition_id(Competition competition_id) {
        this.competition_id = competition_id;
    }

    public String getUser_id() {
        return user_id.getUsername();
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Timestamp getParticipation_date() {
        return participation_date;
    }

    public void setParticipation_date(Timestamp participation_date) {
        this.participation_date = participation_date;
    }

    public video getVideo_id() {
        return video_id;
    }

    public void setVideo_id(video video_id) {
        this.video_id = video_id;
    }

    @Override
    public String toString() {
        return "competition_participant{" +
                "id=" + id +
                ", competition_id=" + competition_id +
                ", user_id=" + user_id +
                ", participation_date=" + participation_date +
                ", video_id=" + video_id +
                '}';
    }
}
