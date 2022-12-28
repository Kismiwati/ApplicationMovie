package com.kismiwati.application.presenter.viewmodel

import androidx.lifecycle.*
import com.kismiwati.application.data.repositorys.IMovieRepository
import com.kismiwati.application.data.repositorys.MovieRepository
import com.kismiwati.application.domain.model.MovieDetails
import kotlinx.coroutines.launch
import retrofit2.await

//pada bagian ini adalah kelas viewmodel
//dengan nama class MovieDetailsViewModel
//variavel movieRepository merupakan variabel anggota
class MovieDetailsViewModel(private val movieRepository: IMovieRepository) : ViewModel() {
    private val _movieDetails = MutableLiveData<MovieDetails>()
    val moviesDetails: LiveData<MovieDetails> = _movieDetails

    fun getMoviesDetailsFromRetrofit(movieId: Int) {

        viewModelScope.launch {
            val details = movieRepository.getDetailsMovies(movieId).await()
            _movieDetails.value = details
        }
    }
}
//pada bagian ini adalah kelas viewmodel
//dengan nama class MovieDetailsViewModelFactory
class MovieDetailsViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieDetailsViewModel(repository) as T
    }
}
