package ch.fork.androidapptesting.app.ui.eventdetails;

import ch.fork.androidapptesting.app.model.Event;

/**
 * Created with love by fork on 19.09.15.
 */
public interface EventDetailView {
    void eventNotFound();

    void setEvent(Event event);

    void onParticipateSuccessful();
}
