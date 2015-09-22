package ch.fork.androidapptesting.app.ui.eventlist;

import java.util.List;

import ch.fork.androidapptesting.app.data.EventService;
import ch.fork.androidapptesting.app.model.Event;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by bmu on 01.09.2015.
 */
public class EventListPresenter {

    private final EventListView eventListView;
    private final EventService eventService;
    private Scheduler scheduler;

    public EventListPresenter(EventListView eventListView, EventService eventService,
                              Scheduler scheduler) {
        this.eventListView = eventListView;
        this.eventService = eventService;
        this.scheduler = scheduler;
    }

    public void loadEvents() {

        eventService.getAllEvents()
                    .observeOn(scheduler)
                    .subscribe(new Subscriber<List<Event>>() {
                                   @Override
                                   public void onCompleted() {
                                   }

                                   @Override
                                   public void onError(Throwable e) {
                                       eventListView.loadingEventsFailed();
                                   }

                                   @Override
                                   public void onNext(List<Event> events) {
                                       eventListView.showEvents(events);
                                   }
                               }
                    );
    }
}
