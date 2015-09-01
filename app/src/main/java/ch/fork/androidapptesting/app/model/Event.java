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

    public Event() {

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
