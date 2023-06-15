package ru.practicum.sprint10koh6.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.practicum.sprint10koh6.Item
import ru.practicum.sprint10koh6.R


class ItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(R.layout.v_item, parent, false)
) {

    val image: View = itemView.findViewById(R.id.image)

    fun bind(item: Item) {
        image.setBackgroundColor(item.color)
    }


}