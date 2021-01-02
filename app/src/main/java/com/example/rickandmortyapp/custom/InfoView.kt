package com.example.rickandmortyapp.custom

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.databinding.ComponentInfoViewBinding

class InfoView @JvmOverloads constructor(ctx: Context, attr: AttributeSet? = null) : LinearLayout(ctx, attr) {

    private val binding: ComponentInfoViewBinding by lazy(LazyThreadSafetyMode.NONE) {
        val layoutInflater = LayoutInflater.from(context)
        DataBindingUtil.inflate(layoutInflater, R.layout.component_info_view, this, true)
    }

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER

        val backgroundDrawable = ContextCompat.getDrawable(context, R.drawable.stroke_rect_background_drawable)
        background = backgroundDrawable


        val ta = context.obtainStyledAttributes(attr, R.styleable.InfoView)

        try {
            val titleRes = ta.getResourceId(R.styleable.InfoView_infoTitle, R.string.info_view_default_title)
            val infoText = ta.getString(R.styleable.InfoView_infoText)

            val title = context.getString(titleRes)

            binding.infoTitle.text = title
            binding.infoText.text = infoText
        } finally {
            ta.recycle()
        }

        if (isInEditMode && binding.infoText.text.isNullOrEmpty()) {
            val info = context.getString(R.string.info_view_default_text)
            setInfoText(info)
        }
    }


    fun setInfoText(infoText: String?) {
        binding.infoText.text = infoText
    }
}