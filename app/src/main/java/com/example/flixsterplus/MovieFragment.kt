package com.example.flixsterplus

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers

private const val API_KEY = BuildConfig.API_KEY

class MovieFragment : Fragment(), OnListFragmentationListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        val progressBar = view.findViewById<ContentLoadingProgressBar>(R.id.progress)
        val recyclerView = view.findViewById<RecyclerView>(R.id.list)
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        updateAdapter(progressBar, recyclerView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialize UI components here if needed
    }

    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = API_KEY

        client.get("https://api.themoviedb.org/3/movie/now_playing", params, object : JsonHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Headers,
                response: JsonHttpResponseHandler.JSON
            ) {
                progressBar.hide()

                try {
                    val gson = Gson()

                    // Extract "results" array from the response
                    val moviesJsonArray = response.jsonObject.getJSONArray("results")

                    // Convert JSON array to list of MovieClass
                    val arrayMovieType = object : TypeToken<List<MovieClass>>() {}.type
                    Log.v("Json data", moviesJsonArray.toString())
                    val models: List<MovieClass> = gson.fromJson(moviesJsonArray.toString(), arrayMovieType)

                    // Set the adapter
                    recyclerView.adapter = MoviesRecyclerViewAdapter(models, this@MovieFragment)
                } catch (e: Exception) {
                    Log.e("MovieFragment", "Error parsing response", e)
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                t: Throwable?
            ) {
                progressBar.hide()
                Log.e("MovieFragment", "Error: $errorResponse", t)
            }
        })
    }

    override fun onItemClick(item: MovieClass) {
        Toast.makeText(context, "Clicked: " + item.title, Toast.LENGTH_LONG).show()
    }
}
