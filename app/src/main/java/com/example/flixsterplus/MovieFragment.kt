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

        // Make these span two times
        recyclerView.layoutManager = GridLayoutManager(context, 2)
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

        client.get("https://api.themoviedb.org/3/person/popular", params, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, response: JsonHttpResponseHandler.JSON) {
                progressBar.hide()

                try {
                    val gson = Gson()
                    val moviesJsonArray = response.jsonObject.getJSONArray("results")
                    val arrayMovieType = object : TypeToken<List<PersonClass>>() {}.type
                    val models: List<PersonClass> = gson.fromJson(moviesJsonArray.toString(), arrayMovieType)

                    // Iterate over each person
                    for (person in models) {
                        person.knownFor?.forEach { knownMovie ->
                            Log.d("Known Movie", "Title: ${knownMovie.title}, Overview: ${knownMovie.overview}")
                        }
                    }

                    // Set the adapter
                    recyclerView.adapter = MoviesRecyclerViewAdapter(models, this@MovieFragment)
                } catch (e: Exception) {
                    Log.e("MovieFragment", "Error parsing response", e)
                }
            }

            override fun onFailure(statusCode: Int, headers: Headers?, errorResponse: String, t: Throwable?) {
                progressBar.hide()
                Log.e("MovieFragment", "Error: $errorResponse", t)
            }
        })
    }



    override fun onItemClick(item: PersonClass) {
        Toast.makeText(context, "Clicked: " + item.name, Toast.LENGTH_LONG).show()
    }
}
