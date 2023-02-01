package com.example.androidstudiocero.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.bumptech.glide.Glide
import com.example.androidstudiocero.R
import com.example.androidstudiocero.databinding.ActivityDetailBinding
import com.example.androidstudiocero.model.Movie

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "DetailActivity:movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        if (movie != null){
            title = movie.title
            Glide
                .with(this)
                .load("https://image.tmdb.org/t/p/w780/${movie.backdrop_path}")
                .into(binding.backdrop)
            binding.summary.text = movie.overview
            bindDetailInfo(binding.detailinfo, movie)

            //binding.fab.setOnClickListener()
        }

    }

    private fun bindDetailInfo(detalInfo : TextView, movie: Movie) {
        detalInfo.text = buildSpannedString {
            appendInfo(R.string.original_language, movie.original_language)

            appendInfo(R.string.original_title, movie.original_title)

            appendInfo(R.string.release_date, movie.release_date)

            appendInfo(R.string.popularity, movie.popularity.toString())

            appendInfo(R.string.vote_average, movie.vote_average.toString())
        }
    }

    private fun SpannableStringBuilder.appendInfo(stringRes: Int, value: String){
        bold {
            append(getString(stringRes))
            append(": ")
        }
        appendLine(value)
    }
}