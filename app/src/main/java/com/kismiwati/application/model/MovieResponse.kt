package com.kismiwati.application.domain.model

//Pada bagian ini adalah model untuk memparsing response json yang diterima dari request api
data class MovieResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)
