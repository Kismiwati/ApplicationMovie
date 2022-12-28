package com.kismiwati.application.data.repositorys

import com.kismiwati.application.domain.model.MovieDetails
import com.kismiwati.application.domain.model.MovieResponse
import retrofit2.Call

interface IMovieRepository {
    //bagian ini digunakan untuk menjeda eksekusi pengambilan corotine saat pemanggilan
    suspend fun getMovies(): Call<MovieResponse>
    suspend fun getDetailsMovies(movieId: Int): Call<MovieDetails>
}
