package ch.fork.androidapptesting.app.ui.eventdetails;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.common.base.Optional;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ch.fork.androidapptesting.R;
import ch.fork.androidapptesting.app.AndroidAppTestingApp;
import ch.fork.androidapptesting.app.model.Event;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by bmu on 01.09.2015.
 */
public class EventDetailActivity extends AppCompatActivity implements EventDetailView {

    public static final String EXTRA_EVENT_ID = "EVENT_ID";
    public static final String GOOGLE_MAPS_URI_GEO_PREFIX = "geo:0,0?q=";
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tvEventTitle)
    TextView tvTitle;
    @Bind(R.id.tvEventDate)
    TextView tvDate;
    @Bind(R.id.tvLocation)
    TextView tvLocation;
    @Bind(R.id.tvEventDescription)
    TextView tvDescription;
    private EventDetailPresenter presenter;

    @OnClick(R.id.btnEventParticipate)
    public void onParticipateClick() {

        showUserNameInput(this, R.string.eventdetail_enter_name_title,
                R.string.eventdetail_enter_name_hint, R.string.eventdetail_enter_name_prefill)
                .subscribe(name -> {
                    if (name.isPresent()) {
                        presenter.participate(getEventIdFromIntent(), name.get());
                    } else {
                        Toast.makeText(this, R.string.eventdetail_enter_name_cancelled,
                                Toast.LENGTH_SHORT)
                             .show();
                    }
                });
    }

    Observable<Optional<String>> showUserNameInput(Context context, @StringRes int title,
                                                   @StringRes int hint,
                                                   @StringRes int prefill) {

        return Observable.create((Subscriber<? super Optional<String>> subscriber) -> {
            final MaterialDialog ad = new MaterialDialog.Builder(context)
                    .title(title)
                    .inputType(InputType.TYPE_CLASS_TEXT)
                    .input(hint, prefill, false, (materialDialog, charSequence) -> {
                                subscriber.onNext(Optional.of(charSequence.toString()));
                                subscriber.onCompleted();
                            }

                    )
                    .negativeText(android.R.string.cancel)
                    .cancelable(true)
                    .callback(new MaterialDialog.ButtonCallback() {
                        @Override public void onNegative(MaterialDialog dialog) {
                            subscriber.onNext(Optional.<String>absent());
                            subscriber.onCompleted();
                        }
                    })
                    .build();
            // cleaning up
            subscriber.add(Subscriptions.create(ad::dismiss));
            ad.show();
        });
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

        presenter = new EventDetailPresenter(this, AndroidAppTestingApp.get(this)
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
        tvLocation.setOnClickListener(v -> {
            Intent googleMapsIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(GOOGLE_MAPS_URI_GEO_PREFIX + event.getLocation()));
            googleMapsIntent.setPackage("com.google.android.apps.maps");
            startActivity(googleMapsIntent);

        });
        tvDescription.setText(event.getDescription());
    }

    @Override public void onParticipateSuccessful() {
        Toast.makeText(this, R.string.eventdetail_thanks_for_participating, Toast.LENGTH_SHORT).show();
    }
}
