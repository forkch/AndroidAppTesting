package ch.fork.androidapptesting.app;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.core.deps.guava.collect.Lists;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.mockito.Mockito;

import java.util.Date;
import java.util.List;

import ch.fork.androidapptesting.app.data.EventService;
import ch.fork.androidapptesting.app.model.Event;
import ch.fork.androidapptesting.app.model.Participant;
import rx.Observable;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.when;

/**
 * Created with love by fork on 20.09.15.
 */
public class MockBackendRule implements TestRule {
    public static final Event THE_EVENT = new Event(1, "ZEDays 2015", "Stuttgart", "description",
            new Date());
    private EventService mockEventService = new EventService() {


        @Override public Observable<List<Event>> getAllEvents() {
            final List<Event> events = com.google.common.collect.Lists.newArrayList(
                    THE_EVENT);
            return Observable.just(events)
                      .subscribeOn(Schedulers.immediate());
        }

        @Override public Observable<Event> getEvent(long eventId) {
            return Observable.just(THE_EVENT)
                             .subscribeOn(Schedulers.immediate());
        }

        @Override public Observable<Event> participate(long eventId, Participant participant) {

            THE_EVENT.getParticipants().add(participant);
            return Observable.just(THE_EVENT)
                             .subscribeOn(Schedulers.immediate());
        }
    };

    public EventService getMockEventService() {
        return mockEventService;
    }

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    initMockBackend();
                    base.evaluate();
                } finally {
                }
            }

        };
    }

    private void initMockBackend() {

        //mockEventService = Mockito.mock(EventService.class);
        AndroidAppTestingApp application = AndroidAppTestingApp
                .get(InstrumentationRegistry.getTargetContext());
        application.setEventService(mockEventService);
    }
}
