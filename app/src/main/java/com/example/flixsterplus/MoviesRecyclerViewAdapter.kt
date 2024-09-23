package com.example.flixsterplus

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MoviesRecyclerViewAdapter (
    private val movies : List<PersonClass>,
    private val mListener : OnListFragmentationListener?,
)
    : RecyclerView.Adapter<MoviesRecyclerViewAdapter.MovieViewHolder>()
{
        inner class MovieViewHolder(val mView: View) : RecyclerView.ViewHolder(mView){
            var mItem: PersonClass? = null
            val mMovieTitle: TextView = mView.findViewById<View>(R.id.movie_title) as TextView
            val mMovieImage: ImageView = mView.findViewById<View>(R.id.movie_image) as ImageView


            override fun toString(): String {
                return mMovieTitle.toString()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movies, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        holder.mItem = movie
        holder.mMovieTitle.text = movie.name

        holder.mView.setOnClickListener {
            holder.mItem?.let { movie ->
                val context = holder.mView.context
                val intent = Intent(context, DetailActivity::class.java)

                // Pass any necessary data to DetailActivity using the intent
                intent.putExtra("MOVIE_NAME", movie.name)
                intent.putExtra("MOVIE_IMAGE", movie.faceImage)

                // Start the DetailActivity
                context.startActivity(intent)
            }
        }

        Glide.with(holder.mView)
            .load("https://image.tmdb.org/t/p/w500/" + movie.faceImage)
            .centerInside()
            .into(holder.mMovieImage)
    }




}