package com.kismiwati.application.data.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.kismiwati.application.domain.model.Movie

@Dao
//bagaian ini adalah sebuah interface yang digunakan untuk mendefinisikan method-method untuk memanipulasi database dan kelas implementasinya.
interface MovieDao {

    //anotasi @insert digunakan untuk menentukan metode yang menyisipkan parameter kedalam tabel sesuai dengan databse.
    @Insert
    fun save(movie: Movie)

    //anotasi @delete digunakan untuk menentukan metode yang menghapus baris tertentu dari tabel database
    //motode @delete menerima instance entity data sebagai parameter
    @Delete
    fun delete(movieId: Movie)

    //pada bagian ini digunakan untuk menentukan metode yang menampilkan semua movie berdasarkan id tertentu.
    @Query("SELECT * FROM Movie WHERE id = :id")
    fun load(id: Int): Movie

    //pada bagian ini digunakan untuk menentukan metode yang menggunakan kueri SElECT untuk menampilkan semua objek movie dalam database.
    @Query("SELECT * FROM Movie")
    fun getInvited(): List<Movie>
}