package ch.fork.androidapptesting.app;

import android.app.Application;
import android.content.Context;

import ch.fork.androidapptesting.app.data.EventService;
import ch.fork.androidapptesting.app.data.rest.EventServiceRestImpl;

/**
 * Created by bmu on 01.09.15.
 */
public class AndroidAppTestingApp extends Application {

    private EventService eventService;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static AndroidAppTestingApp get(Context context) {
        return (AndroidAppTestingApp) context.getApplicationContext();
    }

    public EventService getEventService() {
        if(eventService == null) {
            eventService = new EventServiceRestImpl();
        }
        return eventService;
    }
}
