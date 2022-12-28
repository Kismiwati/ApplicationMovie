package com.kismiwati.application.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity(tableName = "Movie")

//bagain ini adalah Class Movie yang digunakan untuk memparsing reponse json yang diterima dari request api
data class Movie(
    @PrimaryKey()
    val adult: Boolean,
    val backdrop_path: String,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    val media_type: String,
) : Serializable
