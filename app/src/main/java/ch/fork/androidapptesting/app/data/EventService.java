package ch.fork.androidapptesting.app.data;

import java.util.List;

import ch.fork.androidapptesting.app.model.Event;
import ch.fork.androidapptesting.app.model.Participant;
import rx.Observable;

/**
 * Created with love by fork on 17.09.15.
 */
public interface EventService {

    Observable<List<Event>> getAllEvents();

    Observable<Event> getEvent(long eventId);

    Observable<Event> participate(long eventId, Participant participant);
}
