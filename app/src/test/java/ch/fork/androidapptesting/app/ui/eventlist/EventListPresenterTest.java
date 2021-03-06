package ch.fork.androidapptesting.app.ui.eventlist;

import org.assertj.core.util.Lists;
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

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created with love by fork on 17.09.15.
 */
public class EventListPresenterTest {

    private EventListPresenter testee;

    @Mock
    private EventService eventServiceMock;

    @Mock
    private EventListView eventListViewMock;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void when_getting_events_it_shoud_display_them_on_the_view() {
        // given
        testee = new EventListPresenter(eventListViewMock, eventServiceMock,
                Schedulers.immediate());
        final ArrayList<Event> expectedEvents = Lists.newArrayList(
                new Event(1, "ZEDays 2015", "Stuttgart", "a gathering of Zühlke ", new Date()));
        when(eventServiceMock.getAllEvents()).thenReturn(
                Observable.<List<Event>>just(expectedEvents));

        // when
        testee.loadEvents();

        // then
        verify(eventListViewMock).showEvents(eq(expectedEvents));
    }

}