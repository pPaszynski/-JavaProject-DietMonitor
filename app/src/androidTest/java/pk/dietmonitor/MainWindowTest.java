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

public class MainWindowTest {

    private MainWindow mainWindow = null;
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(SearchProductWindow.class.getName(), null, false);

    @Rule
    public ActivityTestRule<MainWindow> mainWindowActivityTestRule = new ActivityTestRule<>(MainWindow.class);

    @Before
    public void setUp() throws Exception {
        mainWindow = mainWindowActivityTestRule.getActivity();

    }

    @Test
    public void btnFindProduct_onClick_ShouldLaunch_SearchProductWindow() {
        assertNotNull("There is no button with id='button_find_produkt'", mainWindow.findViewById(R.id.button_find_produkt));

        onView(withId(R.id.button_find_produkt)).perform(click());

        //int timeDelayIn_ms = 5000;
        Activity searchProductWindow = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);

        assertNotNull("Activity with name '.SearchProductWindow' is a null pointer", searchProductWindow);

        searchProductWindow.finish();
    }

    @After
    public void tearDown() throws Exception {
        mainWindow = null;
        // mainWindowActivityTestRule.finishActivity(); - is executed automatically after method marked with @After
    }
}