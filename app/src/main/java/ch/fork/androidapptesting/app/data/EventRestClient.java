package ch.fork.androidapptesting.app.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class EventRestClient {

    private static final String default_service_url = "https://mfg-eventapp.herokuapp.com";


    RestAdapter restAdapter;
    EventRestService eventRestService;

    public EventRestClient() {
        this(default_service_url);
    }

    public EventRestClient(final String endpoint) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        eventRestService = restAdapter.create(EventRestService.class);
    }

    public EventRestService getEventRestService() {
        return eventRestService;
    }
}
