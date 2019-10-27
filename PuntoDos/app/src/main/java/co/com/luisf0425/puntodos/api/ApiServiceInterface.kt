package co.com.luisf0425.puntodos.api

import co.com.luisf0425.puntodos.model.Post
import co.com.luisf0425.puntodos.model.User
import co.com.luisf0425.puntodos.util.Constants
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

//Retrofit Api
interface ApiServiceInterface {

    @GET("users")
    fun getUser(): Observable<List<User>>

    @GET("posts")
    fun getPostList(): Observable<List<Post>>

    companion object Factory { //Create instance of retrofit
        fun create(): ApiServiceInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}