package ch.fork.androidapptesting.app.ui.eventdetails;

import ch.fork.androidapptesting.app.data.EventService;
import ch.fork.androidapptesting.app.model.Event;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by bmu on 01.09.2015.
 */
public class EventDetailsPresenter {


    private final EventDetailsView eventDetailsView;
    private final EventService eventService;
    private final Scheduler observeScheduler;

    public EventDetailsPresenter(EventDetailsView eventDetailsView, EventService eventService, Scheduler observeScheduler) {

        this.eventDetailsView = eventDetailsView;
        this.eventService = eventService;
        this.observeScheduler = observeScheduler;
    }

    public void showEventDetails(long eventId) {
        if (eventId == -1) {
            eventDetailsView.eventNotFound();
        }
        eventService.getEvent(eventId)
                .observeOn(observeScheduler)
                .subscribe(new Observer<Event>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        eventDetailsView.eventNotFound();
                    }

                    @Override
                    public void onNext(Event event) {
                        eventDetailsView.setEvent(event);

                    }
                });
    }

    public void participate(long eventId) {
        if (eventId == -1) {
            return;
        }


    }
}
