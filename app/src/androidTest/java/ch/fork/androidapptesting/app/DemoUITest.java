package ch.fork.androidapptesting.app;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ch.fork.androidapptesting.R;
import ch.fork.androidapptesting.app.ui.eventlist.EventListActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.mockito.Matchers.contains;

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

    @Before
    public void setupMocksAndStartActivity() {
        activityTestRule.launchActivity(new Intent(Intent.ACTION_MAIN));
    }

    @Test
    public void happy_path() {

        onView(withId(R.id.toolbar))
                .check(matches(hasDescendant(withText("Events"))));
        onView(withId(R.id.rvEventList))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText("ZEDays 2015")), click()));

        onView(allOf(withId(R.id.tvEventTitle), withText("ZEDays 2015")))
                .check(matches(isDisplayed()));

        onView(withId(R.id.btnEventParticipate)).perform(click());
        onView(allOf(withId(R.id.title), withText(R.string.eventdetail_enter_name_title)))
                .check(matches(isDisplayed()));

        onView(withId(android.R.id.input)).perform(typeText("Benjamin"));
        onView(withId(R.id.buttonDefaultPositive)).perform(click());
        Espresso.pressBack();

        onView(withTextContains("Benjamin@zedays2015.com")).check(matches(isDisplayed()));

    }

    private Matcher<View> withTextContains(String containedText) {
        return new BoundedMatcher<View, TextView>(TextView.class) {

            @Override public void describeTo(Description description) {
                description.appendText("with text containing: " + containedText);
            }

            @Override protected boolean matchesSafely(TextView item) {
                return item.getText().toString().contains(containedText);
            }
        };
    }


}
