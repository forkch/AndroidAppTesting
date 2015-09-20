package ch.fork.androidapptesting.app;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.TestLifecycleApplication;

import java.lang.reflect.Method;

import ch.fork.androidapptesting.app.data.EventService;

/**
 * Created by bmu on 01.09.15.
 */
public class TestAndroidAppTestingApp extends AndroidAppTestingApp implements TestLifecycleApplication {

    @Mock
    EventService mockedEventService;

    @Override
    public void onCreate() {
        super.onCreate();

        MockitoAnnotations.initMocks(this);
    }

    public EventService getEventService() {
        return mockedEventService;
    }

    @Override
    public void beforeTest(Method method) {

    }

    @Override
    public void prepareTest(Object test) {

    }

    @Override
    public void afterTest(Method method) {

    }
}
