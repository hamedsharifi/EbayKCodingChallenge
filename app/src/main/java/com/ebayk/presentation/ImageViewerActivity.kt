package com.ebayk.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.ebayk.databinding.ActivityImageViewerBinding
import com.ebayk.presentation.adapter.FullScreenViewPagerAdapter

class ImageViewerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageViewerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent.getStringArrayListExtra(PICTURES)?.let { viewPager(it) }
        binding.bannerSliderViewPager.post {
            binding.bannerSliderViewPager.setCurrentItem(intent.getIntExtra(POSITION, 0), true)
        }
    }

    private fun viewPager(pictures: List<String>) {
        binding.bannerSliderViewPager.adapter = FullScreenViewPagerAdapter(pictures)
        binding.bannerSliderViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }

    companion object {
        const val PICTURES: String = "pictures"
        const val POSITION: String = "position"
    }
}