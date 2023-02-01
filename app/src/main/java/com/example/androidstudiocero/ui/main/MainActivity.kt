package com.example.androidstudiocero.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidstudiocero.R
import com.example.androidstudiocero.databinding.ActivityMainBinding
import com.example.androidstudiocero.model.Movie
import com.example.androidstudiocero.model.MovieDBClient
import com.example.androidstudiocero.ui.detail.DetailActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.layoutManager = GridLayoutManager(this,3)
        val moviesAdapter = MoviesAdapter(emptyList()) { navigateTo(it) }
        binding.recycler.adapter = moviesAdapter

        lifecycleScope.launch{
            val apiKey = getString(R.string.api_key)
            val popularMovies = MovieDBClient.service.listPopularMovies(apiKey)
            moviesAdapter.movies = popularMovies.results
            moviesAdapter.notifyDataSetChanged()
        }

    }

    private fun navigateTo(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_MOVIE, movie)
        startActivity(intent)
    }
}
