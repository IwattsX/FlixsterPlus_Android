package com.example.flixsterplus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import android.util.Log

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        // Retrieve data from intent
        val movieName = intent.getStringExtra("MOVIE_NAME")
        val movieImage = intent.getStringExtra("MOVIE_IMAGE")

        // Find views
        val titleTextView = findViewById<TextView>(R.id.person_title)
        val imageView = findViewById<ImageView>(R.id.person_image)

        // Set data to views
        titleTextView.text = movieName
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/$movieImage")
            .centerInside()
            .into(imageView)

        // Retrieve the ArrayList<KnownFor> from the intent
        val knownForList = intent.getParcelableArrayListExtra<KnownFor>("KNOWN_FOR_LIST")

        // Find views for best works
        val movieTitle = findViewById<TextView>(R.id.movie_title)
        val overview = findViewById<TextView>(R.id.overview)
        val posterImage = findViewById<ImageView>(R.id.movie_poster)

        // Check if the knownForList is not null and has at least one item
        knownForList?.let {
            if (it.isNotEmpty()) {
                val knownForFirstItem = it[0] // Get the first item in the list

                // Set the details for the first "Known For" movie/work
                movieTitle.text = knownForFirstItem.title
                overview.text = knownForFirstItem.overview

                Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500/${knownForFirstItem.posterPath}")
                    .centerInside()
                    .into(posterImage)
            } else {
                Log.e("DetailActivity", "KnownFor list is empty")
            }
        } ?: run {
            Log.e("DetailActivity", "KnownFor list is null")
        }


    }
}
