package ch.fork.androidapptesting.app.ui.eventlist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Date;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import ch.fork.androidapptesting.R;
import ch.fork.androidapptesting.app.model.Participant;


/**
 * Created with love by fork on 04.09.15.
 */
public class EventView extends FrameLayout {

    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.location_date)
    TextView tvLocationDate;
    @Bind(R.id.tvDescription)
    TextView tvDescription;
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

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setLocationAndDate(String location, Date date) {
        tvLocationDate.setText(String.format("%s, %s", location, date.toString()));
    }

    public void setDescription(String description) {
        tvDescription.setText(description);
    }

    public void setParticipants(Set<Participant> participants) {
        tvParticipants.setText(participants.toString());
    }
}
