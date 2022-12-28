package com.kismiwati.application.data.model

sealed class MovieApiResult<T> {
    //pada bagian ini digunakan untuk meneruskan objek dari kelas loading
    class Loading<T> : MovieApiResult<T>()
    //bagian ini digunakan untuk membungkus data di kelas 'success'
    class Success<T>(val data: T) : MovieApiResult<T>()
    //bagian ini digunakan untuk meneruskan pesan kesalahan yang terbungkus dalam kelas 'Error'
    class Error<T>(val throwable: Throwable) : MovieApiResult<T>()
}
