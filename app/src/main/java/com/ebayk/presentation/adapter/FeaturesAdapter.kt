package com.ebayk.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ebayk.databinding.AusstattungItemBinding


class FeaturesAdapter(
    private val items:List<String>
):RecyclerView.Adapter<FeaturesAdapter.AusstattungViewHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AusstattungViewHolder {
       val binding = AusstattungItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
      return  AusstattungViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AusstattungViewHolder, position: Int) {
       holder.bind(items[position])
    }

    override fun getItemCount(): Int =items.size

    class AusstattungViewHolder(val binding:AusstattungItemBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(ausstattung: String) {
            binding.title.text = ausstattung
        }


    }
}