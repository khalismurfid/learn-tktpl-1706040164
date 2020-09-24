package id.ac.ui.cs.mobileprogramming.khalismurfid.helloworld

import android.widget.TextView
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.junit.Before
import org.junit.Rule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matchers.not

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class HelloWorldInstrumentedTest {
    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity>
            = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testHelloWorldExist() {
        onView(ViewMatchers.withId(R.id.quotes))
            .check(ViewAssertions.matches(ViewMatchers.withText("Hello World!")))
    }

    @Test
    fun testQuotesChange() {
        onView(ViewMatchers.withId(R.id.buttonGetQuotes)).perform(click())
            .check(ViewAssertions.matches(not(ViewMatchers.withText("Hello World!"))))
    }
}
