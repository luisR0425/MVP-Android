package co.com.luisf0425.puntodos.model

import androidx.room.*

@Dao
interface PostDao {
    @get:Query("SELECT * FROM post")
    val all: List<Post>

    @Update
    fun updatePost(vararg post: Post)

    @Delete
    fun deletePost(vararg post: Post)

    @Insert
    fun insertAll(vararg post: Post)
}