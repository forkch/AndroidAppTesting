package ch.fork.androidapptesting.app.ui.eventlist;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.common.base.Joiner;

import butterknife.Bind;
import butterknife.ButterKnife;
import ch.fork.androidapptesting.R;
import ch.fork.androidapptesting.app.model.Event;


/**
 * Created with love by fork on 04.09.15.
 */
public class EventView extends FrameLayout {

    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.location_date)
    TextView tvLocationDate;
    @Bind(R.id.tvParticipants)
    TextView tvParticipants;

    public EventView(Context context) {
        this(context, null);
    }

    public EventView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        final View inflate = LayoutInflater.from(getContext()).inflate(R.layout.event_view, this);
        ButterKnife.bind(this, inflate);
    }

    public void setEvent(Event event) {
        tvTitle.setText(event.getTitle());
        tvLocationDate.setText(String.format("%s, %s", event.getLocation(), event.getDate().toString()));
        tvParticipants.setText(createParticipantsList(event));
    }

    private String createParticipantsList(Event event) {
        return "Participants: " + Joiner.on(", ").join(event.getParticipants());
    }
}
