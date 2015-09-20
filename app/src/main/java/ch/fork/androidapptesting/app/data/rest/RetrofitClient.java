package ch.fork.androidapptesting.app.data.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class RetrofitClient {

    private static final String default_service_url = "https://mfg-eventapp.herokuapp.com";


    RestAdapter restAdapter;
    EventRetrofitService eventRetrofitService;

    RetrofitClient() {
        this(default_service_url);
    }

    RetrofitClient(final String endpoint) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        eventRetrofitService = restAdapter.create(EventRetrofitService.class);
    }

    public EventRetrofitService getEventRetrofitService() {
        return eventRetrofitService;
    }
}
