package com.example.bhagavatgeeta.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bhagavatgeeta.databinding.VerseRecycleViewBinding
import com.example.bhagavatgeeta.model.VerseItem

class VerseAdapter : RecyclerView.Adapter<VerseAdapter.ViewHolder>() {


    class ViewHolder(val binding: VerseRecycleViewBinding) :
        RecyclerView.ViewHolder(binding.root) {}


    val diffUtil = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
           return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
           return  oldItem == newItem
        }


    }

    val differ = AsyncListDiffer(this, diffUtil)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerseAdapter.ViewHolder {
        return ViewHolder(
            VerseRecycleViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    }

    override fun onBindViewHolder(holder: VerseAdapter.ViewHolder, position: Int) {

        val verse = differ.currentList[position]

        holder.binding.apply {
holder.binding.tvVerseNumber.text = "Verse ${position+1}"
            holder.binding.tvVerseDes.text = verse


        }


    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}