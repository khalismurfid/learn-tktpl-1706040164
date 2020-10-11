package id.ac.ui.cs.mobileprogramming.helloworld

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.helloworld.quotes.QuotesContent

class QuotesViewModel : ViewModel() {

    val quotes = MutableLiveData<QuotesContent.QuotesItem>()

    fun setQuotes(quotes: QuotesContent.QuotesItem){
        this.quotes.value = quotes
    }
}