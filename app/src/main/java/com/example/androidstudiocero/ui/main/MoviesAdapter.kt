package com.example.androidstudiocero.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidstudiocero.databinding.ViewMovieBinding
import com.example.androidstudiocero.model.Movie

class MoviesAdapter(var movies: List<Movie>,
                    private val MovieClickedListener : (Movie) -> Unit) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { MovieClickedListener(movie)}
    }

    class ViewHolder(private val binding: ViewMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(movie: Movie) {
            binding.title.text = movie.title
            Glide.with(binding.cover.context).load("https://image.tmdb.org/t/p/w185/${movie.poster_path}").into(binding.cover)
        }
    }
}