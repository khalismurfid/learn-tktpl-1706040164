package id.ac.ui.cs.mobileprogramming.khalismurfid.helloworld

import org.junit.Test
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class HelloWorldTest {
    @Test
    fun quotesIsShown() {
        val activity = MainActivity()
        // check if quotes is shown
        assertNotEquals(activity.getQuotes(), "")
    }
}
