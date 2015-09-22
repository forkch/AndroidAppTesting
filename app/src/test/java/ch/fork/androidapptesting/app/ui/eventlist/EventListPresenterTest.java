package ch.fork.androidapptesting.app.ui.eventlist;

import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ch.fork.androidapptesting.app.data.EventService;
import ch.fork.androidapptesting.app.model.Event;
import rx.Observable;
import rx.schedulers.Schedulers;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created with love by fork on 22.09.15.
 */
public class EventListPresenterTest {

    @Mock
    EventService eventService;

    @Mock
    EventListView eventListView;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void test_load_events() {
        // given
        final Event event = new Event(1, "ZEDays 2015", "ICS Stuttgart", "some description",
                new Date());
        List<Event> events = new ArrayList<>();
        events.add(event);

        when(eventService.getAllEvents())
                .thenReturn(Observable.just(events));
        EventListPresenter testee = new EventListPresenter(eventListView, eventService,
                Schedulers.immediate());

        // when
        testee.loadEvents();

        // then
        verify(eventListView).showEvents(Lists.newArrayList(event));
    }
}