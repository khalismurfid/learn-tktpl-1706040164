package id.ac.ui.cs.mobileprogramming.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*;
import android.widget.TextView
import android.widget.Button
import android.view.View

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonGetQuotes: Button = findViewById(R.id.buttonGetQuotes)
        buttonGetQuotes.setOnClickListener{
            getQuotes()
        }

    }

    private fun getQuotes() {
        val quotesView : TextView = findViewById(R.id.quotes) as TextView
        val quotes: List<String> =   listOf("The world isn’t perfect. But it’s there for us, doing the best it can….that’s what makes it so damn beautiful.",
            "Knowing you’re different is only the beginning. If you accept these differences you’ll be able to get past them and grow even closer.", "Even if I die, you keep living okay? Live to see the end of this world, and to see why it was born. Live to see why a weak girl like me ended up here… And the reason you and I met.",
            "Giving up is what kills people.",
            "The ticket to the future is always open.",
            "Hurt me with the truth. But never comfort me with a lie.",
            "You’ve got two legs and a heartbeat. What’s stopping you?")
        quotesView.text = quotes.shuffled().take(1)[0]
    }
}
