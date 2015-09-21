package ch.fork.androidapptesting.app;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ch.fork.androidapptesting.R;
import ch.fork.androidapptesting.app.ui.eventlist.EventListActivity;

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
    public final ActivityTestRule<EventListActivity> activityTestRule = new ActivityTestRule<>(
            EventListActivity.class);

    @Rule
    public final MockBackendRule mockBackendRule = new MockBackendRule();

    @Test
    public void test() {

        onView(withId(R.id.toolbar)).check(matches(hasDescendant(withText("Events"))));
        onView(withId(R.id.rvEventList)).perform(
                RecyclerViewActions.actionOnItem(hasDescendant(withText("ZEDays 2015")), click()));

        onView(allOf(withId(R.id.activity_detail_title), withText("ZEDays 2015"))).check(
                matches(isDisplayed()));
    }


}
