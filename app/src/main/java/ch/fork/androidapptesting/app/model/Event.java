package ch.fork.androidapptesting.app.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bmu on 01.09.2015.
 */
public class Event {
    private long id;
    private String title;
    private String location;
    private String description;

    private Date date;
    private Set<Participant> participants = new HashSet<>();

    public Event(long id, String title, String location, String description, Date date) {
        this.id = id;
        this.title = title;

        this.location = location;
        this.description = description;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public Set<Participant> getParticipants() {
        return participants;
    }

}
