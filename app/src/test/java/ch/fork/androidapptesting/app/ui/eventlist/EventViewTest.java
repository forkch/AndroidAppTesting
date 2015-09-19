package ch.fork.androidapptesting.app.ui.eventlist;

import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import butterknife.Bind;
import butterknife.ButterKnife;
import ch.fork.androidapptesting.BuildConfig;
import ch.fork.androidapptesting.R;
import ch.fork.androidapptesting.app.DefaultConfig;

import static org.assertj.android.api.Assertions.assertThat;

/**
 * Created with love by fork on 17.09.15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = DefaultConfig.EMULATE_SDK, manifest = DefaultConfig.MANIFEST)
public class EventViewTest  {

    @Bind(R.id.tvTitle)
    TextView tvTitle;

    @Test
    public void when_title_is_set_it_should_be_visible() {
        // given
        final EventView testee = new EventView(RuntimeEnvironment.application);
        ButterKnife.bind(this, testee);

        // when
        testee.setTitle("ZEDays 2015");

        // then
        assertThat(tvTitle).hasText("ZEDays 2015");
    }
}