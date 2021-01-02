package com.example.rickandmortyapp.binding

import android.widget.ImageView
import android.widget.ToggleButton
import androidx.databinding.BindingAdapter
import coil.load
import com.example.rickandmortyapp.R
import com.google.android.material.imageview.ShapeableImageView

@BindingAdapter("statusIcon")
fun ImageView.toStatusIcon(status: String) {

    val iconRes = when (status) {
        "Alive" -> R.drawable.ic_alive
        "Dead" -> R.drawable.ic_dead
        else -> R.drawable.ic_unknown
    }

    setImageResource(iconRes)
}

@BindingAdapter("imageUrl")
fun ShapeableImageView.setUrlImage(url: String) {
    load(url)
}

@BindingAdapter("android:checked")
fun ToggleButton.setFavoriteState(state: Int) {
    isChecked = state == 1
}