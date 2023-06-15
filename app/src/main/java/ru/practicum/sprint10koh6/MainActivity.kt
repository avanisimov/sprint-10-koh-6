package ru.practicum.sprint10koh6

import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.sprint10koh6.holder.ItemViewHolder
import kotlin.random.Random

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ItemsAdapter()

        val itemsRecyclerView = findViewById<RecyclerView>(R.id.items)
        itemsRecyclerView.layoutManager = GridLayoutManager(this, 3).apply {
            spanSizeLookup = object :SpanSizeLookup(){
                override fun getSpanSize(position: Int): Int {
                    return if (position % 4 == 0){
                        3
                    } else {
                        1
                    }
                }

            }
        }
        itemsRecyclerView.adapter = adapter

        val items = (1..10).map {id ->
            Item(
                id = "id_$id",
                color = Color.HSVToColor(
                    arrayOf(Random.nextFloat().times(id*36), 1.0f, 1.0f).toFloatArray()
                )
            )
        }

        adapter.setUpItems(items)

    }


}

data class Item(
    val id: String,
    val color: Int,
)

class ItemsAdapter : RecyclerView.Adapter<ItemViewHolder>() {

    private var items: List<Item> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setUpItems(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

}



