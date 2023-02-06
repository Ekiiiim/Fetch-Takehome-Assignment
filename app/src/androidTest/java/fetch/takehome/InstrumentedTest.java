package fetch.takehome;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.util.Objects;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {

    @Rule
    public ActivityTestRule activityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void testListDisplay() {
        RecyclerView recyclerView = activityRule.getActivity().findViewById(R.id.recyclerView);
        assertTrue(Objects.requireNonNull(recyclerView.getAdapter()).getItemCount() > 0);
    }

    @Test
    public void testNameNotNull() {
        RecyclerView recyclerView = activityRule.getActivity().findViewById(R.id.recyclerView);
        int listSize = Objects.requireNonNull(recyclerView.getAdapter()).getItemCount();
        // The non-null list should have size of 320
        assertEquals(listSize, 320);
    }
}