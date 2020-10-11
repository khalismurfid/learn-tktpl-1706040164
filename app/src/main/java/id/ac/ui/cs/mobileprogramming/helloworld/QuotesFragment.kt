 package id.ac.ui.cs.mobileprogramming.helloworld

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import id.ac.ui.cs.mobileprogramming.helloworld.quotes.QuotesContent


 class QuotesFragment : Fragment() {

    companion object {
        fun newInstance() = QuotesFragment()
    }

    private lateinit var viewModel: QuotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(activity!!).get(QuotesViewModel::class.java)
        val rootView: View = inflater.inflate(R.layout.fragment_quotes, container, false)
        val linearLayout: LinearLayout = rootView.findViewById(R.id.quotes_details_linear)
        val titleView: TextView = rootView.findViewById(R.id.quotes_details_title)
        viewModel.quotes.observe(this,
            Observer<QuotesContent.QuotesItem> { quotesItem ->
                for (quotes in quotesItem.details){
                    val textView = TextView(activity).apply {
                        layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        ).apply {
                            setMargins(0, 20, 0, 0)
                            setPadding(10, 10, 0, 10)
                        }
                        text = '"' + quotes + '"'
                        setTextColor(Color.parseColor("#000000"))
                        textSize = 16.0f
                    }
                    linearLayout.addView(textView)
                }
                titleView.text = quotesItem.title
            })
        return rootView
    }

}