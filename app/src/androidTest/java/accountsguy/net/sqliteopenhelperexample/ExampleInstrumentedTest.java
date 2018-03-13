package accountsguy.net.sqliteopenhelperexample;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new
            ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("accountsguy.net.sqliteopenhelperexample", appContext.getPackageName());


//        onView(withId(R.id.name_insert)).perform(typeText("Venkkatesh"), ViewActions.closeSoftKeyboard());
//        onView(withId(R.id.address_insert)).perform(typeText("Kurnool"), ViewActions
//                .closeSoftKeyboard());
//        onView(withText("Insert")).perform(click());
//
//        onView(withId(R.id.search_id)).perform(typeText("1"), ViewActions.closeSoftKeyboard());
//        onView(withText("search")).perform(click());
    }
}
