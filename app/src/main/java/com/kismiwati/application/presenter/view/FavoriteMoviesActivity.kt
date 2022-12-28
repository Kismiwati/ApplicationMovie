package com.kismiwati.application.presenter.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kismiwati.application.databinding.ActivityMovieFavoritesBinding
import com.kismiwati.application.domain.model.Movie
import com.kismiwati.application.presenter.adapters.FavoriteItemAdapter

class FavoriteMoviesActivity  : AppCompatActivity() {
    private val binding by lazy {
        ActivityMovieFavoritesBinding.inflate(layoutInflater)
    }

    private val movieAdapter by lazy {
        FavoriteItemAdapter(onClickListener = { coin ->
            getCoinFavorites(coin)
        })
    }

    //method yang menunjukkan kondisi awal saat activity tersebut mulai dijalankan
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.icHome.setOnClickListener {
            val listIntent = Intent(this, MainActivity::class.java)
            startActivity(listIntent)
        }


        binding.favoriteListRecyclerview.adapter = movieAdapter

    }

    private fun setListAdapter(list: List<Movie>) {
        movieAdapter.submitList(list)
    }

    private fun getCoinFavorites(movie: Movie) {
        val favoriteIntent = Intent(this, FavoriteMoviesActivity::class.java)
        favoriteIntent.putExtra("movie", movie)
        startActivity(favoriteIntent)
    }
}