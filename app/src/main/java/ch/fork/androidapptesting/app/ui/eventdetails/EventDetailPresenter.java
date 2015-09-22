package ch.fork.androidapptesting.app.ui.eventdetails;

import ch.fork.androidapptesting.app.data.EventService;
import ch.fork.androidapptesting.app.model.Event;
import ch.fork.androidapptesting.app.model.Participant;
import rx.Observer;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created with love by bmu on 01.09.2015.
 */
public class EventDetailPresenter {


    private final EventDetailView eventDetailView;
    private final EventService eventService;
    private final Scheduler observeScheduler;

    public EventDetailPresenter(EventDetailView eventDetailView, EventService eventService,
                                Scheduler observeScheduler) {

        this.eventDetailView = eventDetailView;
        this.eventService = eventService;
        this.observeScheduler = observeScheduler;
    }

    public void showEventDetails(long eventId) {
        if (eventId == -1) {
            eventDetailView.eventNotFound();
        }
        eventService.getEvent(eventId)
                    .observeOn(observeScheduler)
                    .subscribe(new Observer<Event>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            eventDetailView.eventNotFound();
                        }

                        @Override
                        public void onNext(Event event) {
                            eventDetailView.setEvent(event);

                        }
                    });
    }

    public void participate(long eventId, String name) {
        if (eventId == -1) {
            return;
        }

        eventService.participate(eventId, new Participant(name, name + "@zedays2015.com"))
                    .subscribeOn(Schedulers.io())
                    .observeOn(observeScheduler)
                    .subscribe(event -> eventDetailView.onParticipateSuccessful());


    }
}
