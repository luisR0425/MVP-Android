package co.com.luisf0425.puntodos.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import co.com.luisf0425.puntodos.model.Post
import co.com.luisf0425.puntodos.model.PostDao

@Database(entities = [Post::class ], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun postDao(): PostDao
}