package io.github.ravindragv.nasapictures

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.github.ravindragv.nasapictures.activities.MainActivity
import io.github.ravindragv.nasapictures.fragments.ImageDetailFragment
import io.github.ravindragv.nasapictures.factory.ImageFragmentFactory
import io.github.ravindragv.nasapictures.model.ImageMetaData
import io.github.ravindragv.nasapictures.model.ImageMetaDataViewModel
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.text.SimpleDateFormat

@RunWith(AndroidJUnit4::class)
class ImageDetailTest {
    @get:Rule
    val activityScenarioRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(
        MainActivity::class.java
    )
    private lateinit var imageMetaDataViewModel: ImageMetaDataViewModel
    private lateinit var imageFragmentFactory: ImageFragmentFactory
    private lateinit var imageMetaDataList: List<ImageMetaData>

    @Before
    fun setUp() {
        activityScenarioRule.scenario.onActivity { activity ->
            imageMetaDataViewModel = activity
                .defaultViewModelProviderFactory
                .create(ImageMetaDataViewModel::class.java)
            imageMetaDataList = imageMetaDataViewModel.getList()
            imageFragmentFactory = ImageFragmentFactory(imageMetaDataViewModel)
        }
    }

    @Test
    fun testAllViewsAreVisible() {
        launchFragmentInContainer<ImageDetailFragment>(factory = imageFragmentFactory)

        onView(withId(R.id.iv_full_image)).check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.tv_copyright)).check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.tv_date)).check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.tv_explanation)).check(matches(isDisplayed()))
    }

    @Test
    fun testContentForFirstPosition() {
        imageMetaDataViewModel.currentPosition = 0

        launchFragmentInContainer<ImageDetailFragment>(factory = imageFragmentFactory)
        onView(withId(R.id.tv_title))
            .check(matches(withText(
                imageMetaDataList[imageMetaDataViewModel.currentPosition].title)))
        onView(withId(R.id.tv_copyright))
            .check(matches(withText(
                imageMetaDataList[imageMetaDataViewModel.currentPosition].copyright)))
        val date = SimpleDateFormat("yyyy-MM-dd")
            .format(imageMetaDataList[imageMetaDataViewModel.currentPosition].date)
        onView(withId(R.id.tv_date))
            .check(matches(withText(date)))
        onView(withId(R.id.tv_explanation))
            .check(matches(withText(
                imageMetaDataList[imageMetaDataViewModel.currentPosition].explanation)))

    }

    @Test
    fun testContentForLastPosition() {
        imageMetaDataViewModel.currentPosition = imageMetaDataList.size - 1

        launchFragmentInContainer<ImageDetailFragment>(factory = imageFragmentFactory)
        onView(withId(R.id.tv_title))
            .check(matches(withText(
                imageMetaDataList[imageMetaDataViewModel.currentPosition].title)))
        onView(withId(R.id.tv_copyright))
            .check(matches(withText(
                imageMetaDataList[imageMetaDataViewModel.currentPosition].copyright)))
        val date = SimpleDateFormat("yyyy-MM-dd")
            .format(imageMetaDataList[imageMetaDataViewModel.currentPosition].date)
        onView(withId(R.id.tv_date))
            .check(matches(withText(date)))
        onView(withId(R.id.tv_explanation))
            .check(matches(withText(
                imageMetaDataList[imageMetaDataViewModel.currentPosition].explanation)))
    }

    @Test
    fun testContentWithoutCopyright() {
        imageMetaDataViewModel.currentPosition = 2

        launchFragmentInContainer<ImageDetailFragment>(factory = imageFragmentFactory)
        onView(withId(R.id.tv_title))
            .check(matches(withText(
                imageMetaDataList[imageMetaDataViewModel.currentPosition].title)))
        onView(withId(R.id.tv_copyright)).check(matches(not(isCompletelyDisplayed())))
        val date = SimpleDateFormat("yyyy-MM-dd")
            .format(imageMetaDataList[imageMetaDataViewModel.currentPosition].date)
        onView(withId(R.id.tv_date))
            .check(matches(withText(date)))
        onView(withId(R.id.tv_explanation))
            .check(matches(withText(
                imageMetaDataList[imageMetaDataViewModel.currentPosition].explanation)))
    }
}