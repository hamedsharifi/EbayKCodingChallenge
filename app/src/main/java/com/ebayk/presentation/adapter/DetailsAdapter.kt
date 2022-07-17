package com.ebayk.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ebayk.R
import com.ebayk.core.model.Attributes
import com.ebayk.databinding.DetailsItemBinding


class DetailsAdapter(
    private val items: List<Attributes>,
) : RecyclerView.Adapter<DetailsAdapter.DetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val binding = DetailsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class DetailViewHolder(val binding: DetailsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(attribute: Attributes) {
            binding.apply {
                title.text = attribute.label
                value.text = binding.root.context.getString(R.string.feature_value, attribute.value,
                    if (attribute.unit.isNullOrEmpty()) "" else attribute.unit)
            }
        }

    }
}