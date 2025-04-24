package com.example.bhagavatgeeta.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.bhagavatgeeta.databinding.RecyleLytBinding
import com.example.bhagavatgeeta.model.chaptersItem

class AdapterFile( val kFunction1: (chaptersItem) -> Unit) :RecyclerView.Adapter<AdapterFile.viewHolder>() {

    class viewHolder(val binding: RecyleLytBinding) : ViewHolder(binding.root)


    val diffUtil = object : DiffUtil.ItemCallback<chaptersItem>() {
        override fun areItemsTheSame(oldItem: chaptersItem, newItem: chaptersItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: chaptersItem, newItem: chaptersItem): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffUtil)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterFile.viewHolder {
        return viewHolder(
            RecyleLytBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: AdapterFile.viewHolder, position: Int) {


        val chapter: chaptersItem = differ.currentList[position]



        holder.binding.apply {
            tvRvChapterNo.text = "chapter ${chapter.chapter_number}"
            tvRvChapterName.text = chapter.name_translated
            tvRvSaransh.text = chapter.chapter_summary
            tvRvViewCount.text = chapter.verses_count.toString()


        }
      holder.binding.card.setOnClickListener {
          kFunction1(chapter)
      }

    }


    override fun getItemCount(): Int {
        return differ.currentList.size

    }

}