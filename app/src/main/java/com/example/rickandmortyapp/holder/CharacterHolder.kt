package com.example.rickandmortyapp.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortyapp.database.CharacterEntity
import com.example.rickandmortyapp.databinding.ListItemCharactersBinding
import com.example.rickandmortyapp.listeners.CharacterSelectionListener

class CharacterHolder private constructor(private val binding: ListItemCharactersBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun createHolder(parent: ViewGroup): CharacterHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ListItemCharactersBinding.inflate(inflater, parent, false)
            return CharacterHolder(binding)
        }
    }

    fun bind(entity: CharacterEntity?, characterSelectionListener: CharacterSelectionListener) {
        with(binding) {
            characterEntity = entity
            selectionListener = characterSelectionListener
            executePendingBindings()
        }
    }
}