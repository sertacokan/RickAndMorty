package com.example.rickandmortyapp.delegations

import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

fun <DB : ViewDataBinding> Fragment.dataBinding(@LayoutRes layout: Int): Lazy<DB> {
    return lazy(LazyThreadSafetyMode.NONE) {
        DataBindingUtil.inflate(layoutInflater, layout, null, false)
    }
}