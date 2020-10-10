package id.ac.ui.cs.mobileprogramming.helloworld

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuotesViewModel : ViewModel() {

    val quotes = MutableLiveData<List<String>>()

    fun setQuotes(quotes: List<String>){
        this.quotes.value = quotes
    }
}