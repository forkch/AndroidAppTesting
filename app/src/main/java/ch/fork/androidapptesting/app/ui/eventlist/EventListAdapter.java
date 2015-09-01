package ch.fork.androidapptesting.app.ui.eventlist;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import androidapptesting.fork.ch.androidapptesting.R;
import ch.fork.androidapptesting.app.model.Event;
import ch.fork.androidapptesting.app.model.Participant;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventViewHolder> {

    private final Context context;
    private List<Event> eventList = new ArrayList<>();
    private final EventListView eventListView;

    public EventListAdapter(Context context,  EventListView eventListView) {
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
    public int getItemCount() {
        return eventList.size();
    }

    @Override

    public void onBindViewHolder(final EventViewHolder eventViewHolder, int i) {
        final Event event = eventList.get(i);
        eventViewHolder.title.setText(event.getTitle());
        eventViewHolder.locationDate.setText(event.getLocation() + ", " + event.getDate());
        eventViewHolder.description.setText(event.getDescription());

//        eventViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                eventListView.openDetailsForEvent(event);
//            }
//        });

        String participantsText = "Keine Teilnehmer";
        if (event.getParticipants() != null) {
            List<Participant> participants = new ArrayList<>(event.getParticipants());
            participantsText = participants.size() + " Teilnehmer";
        }
        eventViewHolder.participants.setText(participantsText);
    }


    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_view, viewGroup, false);
        return new EventViewHolder(itemView);
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        protected TextView title;
        protected TextView locationDate;
        protected TextView description;
        protected TextView participants;
        protected Button details;
        protected ImageView eventImage;
        private final CardView cardView;

        public EventViewHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.cvEvent);
            title = (TextView) v.findViewById(R.id.title);
            locationDate = (TextView) v.findViewById(R.id.location_date);
            description = (TextView) v.findViewById(R.id.description);
            participants = (TextView) v.findViewById(R.id.participants);
            eventImage = (ImageView) v.findViewById(R.id.image);
        }
    }
}