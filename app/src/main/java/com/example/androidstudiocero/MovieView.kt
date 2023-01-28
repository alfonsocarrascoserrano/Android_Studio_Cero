package com.example.androidstudiocero

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide

class MovieView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val cover : ImageView
    private val title : TextView

    init {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.view_movie, this , true)
        orientation = VERTICAL

        cover = findViewById(R.id.cover)
        title = findViewById(R.id.title)
    }

    fun setMovie(movie : Movie){
        title.text = movie.title
        Glide.with(context).load(movie.cover).into(cover)
    }
}