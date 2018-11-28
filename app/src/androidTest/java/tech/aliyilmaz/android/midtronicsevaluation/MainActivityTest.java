package tech.aliyilmaz.android.midtronicsevaluation;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import tech.aliyilmaz.android.midtronicsevaluation.Activity.CountriesActivity;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private MainActivity mainActivity = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(CountriesActivity.class.getName(), null, false);
    @Before
    public void setUp() throws Exception {
        mainActivity = mainActivityActivityTestRule.getActivity();
    }
    @Test
    public void testLaunchOfSecondActivityOnButtonClick(){
        assertNotNull(mainActivity.findViewById(R.id.countryButton));
        onView(withId(R.id.countryButton)).perform(click());
        Activity countriesActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(countriesActivity);
        countriesActivity.finish();
    }
    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}