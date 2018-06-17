package pk.dietmonitor;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNotNull;

public class SearchProductWindowTest {

    private SearchProductWindow searchProductWindow = null;
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(SearchProductWindow.class.getName(), null, false);

    @Rule
    public ActivityTestRule<SearchProductWindow> searchProductWindowActivityTestRule = new ActivityTestRule<>(SearchProductWindow.class);

    @Before
    public void setUp() throws Exception {
        searchProductWindow = searchProductWindowActivityTestRule.getActivity();

    }

    @Test
    public void btnFindProduct_onClick_ShouldLaunch_SearchProductWindow() {
        assertNotNull("There is no button with id='button_find_produkt'", searchProductWindow.findViewById(R.id.mt_search_bar));

        onView(withId(R.id.mt_search_bar)).perform(click());

        //int timeDelayIn_ms = 5000;
        Activity searchProductWindow = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);

        assertNotNull("Activity with name '.SearchProductWindow' is a null pointer", searchProductWindow);

        searchProductWindow.finish();
    }

    @After
    public void tearDown() throws Exception {
        searchProductWindow = null;
        // mainWindowActivityTestRule.finishActivity(); - is executed automatically after method marked with @After
    }
}