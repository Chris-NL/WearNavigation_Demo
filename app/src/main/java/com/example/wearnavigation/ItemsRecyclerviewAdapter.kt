package com.example.wearnavigation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wearnavigation.databinding.FragmentThirdItemBinding

class ItemsRecyclerviewAdapter(
    private var items: Array<String>,
) :
    RecyclerView.Adapter<ItemsRecyclerviewAdapter.ItemsViewHolder>() {
    private val TAG = "ItemsRecyclerviewAdapter"

    class ItemsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var context: Context = itemView.context
        val binding = FragmentThirdItemBinding.bind(view)

        init {
            context = itemView.context
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemsViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_third_item, parent, false)
        return ItemsViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ItemsViewHolder,
        listPosition: Int
    ) {
        with(holder) {
            with(items[listPosition]) {
                binding.tvIconLabel.text = this
            }
        }
    }

    override fun getItemCount(): Int {
        return this.items.size
    }

}
