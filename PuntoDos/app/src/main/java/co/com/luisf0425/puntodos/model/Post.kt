package co.com.luisf0425.puntodos.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(
    val userId: Int,
    @field:PrimaryKey //primaryKey that help in delete and update
    val id: Int,
    val title: String,
    val body: String,
    var isRead: Boolean = false)