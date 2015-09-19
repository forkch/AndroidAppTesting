package ch.fork.androidapptesting.app.model;

import java.io.Serializable;

public class Participant implements Serializable {

    private final String name;
    private final String email;


    public Participant(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Participant that = (Participant) o;

        return email.equals(that.email);
    }

    @Override
    public int hashCode() {
        if(email != null){
            return email.hashCode();
        }
        return super.hashCode();
    }
}