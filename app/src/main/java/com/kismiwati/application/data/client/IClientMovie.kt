package com.kismiwati.application.data.client

import com.kismiwati.application.domain.model.MovieDetails
import com.kismiwati.application.domain.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

//class interface yang berisi list enpoint yang akan digunakan
//retrofit yang digunakan adalah anotasi @get untuk request method yang digunakan.
interface IClientMovie {

    companion object {
        const val API_KEY = "?api_key=d0424b3b44e681c7d399af0fb11d6091&page=1&language=pt-BR"
        const val baseUrl = "https://api.themoviedb.org/3/"
    }
    @GET("trending/movie/week$API_KEY")
    fun getBreeds(): Call<MovieResponse>

    @GET("movie/{movie_id}$API_KEY")
    fun getDetailsMovies(@Path("movie_id") movieId: Int): Call<MovieDetails>
}
