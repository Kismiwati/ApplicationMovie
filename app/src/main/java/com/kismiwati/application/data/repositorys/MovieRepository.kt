package com.kismiwati.application.data.repositorys

import com.kismiwati.application.data.client.IClientMovie
import com.kismiwati.application.domain.model.MovieDetails
import com.kismiwati.application.domain.model.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class MovieRepository(private val movieClient: IClientMovie) : IMovieRepository {
    override suspend fun getMovies(): Call<MovieResponse> {
        //memindahkan eksekusi coroutine ke operator I/O
        //digunakan untuk memblokir kode permintaan jaringan
        return withContext(Dispatchers.IO) {
            movieClient.getBreeds()
        }
    }

    override suspend fun getDetailsMovies(movieID: Int): Call<MovieDetails> {
        //memindahkan eksekusi coroutine ke operator I/O
        //digunakan untuk memblokir kode permintaan jaringan
        return withContext(Dispatchers.IO) {
            movieClient.getDetailsMovies(movieId = movieID)
        }
    }
}
