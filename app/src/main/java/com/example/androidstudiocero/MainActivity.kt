package com.example.androidstudiocero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler = findViewById<RecyclerView>(R.id.recycler)
        val layoutmanager = GridLayoutManager(this,3)
        recycler.layoutManager = layoutmanager

        recycler.adapter = MoviesAdapter(listOf(
            Movie("Movie 1", "https://loremflickr.com/320/240?lock=1"), //loremflickr.com provides random placeholder images
            Movie("Movie 2", "https://loremflickr.com/320/240?lock=2"),
            Movie("Movie 3", "https://loremflickr.com/320/240?lock=3"),
            Movie("Movie 4", "https://loremflickr.com/320/240?lock=4"),
            Movie("Movie 5", "https://loremflickr.com/320/240?lock=5"),
            Movie("Movie 6", "https://loremflickr.com/320/240?lock=6"),
            Movie("Movie 7", "https://loremflickr.com/320/240?lock=7"),
            Movie("Movie 8", "https://loremflickr.com/320/240?lock=8"),
            Movie("Movie 9", "https://loremflickr.com/320/240?lock=9"),
        ))
    }
}
