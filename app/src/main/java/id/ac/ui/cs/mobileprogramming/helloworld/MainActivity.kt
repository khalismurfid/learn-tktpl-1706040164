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
        supportFragmentManager.beginTransaction().replace(R.id.quotes_list_fragment, ItemQuotesFragment()).commit()
    }

    override fun onBackPressed() {
        if(supportFragmentManager.findFragmentById(R.id.quotes_list_fragment) is QuotesFragment){
            supportFragmentManager.beginTransaction().replace(R.id.quotes_list_fragment, ItemQuotesFragment()).commit()
        } else{
            super.onBackPressed()
        }
    }

}
