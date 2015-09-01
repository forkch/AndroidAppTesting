package ch.fork.androidapptesting.app.ui.eventlist;

import java.util.List;

import ch.fork.androidapptesting.app.data.EventRestClient;
import ch.fork.androidapptesting.app.model.Event;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by bmu on 01.09.2015.
 */
public class EventListPresenter {

    private final EventListView eventListView;

    public EventListPresenter(EventListView eventListView) {
        this.eventListView = eventListView;
    }

    public void loadEvents() {
        EventRestClient eventRestClient = new EventRestClient();
        eventRestClient.getEventRestService().getAllEventsAsync(new Callback<List<Event>>() {
            @Override public void success(List<Event> events, Response response) {
                eventListView.showEvents(events);
            }

            @Override public void failure(RetrofitError error) {

            }
        });
    }
}
