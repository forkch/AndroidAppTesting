package ch.fork.androidapptesting.app.ui.eventlist;

import java.util.List;

import ch.fork.androidapptesting.app.data.EventService;
import ch.fork.androidapptesting.app.model.Event;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by bmu on 01.09.2015.
 */
public class EventListPresenter {

    private final EventListView eventListView;
    private final EventService eventService;

    public EventListPresenter(EventListView eventListView, EventService eventService) {
        this.eventListView = eventListView;
        this.eventService = eventService;
    }

    public void loadEvents() {

    }
}
