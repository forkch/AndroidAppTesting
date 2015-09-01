package ch.fork.androidapptesting.app.ui.eventlist;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import androidapptesting.fork.ch.androidapptesting.R;
import ch.fork.androidapptesting.app.model.Event;

/**
 * Created by bmu on 01.09.2015.
 */
public class EventListActivity extends AppCompatActivity implements EventListView {

    private EventListPresenter presenter;
    private RecyclerView rvEventList;
    private EventListAdapter eventListAdapter;

    @Override public void openDetailsForEvent(Event event) {

    }

    @Override public void showEvents(List<Event> allEvents) {
        eventListAdapter.clear();
        for (Event event : allEvents) {
            eventListAdapter.addEvent(event);
        }
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventlist);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Events");

        presenter = new EventListPresenter(this);

        eventListAdapter = new EventListAdapter(this, this);
        rvEventList = (RecyclerView) findViewById(R.id.rvEventList);
        rvEventList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvEventList.setAdapter(eventListAdapter);
    }

    @Override protected void onResume() {
        super.onResume();
        presenter.loadEvents();
    }


}
