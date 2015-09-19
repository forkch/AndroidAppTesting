package ch.fork.androidapptesting.app.ui.eventlist;

import android.content.Intent;

import org.assertj.android.api.Assertions;
import org.assertj.core.api.StrictAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import ch.fork.androidapptesting.BuildConfig;
import ch.fork.androidapptesting.app.DefaultConfig;
import ch.fork.androidapptesting.app.model.Event;

import static org.assertj.android.api.Assertions.assertThat;


/**
 * Created with love by fork on 17.09.15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = DefaultConfig.EMULATE_SDK, manifest = DefaultConfig.MANIFEST)
public class EventListActivityTest {

    @Test
    public void when_event_is_clicked_it_should_start_intent(){
        final EventListActivity eventListActivity = Robolectric.buildActivity(EventListActivity.class).create().get();

        final Event event = new Event(1, "ZEDays 2015");
        eventListActivity.openDetailsForEvent(event);
        final ShadowActivity shadowActivity = Shadows.shadowOf(eventListActivity);
        final Intent firedIntent = shadowActivity.getNextStartedActivity();
        assertThat(firedIntent).isNotNull().hasExtra("EVENT_ID").hasComponent(RuntimeEnvironment.application, EventListActivity.class);

    }

}