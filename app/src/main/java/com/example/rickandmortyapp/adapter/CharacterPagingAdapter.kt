package com.example.rickandmortyapp.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmortyapp.database.CharacterEntity
import com.example.rickandmortyapp.holder.CharacterHolder
import com.example.rickandmortyapp.listeners.CharacterSelectionListener

class CharacterPagingAdapter(private val characterSelectionListener: CharacterSelectionListener) :
    PagingDataAdapter<CharacterEntity, CharacterHolder>(CHARACTER_DIFF_UTIL) {

    companion object {
        private val CHARACTER_DIFF_UTIL = object : DiffUtil.ItemCallback<CharacterEntity>() {
            override fun areItemsTheSame(oldItem: CharacterEntity, newItem: CharacterEntity): Boolean {
                return oldItem.characterId == newItem.characterId
            }

            override fun areContentsTheSame(oldItem: CharacterEntity, newItem: CharacterEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        return CharacterHolder.createHolder(parent)
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        holder.bind(getItem(position),characterSelectionListener)
    }
}