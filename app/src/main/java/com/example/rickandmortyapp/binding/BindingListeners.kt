package com.example.rickandmortyapp.binding

import android.widget.CompoundButton
import android.widget.ToggleButton
import androidx.databinding.BindingAdapter

@BindingAdapter("checkListener")
fun ToggleButton.setToggleListener(toggleCheckListener: ToggleCheckListener?) {

    val listener = CompoundButton.OnCheckedChangeListener { _, isChecked ->
        toggleCheckListener?.onChecked(isChecked)
    }

    setOnCheckedChangeListener(listener)
}


interface ToggleCheckListener {
    fun onChecked(isChecked: Boolean)
}