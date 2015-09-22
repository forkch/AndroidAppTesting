package ch.fork.androidapptesting.app.ui.eventlist;

import java.util.List;

import ch.fork.androidapptesting.app.model.Event;

/**
 * Created by bmu on 01.09.15.
 */
public interface EventListView {
    void openDetailsForEvent(Event event);

    void showEvents(List<Event> allEvents);

    void loadingEventsFailed();
}
