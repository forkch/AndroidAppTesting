package ch.fork.androidapptesting.app.data.rest;

import java.util.List;

import ch.fork.androidapptesting.app.data.EventService;
import ch.fork.androidapptesting.app.model.Event;
import ch.fork.androidapptesting.app.model.Participant;
import rx.Observable;

/**
 * Created with love by fork on 17.09.15.
 */
public class EventServiceRestImpl implements EventService {

    private final RetrofitClient retrofitClient;
    private final EventRetrofitService eventRetrofitService;

    public EventServiceRestImpl() {
        retrofitClient = new RetrofitClient();
        eventRetrofitService = retrofitClient.getEventRetrofitService();
    }

    @Override
    public Observable<List<Event>> getAllEvents() {
        return eventRetrofitService.getAllEvents();
    }

    @Override
    public Observable<Event> getEvent(long eventId) {
        return eventRetrofitService.getEvent(eventId);
    }

    @Override
    public Observable<Event> participate(long eventId, Participant participant) {
        return eventRetrofitService.participate(eventId, participant);
    }
}
