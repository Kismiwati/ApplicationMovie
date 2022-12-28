package com.kismiwati.application.presenter.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kismiwati.application.data.client.ClientMovie.Companion.movieClientService
import com.kismiwati.application.data.model.MovieApiResult
import com.kismiwati.application.data.repositorys.MovieRepository
import com.kismiwati.application.databinding.ActivityMainBinding
import com.kismiwati.application.domain.model.Movie
import com.kismiwati.application.presenter.adapters.MovieItemAdapter
import com.kismiwati.application.presenter.viewmodel.MovieViewModel
import com.kismiwati.application.presenter.viewmodel.MovieViewModelFactory

class MainActivity : AppCompatActivity() {

    private val movieListAdapter by lazy {
        MovieItemAdapter(onClickListener = { movie ->
            goToMovieDetails(movie)
        })
    }
    private val movieRepository = MovieRepository(movieClientService)
    private val movieFactory = MovieViewModelFactory(movieRepository)
    private val movieViewModel by viewModels<MovieViewModel> { movieFactory }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //method yang menunjukkan kondisi awal saat activity tersebut mulai dijalankan
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.movieListRecyclerview.adapter = movieListAdapter
        movieViewModel.getMoviesFromRetrofit()

        binding.icFav?.setOnClickListener {
            val intent = Intent(this, FavoriteMoviesActivity::class.java)
            startActivity(intent)
        }


        getMoviesAndObserve()
    }

    private fun getMoviesAndObserve() {
        movieViewModel.getMoviesFromRetrofit()
        movieViewModel.movies.observe(this) { movieApiResult ->
            when (movieApiResult) {
                is MovieApiResult.Loading -> {
                    Log.d("INFO", "Loading")
                }
                is MovieApiResult.Success -> {
                    Log.d("INFO", "Success")
                    setupAdapter(movieApiResult.data)
                }
                is MovieApiResult.Error -> {
                    Log.d("INFO", "Error: ${movieApiResult.throwable.cause}")
                }
            }
        }
    }

    private fun goToMovieDetails(movie: Movie) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }
    private fun setupAdapter(list: List<Movie>) {
        movieListAdapter.submitList(list)
    }
}
