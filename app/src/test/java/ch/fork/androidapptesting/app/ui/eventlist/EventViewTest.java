package ch.fork.androidapptesting.app.ui.eventlist;

import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import ch.fork.androidapptesting.BuildConfig;
import ch.fork.androidapptesting.R;
import ch.fork.androidapptesting.app.DefaultConfig;
import ch.fork.androidapptesting.app.model.Event;

import static org.assertj.android.api.Assertions.assertThat;

/**
 * Created with love by fork on 17.09.15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = DefaultConfig.EMULATE_SDK, manifest = DefaultConfig.MANIFEST)
public class EventViewTest {

    @Bind(R.id.tvEventTitle)
    TextView tvTitle;
    @Bind(R.id.tvEventLocationAndDate)
    TextView tvLocationDate;
    @Bind(R.id.tvEventParticipants)
    TextView tvParticipants;

    @Test
    public void when_event_is_set_on_view_it_should_show_event_title() {
        // given
        final EventView testee = new EventView(RuntimeEnvironment.application);
        ButterKnife.bind(this, testee);
        final Event event = new Event(1, "ZEDays 2015", "Stuttgart", "a gathering of Zühlke people",
                new Date());
        // when
        testee.setEvent(event);

        // then
        assertThat(tvTitle).hasText("ZEDays 2015");
    }

    @Test
    public void when_event_is_set_on_view_with_no_participants_it_should_show_no_participants_yet() {
        // given
        final EventView testee = new EventView(RuntimeEnvironment.application);
        ButterKnife.bind(this, testee);
        final Event event = new Event(1, "ZEDays 2015", "Stuttgart", "a gathering of Zühlke people",
                new Date());
        // when
        testee.setEvent(event);

        // then
        assertThat(tvParticipants).hasText("No participants yet!");
    }
}