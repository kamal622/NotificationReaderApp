package com.kp.notificationreaderapp.apiCall.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kp.notificationreaderapp.apiCall.model.categoryList.CategoryList
import com.kp.notificationreaderapp.apiCall.model.getMovies.GetMovies
import com.kp.notificationreaderapp.apiCall.model.getMoviesVideo.GetMoviesVideo
import com.kp.notificationreaderapp.apiCall.model.getVideos.GetVideosData
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap
import java.util.concurrent.TimeUnit

interface DepaniniWorkerService {

    companion object {
        private const val BASE_URL = "https://api.indiatvshowz.com/v1/"
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(createClient())
                .build()

        private fun createClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val builder = OkHttpClient.Builder()
            builder.addInterceptor(interceptor)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
            return builder.build()
        }
    }

    @GET("categoryList.php?")
    fun categoryList(
            @QueryMap params: Map<String, String>
    ): Deferred<CategoryList>

    @GET("getMovies.php?")
    fun getMovies(
            @QueryMap params: Map<String, String>
    ): Deferred<GetMovies>

    @GET("getMoviesVideo.php?")
    fun getMoviesVideo(
            @QueryMap params: Map<String, String>
    ): Deferred<GetMoviesVideo>

    @GET("getVideos.php?")
    fun getVideos(
            @QueryMap params: Map<String, String>
    ): Deferred<GetVideosData>

}