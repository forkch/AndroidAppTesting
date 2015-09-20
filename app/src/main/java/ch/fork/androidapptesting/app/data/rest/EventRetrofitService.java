package ch.fork.androidapptesting.app.data.rest;

import java.util.List;

import ch.fork.androidapptesting.app.model.Event;
import ch.fork.androidapptesting.app.model.Participant;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

public interface EventRetrofitService {
    @GET("/events") Observable<List<Event>> getAllEvents();

    @GET("/events/{id}") Observable<Event> getEvent(@Path("id") long eventId);

    @POST("/events/{id}/participants") Observable<Event> participate(@Path("id") long eventId,
                                                                     @Body Participant participant);

}
