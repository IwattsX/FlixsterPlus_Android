package com.example.flixsterplus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        // Retrieve data from intent
        val movieName = intent.getStringExtra("MOVIE_NAME")
        val movieImage = intent.getStringExtra("MOVIE_IMAGE")

        // Find views
        val titleTextView = findViewById<TextView>(R.id.movie_title)
        val imageView = findViewById<ImageView>(R.id.movie_image)

        // Set data to views
        titleTextView.text = movieName
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/$movieImage")
            .centerInside()
            .into(imageView)
    }
}

