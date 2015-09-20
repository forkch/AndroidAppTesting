package ch.fork.androidapptesting.app.ui.eventlist;

import java.util.List;

import ch.fork.androidapptesting.app.data.EventService;
import ch.fork.androidapptesting.app.model.Event;
import rx.Scheduler;
import rx.Subscriber;

/**
 * Created by bmu on 01.09.2015.
 */
public class EventListPresenter {

    private final EventListView eventListView;
    private final EventService eventService;
    private final Scheduler observeScheduler;

    public EventListPresenter(EventListView eventListView, EventService eventService,
                              Scheduler observeScheduler) {
        this.eventListView = eventListView;
        this.eventService = eventService;
        this.observeScheduler = observeScheduler;
    }

    public void loadEvents() {
        eventService.getAllEvents()
                    .observeOn(observeScheduler)
                    .subscribe(new Subscriber<List<Event>>() {
                                   @Override
                                   public void onCompleted() {
                                   }

                                   @Override
                                   public void onError(Throwable e) {
                                       eventListView.loadingEventsFaild();
                                   }

                                   @Override
                                   public void onNext(List<Event> events) {
                                       eventListView.showEvents(events);
                                   }
                               }
                    );
    }
}
