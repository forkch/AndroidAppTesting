package ch.fork.androidapptesting.app.ui.eventlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import ch.fork.androidapptesting.app.model.Event;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventViewHolder> {

    private final Context context;
    private final EventListView eventListView;
    private List<Event> eventList = new ArrayList<>();

    public EventListAdapter(Context context, EventListView eventListView) {
        this.context = context;
        this.eventListView = eventListView;
    }

    public void clear() {
        eventList.clear();
    }

    public void addEvent(Event event) {
        eventList.add(event);
        notifyDataSetChanged();
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new EventViewHolder(new EventView(context));
    }

    @Override
    public void onBindViewHolder(final EventViewHolder eventViewHolder, int i) {
        final Event event = eventList.get(i);

        eventViewHolder.eventView.setEvent(event);
        eventViewHolder.eventView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventListView.openDetailsForEvent(event);
            }
        });

        eventViewHolder.eventView.setLayoutParams(
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {

        private final EventView eventView;

        public EventViewHolder(EventView v) {
            super(v);
            eventView = v;
        }
    }
}