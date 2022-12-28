package com.kismiwati.application.presenter.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.kismiwati.application.data.client.ClientMovie.Companion.movieClientService
import com.kismiwati.application.data.repositorys.MovieRepository
import com.kismiwati.application.databinding.ActivityMovieDetailsBinding
import com.kismiwati.application.domain.model.Movie
import com.kismiwati.application.domain.model.MovieDetails
import com.kismiwati.application.presenter.viewmodel.MovieDetailsViewModel
import com.kismiwati.application.presenter.viewmodel.MovieDetailsViewModelFactory

//bagian ini adalah class MovieDetailsActivity
class MovieDetailsActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMovieDetailsBinding.inflate(layoutInflater)
    }

    private val movieRepository = MovieRepository(movieClientService)
    private val movieDetailsFactory = MovieDetailsViewModelFactory(movieRepository)
    private val moviesDetailsViewModel by viewModels<MovieDetailsViewModel> { movieDetailsFactory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getMovieDetails()
    }

    private fun bindDetails(movieDetails: MovieDetails) {
        binding.movieName.text = movieDetails.title
        binding.movieDescrition.text = movieDetails.overview
        binding.movieVoto.rating = (movieDetails.voteAverage!! / 2).toFloat()

        Glide
            .with(binding.root.context)
            .load("https://image.tmdb.org/t/p/original" + movieDetails.posterPath)
            .centerCrop()
            .into(binding.moviePoster)
    }

    private fun getMovieDetails() {
        val movie: Movie = intent.getSerializableExtra("movie") as Movie
        moviesDetailsViewModel.getMoviesDetailsFromRetrofit(movie.id)
        moviesDetailsViewModel.moviesDetails.observe(this) { movieDetails ->
            bindDetails(movieDetails)
        }
    }
}
