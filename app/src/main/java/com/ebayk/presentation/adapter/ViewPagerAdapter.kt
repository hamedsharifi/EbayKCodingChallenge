package com.ebayk.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ebayk.EbayApplication
import com.ebayk.R
import com.ebayk.databinding.ItemPageBinding
import com.ebayk.presentation.ImageViewerActivity
import com.ebayk.presentation.interfaces.OnPictureItemClick
import com.ebayk.presentation.interfaces.OnSharePictureClick
import com.ebayk.util.IMAGE_PLACE_HOLDER
import com.ebayk.util.IMAGE_SMALL


class ViewPagerAdapter(
    val banners: List<String>,
    val onPictureItemClick: OnPictureItemClick,
    val onSharePictureClick: OnSharePictureClick,
) :
    RecyclerView.Adapter<ViewPagerAdapter.SliderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {

        val binding = ItemPageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.bind(banners[position], position, banners.size)
    }

    override fun getItemCount(): Int = banners.size


    inner class SliderViewHolder(private val binding: ItemPageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(banner: String, position: Int, size: Int) {
            binding.apply {
                Glide.with(imageSlider.context)
                    .load(banner.replace(IMAGE_PLACE_HOLDER, IMAGE_SMALL))
                    .into(imageSlider)
                countBanner.text = size.toString()
                positionBanner.text =
                    positionBanner.context.getString(R.string.image_position, position + 1)

                share.setOnClickListener {
                    onSharePictureClick.onSharePictureClicked(
                        banner.replace(
                            IMAGE_PLACE_HOLDER,
                            IMAGE_SMALL
                        )
                    )
                }
            }
            binding.root.setOnClickListener {
                onPictureItemClick.onPictureClicked(banners, position)
            }
        }

    }
}