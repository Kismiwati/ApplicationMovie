package com.kismiwati.application.data.Dao

import android.content.Context
import androidx.room.Room
import com.kismiwati.application.domain.model.Movie

//pada bagian ini adalah class MovieDaoRepository yang dibuat didalam package movieDaoRepository.
class MovieDaoRepository {
    companion object {
        private lateinit var context: Context
        fun setContext(contextCoin: Context) {
            context = contextCoin
        }
    }

    //function loadDatabase diguakan untuk memuat database dengan ketentuan id
    fun loadDatabase(id: Int): Movie {
        val databaseMovie = Room
            .databaseBuilder(context, MovieDatabase::class.java, "movie")
            .allowMainThreadQueries()
            .build()
        return databaseMovie.movieDao().load(id)
    }

    //function addFavorite digunakan untuk menambahkan movie yang disukai
    fun addFavorite(movie: Movie) {
        val databaseMovie = Room
            .databaseBuilder(context, MovieDatabase::class.java, "movie")
            .allowMainThreadQueries()
            .build()
        databaseMovie.movieDao().save(movie)
    }

    //function deleteFavorite digunakan untuk menghapus movie yang disukai
    fun deleteFavorite(movieId: Movie) {
        val databaseMovie = Room
            .databaseBuilder(context, MovieDatabase::class.java, "movie")
            .allowMainThreadQueries()
            .build()
        return databaseMovie.movieDao().delete(movieId)
    }

    //function listFavorite digunakan untuk mengelist daftar movie yang disukai
    fun listFavorite(): List<Movie> {
        val databaseMovie = Room
            .databaseBuilder(context, MovieDatabase::class.java, "movie")
            .allowMainThreadQueries()
            .build()
        return databaseMovie.movieDao().getInvited()
    }
}