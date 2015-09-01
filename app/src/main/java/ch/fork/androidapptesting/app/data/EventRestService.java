package ch.fork.androidapptesting.app.data;

import java.util.List;

import ch.fork.androidapptesting.app.model.Event;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface EventRestService {

    @GET("/events")
    void getAllEventsAsync(Callback<List<Event>> callback);

    @GET("/events")
    List<Event> getAllEvents();

    @GET("/events/{id}")
    void getEventAsync(@Path("id") long eventId, Callback<Event> callback);

    @GET("/events/{id}")
    Event getEvent(@Path("id") long eventId);
}
