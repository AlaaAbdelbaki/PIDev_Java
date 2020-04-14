/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 package tunisiagottalent.Entity;

import java.util.Date;
import javafx.scene.control.Button;

/**
 *
 * @author hela
 */
public class Event {

    public int id;
    public String title;
    public Date start_date;
    public Date end_date;
    public String img;
    public String location;
    public int nb_places;
    public String description;
    public String type;

    public Event() {
    }

    public Event(String title, String img, String location, int nb_places, String description, String type) {
        this.title = title;
        this.img = img;
        this.location = location;
        this.nb_places = nb_places;
        this.description = description;
        this.type = type;
    }

    public Event(String title, Date start_date, Date end_date, String img, String location, int nb_places, String description, String type) {
        this.title = title;
        this.start_date = start_date;
        this.end_date = end_date;
        this.img = img;
        this.location = location;
        this.nb_places = nb_places;
        this.description = description;
        this.type = type;
    }

    public Event(int id, String title, Date start_date, Date end_date, String img, String location, int nb_places, String description, String type) {
        this.id = id;
        this.title = title;
        this.start_date = start_date;
        this.end_date = end_date;
        this.img = img;
        this.location = location;
        this.nb_places = nb_places;
        this.description = description;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getStart_date() {
        return start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public String getImg() {
        return img;
    }

    public String getLocation() {
        return location;
    }

    public int getNb_places() {
        return nb_places;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setNb_places(int nb_places) {
        this.nb_places = nb_places;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", title=" + title + ", start_date=" + start_date + ", end_date=" + end_date + ", img=" + img + ", location=" + location + ", nb_places=" + nb_places + ", description=" + description + ", type=" + type + '}';
    }

    public String getInfo() {
        return this.title + ", " + this.type + ", " + this.description;
    }

}
