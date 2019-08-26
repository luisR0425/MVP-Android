package co.com.luisf0425.puntodos.model

data class Post(val id: Int, val userId: Int, val title: String, val body: String, var isRead: Boolean = false)