package com.example.bhagavatgeeta.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bhagavatgeeta.databinding.VerseRecycleViewBinding

class VerseAdapter( val onVerseItemVClick: (Int, String) -> Unit) : RecyclerView.Adapter<VerseAdapter.ViewHolder>() {


    class ViewHolder(val binding: VerseRecycleViewBinding) :
        RecyclerView.ViewHolder(binding.root) {}


    private val diffUtil = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }


    }

    val differ = AsyncListDiffer(this, diffUtil)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            VerseRecycleViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val verse = differ.currentList[position]
        holder.binding.tvVerseNumber.text = "Verse ${position + 1}"
        holder.binding.tvVerseDes.text = verse


    holder.binding.verseCard.setOnClickListener {

        onVerseItemVClick(position+1,verse)
    }


    }

    override fun getItemCount(): Int {
        return differ.currentList.size
}
}