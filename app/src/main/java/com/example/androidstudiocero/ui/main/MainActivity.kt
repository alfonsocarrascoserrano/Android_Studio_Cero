package com.example.androidstudiocero.ui.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidstudiocero.R
import com.example.androidstudiocero.databinding.ActivityMainBinding
import com.example.androidstudiocero.model.Movie
import com.example.androidstudiocero.model.MovieDBClient
import com.example.androidstudiocero.ui.detail.DetailActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class MainActivity : AppCompatActivity() {

    companion object {
        private const val DEFAULT_REGION = "US"
    }

    private val moviesAdapter = MoviesAdapter(emptyList()) { navigateTo(it) }
    private lateinit  var fusedLocationClient : FusedLocationProviderClient

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
        isGranted ->
        doRequestPopularMovies(isGranted)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        binding.recycler.layoutManager = GridLayoutManager(this,3)
        binding.recycler.adapter = moviesAdapter

        requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)

    }

    private fun doRequestPopularMovies(isLocationGranted: Boolean) {
        lifecycleScope.launch{
            val apiKey = getString(R.string.api_key)
            val region = getRegion(isLocationGranted)
            val popularMovies = MovieDBClient.service.listPopularMovies(apiKey, region)
            moviesAdapter.movies = popularMovies.results
            moviesAdapter.notifyDataSetChanged()
        }
    }

    @SuppressLint("MissingPermission")
    private suspend fun getRegion(isLocationGranted: Boolean): String =
        suspendCancellableCoroutine { continuation ->
        if (isLocationGranted) {
            fusedLocationClient.lastLocation.addOnCompleteListener{
                continuation.resume(getRegionFromLocation(it.result))
            }
        } else {
            continuation.resume(DEFAULT_REGION)
        }
    }

    private fun getRegionFromLocation(location: Location?) : String {
        if (location == null){
            Toast.makeText(this, "US", Toast.LENGTH_SHORT)
            return DEFAULT_REGION
        }

        val geocoder = Geocoder(this)
        val result = geocoder.getFromLocation(
            location.latitude,
            location.longitude,
            1
        )
        Toast.makeText(this, result?.firstOrNull()?.countryCode, Toast.LENGTH_SHORT)
        return result?.firstOrNull()?.countryCode ?: DEFAULT_REGION
    }

    private fun navigateTo(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_MOVIE, movie)
        startActivity(intent)
    }
}
