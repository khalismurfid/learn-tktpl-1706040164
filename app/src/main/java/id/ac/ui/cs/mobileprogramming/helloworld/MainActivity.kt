package id.ac.ui.cs.mobileprogramming.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*;
import android.widget.TextView
import android.widget.Button
import android.view.View

class MainActivity : AppCompatActivity() {

    /* A native method that is implemented by the
    * 'hello-jni' native library, which is packaged
    * with this application.
    */
    var quotesList: ArrayList<String> = ArrayList<String>()

    private external fun processQuotes(quotes: String ): String


    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonGetQuotes: Button = findViewById(R.id.buttonGetQuotes)
        buttonGetQuotes.setOnClickListener{
            getQuotes()
        }
        buttonSubmitQuotes.setOnClickListener {
            val processedQuotes = processQuotes(input_quotes.text.toString())
            quotesList.add(processedQuotes)
            quotes.text = processedQuotes
        }

    }

    private fun getQuotes() {
        val quotesView : TextView = findViewById(R.id.quotes) as TextView
        quotesView.text = quotesList.shuffled().take(1)[0]
    }


}
