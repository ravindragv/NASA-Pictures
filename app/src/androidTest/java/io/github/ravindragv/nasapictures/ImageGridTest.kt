package io.github.ravindragv.nasapictures

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.github.ravindragv.nasapictures.activities.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ImageGridTest {
    @get:Rule
    val activityScenarioRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(
        MainActivity::class.java
    )

    /**
     * Check if the image grid view is shown
     */
    @Test
    fun checkIfImageGridViewIsVisible() {
        onView(withId(R.id.rv_image_grid)).check(matches(isDisplayed()))
    }

    /**
     * Scroll to the first item in the grid
     */
    @Test
    fun scrollToFirstItemInGrid() {
        onView(withId(R.id.rv_image_grid))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }

    /**
     * Scroll to the last item in the list
     */
    @Test
    fun scrollToLastItemInGrid() {
        onView(withId(R.id.rv_image_grid))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(25, click()))
    }
}