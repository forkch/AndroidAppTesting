package ch.fork.androidapptesting.app.ui.eventlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.List;

import ch.fork.androidapptesting.R;
import ch.fork.androidapptesting.app.AndroidAppTestingApp;
import ch.fork.androidapptesting.app.model.Event;
import ch.fork.androidapptesting.app.ui.eventdetails.EventDetailActivity;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by bmu on 01.09.2015.
 */
public class EventListActivity extends AppCompatActivity implements EventListView {

    private EventListPresenter presenter;
    private RecyclerView rvEventList;
    private EventListAdapter eventListAdapter;

    @Override
    public void openDetailsForEvent(Event event) {
        final Intent intent = new Intent(this, EventDetailActivity.class);
        intent.putExtra(EventDetailActivity.EXTRA_EVENT_ID, event.getId());
        startActivity(intent);

    }

    @Override
    public void showEvents(List<Event> allEvents) {
        eventListAdapter.clear();
        for (Event event : allEvents) {
            eventListAdapter.addEvent(event);
        }
    }

    @Override
    public void loadingEventsFaild() {
        Toast.makeText(this, "Loading events failed", Toast.LENGTH_SHORT)
             .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventlist);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Events");

        presenter = new EventListPresenter(this, AndroidAppTestingApp.get(this)
                                                                     .getEventService(),
                AndroidSchedulers.mainThread());

        eventListAdapter = new EventListAdapter(this, this);
        rvEventList = (RecyclerView) findViewById(R.id.rvEventList);
        rvEventList.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvEventList.setAdapter(eventListAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadEvents();
    }


}
