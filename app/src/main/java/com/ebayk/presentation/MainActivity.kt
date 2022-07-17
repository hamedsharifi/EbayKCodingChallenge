package com.ebayk.presentation

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.ebayk.R
import com.ebayk.core.model.Ad
import com.ebayk.core.model.Attributes
import com.ebayk.core.model.Documents
import com.ebayk.databinding.ActivityMainBinding
import com.ebayk.presentation.adapter.DetailsAdapter
import com.ebayk.presentation.adapter.DocumentsAdapter
import com.ebayk.presentation.adapter.FeaturesAdapter
import com.ebayk.presentation.adapter.ViewPagerAdapter
import com.ebayk.presentation.interfaces.OnDocumentClick
import com.ebayk.presentation.interfaces.OnPictureItemClick
import com.ebayk.presentation.interfaces.OnSharePictureClick
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : AppCompatActivity(), OnDocumentClick, OnPictureItemClick, OnSharePictureClick {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: AdViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = getViewModel()

        viewModel.adDetail.observe(this) {
            basicInfo(it)

            viewPager(it.pictures)

            detailSection(it)

            featureSection(it)

            documentsSection(it)

            descriptionSection(it)

            binding.scrollView.visibility = View.VISIBLE
        }

        viewModel.loading.observe(this) {
            binding.scrollView.visibility = if (it) View.GONE else View.VISIBLE
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }

        viewModel.getAdDetail()
    }

    private fun descriptionSection(it: Ad) {
        if (it.description.isNullOrEmpty()) {
            binding.descriptionSection.visibility = View.GONE
        } else {
            description(it)
        }
    }

    private fun documentsSection(it: Ad) {
        if (it.documents.isNullOrEmpty()) {
            binding.documentsSection.visibility = View.GONE
        } else {
            it.documents.let {
                if (it is List<Documents>) {
                    documentsAdapter(it)
                }
            }

        }
    }

    private fun featureSection(it: Ad) {
        if (it.features.isNullOrEmpty()) {
            binding.featuresSection.visibility = View.GONE
        } else {
            it.features.let {
                if (it is List<String>) {
                    featuresAdapter(it)
                }
            }

        }
    }

    private fun detailSection(it: Ad) {
        if (it.attributes.isNullOrEmpty()) {
            binding.detailsSection.visibility = View.GONE
        } else {
            it.attributes.let {
                if (it is List<Attributes>) {
                    detailAdapter(it)
                }
            }
        }
    }

    private fun basicInfo(ad: Ad) {
        binding.adTitle.text = ad.title
        binding.price.text =
            getString(R.string.price, ad.price.amount.toString(), ad.price.currency)
        binding.address.text = getString(
            R.string.ad_address,
            ad.address.street,
            ad.address.zipCode.toString(),
            ad.address.city
        )
        binding.address.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:${ad.address.latitude},${ad.address.longitude}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            try {
                startActivity(mapIntent)
            } catch (ex: ActivityNotFoundException) {
                Toast.makeText(this, R.string.application_not_found, Toast.LENGTH_LONG).show()
            }
        }
        binding.dateTime.text = ad.postedDateTime.substring(0, 10)
        binding.visits.text = ad.visits.toString()
        binding.adId.text = getString(R.string.ad_id, ad.id.toString())
    }

    private fun viewPager(pictures: List<String>) {
        binding.bannerSliderViewPager.adapter = ViewPagerAdapter(pictures, this, this)
        binding.bannerSliderViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }

    private fun documentsAdapter(documents: List<Documents>) {
        val documentsAdapter = DocumentsAdapter(documents, this)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.documentsRecyclerView.layoutManager = layoutManager
        binding.documentsRecyclerView.adapter = documentsAdapter
    }

    private fun featuresAdapter(features: List<String>) {
        val featuresAdapter = FeaturesAdapter(features)
        val layoutManager = GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false)
        binding.featuresRecyclerView.layoutManager = layoutManager
        binding.featuresRecyclerView.adapter = featuresAdapter
    }

    private fun detailAdapter(attributes: List<Attributes>) {
        val detailsAdapter = DetailsAdapter(attributes)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.detailRecyclerView.layoutManager = layoutManager
        binding.detailRecyclerView.adapter = detailsAdapter
    }

    private fun description(ad: Ad) {
        binding.description.text = ad.description
    }

    override fun onDocumentClicked(value: String?) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(value))
        browserIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(browserIntent)
    }

    override fun onPictureClicked(value: List<String>?, position: Int) {
        val imageViewer = Intent(this, ImageViewerActivity::class.java)
        imageViewer.putStringArrayListExtra(
            ImageViewerActivity.PICTURES,
            value as ArrayList<String>
        )
        imageViewer.putExtra(ImageViewerActivity.POSITION, position)
        startActivity(imageViewer)
    }

    override fun onSharePictureClicked(value: String?) {
        // Should send image instead of link in the future
        try {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Ebay Kleinanzeigen")
            val
            shareMessage =
                """
                ${value}
                """.trimIndent()
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
            startActivity(Intent.createChooser(shareIntent, "choose one"))
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }


}