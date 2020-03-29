package tunisiagottalent.Entity;


import java.sql.Timestamp;


public class Competition {
    private int id;
    private String subject;
    private Timestamp competition_date;
    private Timestamp competition_end_date;

    public Competition(String subject, Timestamp competition_date, Timestamp competition_end_date) {
        this.subject = subject;
        this.competition_date = competition_date;
        this.competition_end_date = competition_end_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Timestamp getCompetition_date() {
        return competition_date;
    }

    public void setCompetition_date(Timestamp competition_date) {
        this.competition_date = competition_date;
    }

    public Timestamp getCompetition_end_date() {
        return competition_end_date;
    }

    public void setCompetition_end_date(Timestamp competition_end_date) {
        this.competition_end_date = competition_end_date;
    }

    public Competition(int id, String subject, Timestamp competition_date, Timestamp competition_end_date) {
        this.id = id;
        this.subject = subject;
        this.competition_date = competition_date;
        this.competition_end_date = competition_end_date;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", competition_date=" + competition_date +
                ", competition_end_date=" + competition_end_date +
                '}';
    }
}
