package com.kismiwati.application.data.Dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kismiwati.application.domain.model.Movie

//anotasi @database yang didalamnya terdapat alur entitas yang ada dalam database
//beserta version schemanya.
@Database(entities = [Movie::class], version = 1)
//abstract class merupakan turunan dari roomDatabase
// dengan nama database MovieDatabase
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}