package id.ac.ui.cs.mobileprogramming.khalismurfid.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonGetQuotes: Button = findViewById(R.id.buttonGetQuotes)
        buttonGetQuotes.setOnClickListener{
            buttonGetQuotesClickListener()
        }

    }

     private fun buttonGetQuotesClickListener() {
        val quotesView : TextView = findViewById<TextView>(R.id.quotes)

        quotesView.text = getQuotes()
    }

    fun getQuotes() : String{
        val quotes: List<String> =   listOf("The world isn’t perfect. But it’s there for us, doing the best it can….that’s what makes it so damn beautiful.",
            "Knowing you’re different is only the beginning. If you accept these differences you’ll be able to get past them and grow even closer.", "Even if I die, you keep living okay? Live to see the end of this world, and to see why it was born. Live to see why a weak girl like me ended up here… And the reason you and I met.",
            "Giving up is what kills people.",
            "The ticket to the future is always open.",
            "Hurt me with the truth. But never comfort me with a lie.",
            "You’ve got two legs and a heartbeat. What’s stopping you?")
        return quotes.shuffled().take(1)[0]
    }
}
