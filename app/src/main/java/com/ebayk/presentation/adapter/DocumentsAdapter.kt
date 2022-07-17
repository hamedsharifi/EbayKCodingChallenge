package com.ebayk.presentation.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ebayk.EbayApplication
import com.ebayk.core.model.Documents
import com.ebayk.databinding.WeitereInformationItemBinding
import com.ebayk.presentation.interfaces.OnDocumentClick


class DocumentsAdapter(
    private val items: List<Documents>,
    val onDocumentClick: OnDocumentClick
) : RecyclerView.Adapter<DocumentsAdapter.DocumentViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocumentViewHolder {
        val binding = WeitereInformationItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DocumentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DocumentViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class DocumentViewHolder(val binding: WeitereInformationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(document: Documents) {
            binding.apply {
                title.text = document.title
                parentLayout.setOnClickListener {
                    onDocumentClick.onDocumentClicked(document.link)
                }
            }
        }


    }

}