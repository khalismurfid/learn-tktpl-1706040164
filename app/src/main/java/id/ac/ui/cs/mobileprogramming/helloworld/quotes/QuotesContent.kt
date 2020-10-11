package id.ac.ui.cs.mobileprogramming.helloworld.quotes

import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object QuotesContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<QuotesItem> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, QuotesItem> = HashMap()

    private val ITEM_QUOTES_DUMMY: MutableMap<String, List<String>> = HashMap()

    init {
        // Generate Dummy Quotes
        generateQuotesDummy()
        var counter = 1
        for ((title, detail) in ITEM_QUOTES_DUMMY) {
            addItem(createQuotesItem(counter, title, detail))
            counter++
        }
    }

    private fun addItem(item: QuotesItem) {
        ITEMS.add(item)
        ITEM_MAP[item.id] = item
    }

    private fun generateQuotesDummy(){
        ITEM_QUOTES_DUMMY["Naruto"] = listOf(
            "If you don’t like the hand that fate’s dealt you with, fight for a new one.",
            "Hard work is worthless for those that don’t believe in themselves.",
            "It’s not the face that makes someone a monster, it’s the choices they make with their lives.",
            "Once you question your own belief it’s over."
        )
        ITEM_QUOTES_DUMMY["Darling in the franxx"] = listOf(
            "Once we die, we’ll only be a statistic. It won’t matter what we were called.",
            "If you have anything you wanna say, you better spit it out while you can. Because you’re all going to die sooner or later.",
            "The weak ones die, big deal",
            "If you don’t belong here, just a build a place where you do. If you don’t have a partner, just find another one. If you can’t find one, just take one by force!"
        )
        ITEM_QUOTES_DUMMY["Fate"] = listOf(
            "The wish of wanting everyone to be happy is merely a fairy tale!",
            "When something terrible happens, it’s true you can only save a handful of people, no matter how hard you try. But that doesn't make it okay!",
            "It is at the moment of death that humanity has value.",
            "Fighting for others, but not yourself, is nothing but hypocrisy."
        )
        ITEM_QUOTES_DUMMY["Spongebob"] = listOf(
            "Is mayonnaise an instrument?",
            "It's just a cruel reminder that I'm single and likely to remain that way forever.",
            "Hmmm, a five-letter word for happiness...money.",
            "Goodbye everyone, I'll remember you all in therapy."
        )
        ITEM_QUOTES_DUMMY["Haikyuu"] = listOf(
            "The future belongs to those who believe in the beauty of their dreams.”",
            "Before my eyes, it blocks my path. A high, high wall. What sort of scene is on the other side? What will I be able to see there? “The view from the top”. A scenery I will never be able to see on my own. But if I’m not alone, then… I might be able to see it.”",
            "Do you need a reason to not want to lose?",
            "There’s no guarantee that the weapon that worked first will continue working until the end. I have to keep moving forward. I have to keep going! My greatest weapon is not getting caught!"
        )
    }
    private fun createQuotesItem(position: Int, title: String, details: List<String>): QuotesItem {
        return QuotesItem(position.toString(), title, details)
    }

    /**
     * A dummy item representing a piece of content.
     */
    data class QuotesItem(val id: String, val title: String, val details: List<String>) {
        override fun toString(): String = title
    }
}