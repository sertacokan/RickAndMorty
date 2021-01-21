package com.example.rickandmortyapp

import androidx.lifecycle.LiveData

fun <T> LiveData<T>.observeForTesting(block: (liveDataValue: T?) -> Unit) {
    val observer = { _: T -> }
    try {
        observeForever(observer)
        block(value)
    } finally {
        removeObserver(observer)
    }
}