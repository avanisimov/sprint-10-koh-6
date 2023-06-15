package ru.practicum.sprint10koh6

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myView = findViewById<MyView>(R.id.my_view)

        val inflater = LayoutInflater.from(this)
        val adapter = Adapter(inflater)
        myView.setUpContainer(
            LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
            }
        )
        myView.adapter = adapter


        val items = (1..100).map {
            "Item $it"
        }

        myView.setItems(items)


    }


}


class MyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
) : ScrollView(context, attrs) {

    lateinit var adapter: Adapter

    lateinit var itemsContainer: ViewGroup


    fun setUpContainer(container: ViewGroup) {
        itemsContainer = container
        addView(itemsContainer)
    }


    fun setItems(items: List<String>) {
        val visibleItemsCount = 13
        items.subList(0, visibleItemsCount).forEach { item: String ->
            val child: View = adapter.adapt(itemsContainer, item)
            itemsContainer.addView(child)
        }
    }

}


class Adapter(
    private val inflater: LayoutInflater,
) {

    val viewsCache: MutableList<View> = mutableListOf()

    fun adapt(
        itemsContainer: ViewGroup,
        item: String
    ): View {
        val child = if (viewsCache.isEmpty()) {
            onCreateView(itemsContainer)
        } else {
            viewsCache.removeFirst()
        }
        onBindView(child, item)
        return child
    }

    fun onCreateView(itemsContainer: ViewGroup): View {
        return inflater.inflate(R.layout.v_item, itemsContainer, false)
    }

    fun onBindView(view: View, item: String) {
        val textView = view as TextView
        textView.text = item
    }
}
