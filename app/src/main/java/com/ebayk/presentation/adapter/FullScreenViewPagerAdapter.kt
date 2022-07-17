package com.ebayk.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ebayk.databinding.FullscreenItemPageBinding
import com.ebayk.databinding.ItemPageBinding
import com.ebayk.util.IMAGE_FULL
import com.ebayk.util.IMAGE_PLACE_HOLDER


class FullScreenViewPagerAdapter(val banners: List<String>) :
    RecyclerView.Adapter<FullScreenViewPagerAdapter.SliderViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val binding = FullscreenItemPageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.bind(banners[position])
    }

    override fun getItemCount(): Int = banners.size

    class SliderViewHolder(private val binding: FullscreenItemPageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(banner: String) {
            binding.apply {
                Glide.with(imageSlider.context).load(banner.replace(IMAGE_PLACE_HOLDER, IMAGE_FULL))
                    .into(imageSlider)
            }
        }

    }
}