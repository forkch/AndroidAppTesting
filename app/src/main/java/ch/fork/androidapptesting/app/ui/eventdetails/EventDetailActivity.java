package ch.fork.androidapptesting.app.ui.eventdetails;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ch.fork.androidapptesting.R;
import ch.fork.androidapptesting.app.AndroidAppTestingApp;
import ch.fork.androidapptesting.app.model.Event;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by bmu on 01.09.2015.
 */
public class EventDetailActivity extends AppCompatActivity implements EventDetailsView {

    public static final String EXTRA_EVENT_ID = "EVENT_ID";
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.activity_detail_title)
    TextView tvTitle;
    @Bind(R.id.activity_detail_date)
    TextView tvDate;
    @Bind(R.id.activity_detail_location)
    TextView tvLocation;
    @Bind(R.id.activity_detail_description)
    TextView tvDescription;
    private EventDetailsPresenter presenter;

    @OnClick(R.id.activity_detail_participate)
    public void onParticipateClick() {
        presenter.participate(getEventIdFromIntent());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        presenter = new EventDetailsPresenter(this, AndroidAppTestingApp.get(this)
                                                                        .getEventService(),
                AndroidSchedulers.mainThread());

    }

    @Override
    protected void onResume() {
        super.onResume();
        final long eventId = getEventIdFromIntent();
        presenter.showEventDetails(eventId);
    }

    private long getEventIdFromIntent() {
        final Intent intent = getIntent();
        return intent.getLongExtra(EXTRA_EVENT_ID, -1);
    }


    @Override
    public void eventNotFound() {
        Toast.makeText(this, "Event not found", Toast.LENGTH_SHORT)
             .show();
        finish();
    }

    @Override
    public void setEvent(final Event event) {
        tvTitle.setText(event.getTitle());
        tvDate.setText(event.getDate()
                            .toString());
        tvLocation.setText(event.getLocation());
        tvLocation.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent googleMapsIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("geo:0,0?q=" + event.getLocation()));
                googleMapsIntent.setPackage("com.google.android.apps.maps");
                startActivity(googleMapsIntent);

            }
        });
        tvDescription.setText(event.getDescription());
    }
}
