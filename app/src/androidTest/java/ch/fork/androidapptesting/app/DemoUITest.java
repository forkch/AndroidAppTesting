package ch.fork.androidapptesting.app;

import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;

import java.util.Date;
import java.util.List;

import ch.fork.androidapptesting.R;
import ch.fork.androidapptesting.app.model.Event;
import ch.fork.androidapptesting.app.ui.eventlist.EventListActivity;
import rx.Observable;
import rx.schedulers.Schedulers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created with love by fork on 19.09.15.
 */
@RunWith(AndroidJUnit4.class)
public class DemoUITest {

    @Rule
    public final MockBackendRule mockBackendRule = new MockBackendRule();

    @Rule
    public final ActivityTestRule<EventListActivity> activityTestRule = new ActivityTestRule<>(
            EventListActivity.class, true, false);


    public static final Event THE_EVENT = new Event(1, "ZEDays 2015", "Stuttgart", "description",
            new Date());

    @Before
    public void setupMocksAndStartActivity() {
        final List<Event> events = Lists.newArrayList(THE_EVENT);
        Mockito.when(mockBackendRule.getMockEventService()
                                    .getAllEvents())
               .thenReturn(
                       Observable.just(events)
                                 .subscribeOn(Schedulers.immediate())
               );
        Mockito.when(mockBackendRule.getMockEventService()
                                    .getEvent(Matchers.any(Long.class)))
               .thenReturn(
                       Observable.just(THE_EVENT)
                                 .subscribeOn(Schedulers.immediate())
               );

        activityTestRule.launchActivity(new Intent(Intent.ACTION_MAIN));
    }

    @Test
    public void happy_path() {

        onView(withId(R.id.toolbar))
                .check(matches(hasDescendant(withText("Events"))));
        onView(withId(R.id.rvEventList))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText("ZEDays 2015")), click()));

        onView(allOf(withId(R.id.activity_detail_title), withText("ZEDays 2015")))
                .check(matches(isDisplayed()));
    }


}
