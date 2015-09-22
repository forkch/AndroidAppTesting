package ch.fork.androidapptesting.app.ui.eventlist;

import android.content.Intent;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import java.util.Date;

import ch.fork.androidapptesting.BuildConfig;
import ch.fork.androidapptesting.R;
import ch.fork.androidapptesting.app.DefaultConfig;
import ch.fork.androidapptesting.app.model.Event;
import ch.fork.androidapptesting.app.ui.eventdetails.EventDetailActivity;

import static org.assertj.android.api.Assertions.assertThat;


/**
 * Created with love by fork on 17.09.15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = DefaultConfig.EMULATE_SDK, manifest = DefaultConfig.MANIFEST)
public class EventDetailsActivityTest {

    @Test
    public void when_event_location_is_clicked_it_should_start_intent_to_google_maps() {
        // TODO
        // given

        // when

        // then

    }

}