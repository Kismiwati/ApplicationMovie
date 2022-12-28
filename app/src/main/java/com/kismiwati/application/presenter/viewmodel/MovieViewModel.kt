package com.kismiwati.application.presenter.viewmodel

import androidx.lifecycle.*
import com.kismiwati.application.data.model.MovieApiResult
import com.kismiwati.application.data.repositorys.IMovieRepository
import com.kismiwati.application.data.repositorys.MovieRepository
import com.kismiwati.application.domain.model.Movie
import kotlinx.coroutines.launch
import retrofit2.await
import java.lang.Exception

//pada bagian ini adalah kelas viewmodel
//dengan nama class MovieViewModel
class MovieViewModel(
    private val movieRepository: IMovieRepository
) : ViewModel() {
    private val _movie = MutableLiveData<MovieApiResult<List<Movie>>>()
    val movies: LiveData<MovieApiResult<List<Movie>>> = _movie


    fun getMoviesFromRetrofit() {
        // Coroutine yang akan dibatalkan saat ViewModel dihapus.
        viewModelScope.launch {
            _movie.value = MovieApiResult.Loading()
            //try digunakan untuk menentukkan bagian statement program
            // dimana akan terjadi pengecualian
            try {
                val movieApi = movieRepository.getMovies().await()
                _movie.value = MovieApiResult.Success(movieApi.results)
            }
            //catch digunakan untuk menangani kesalahan/pengecualian yang terjadi
            catch (e: Exception) {
                val movieResult = MovieApiResult.Error<List<Movie>>(e)
                _movie.value = movieResult
            }
        }
    }
}
class MovieViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieViewModel(repository) as T
    }
}
