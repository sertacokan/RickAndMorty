package com.example.rickandmortyapp.network

import com.example.rickandmortyapp.R
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier

// String -> Int
class CharacterStatusAdapter {

    @FromJson
    @StatusIcon
    fun stateToIcon(status: String): Int {
        return when (status) {
            "Alive" -> R.drawable.ic_alive
            "Dead" -> R.drawable.ic_dead
            else -> R.drawable.ic_unknown
        }
    }

    @StatusIcon
    fun iconToState(@StatusIcon iconRes: Int): String {
        return when (iconRes) {
            R.drawable.ic_alive -> "Alive"
            R.drawable.ic_dead -> "Dead"
            else -> "unknown"
        }
    }
}


@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class StatusIcon