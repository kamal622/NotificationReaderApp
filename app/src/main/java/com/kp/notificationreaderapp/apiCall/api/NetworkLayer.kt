package com.kp.notificationreaderapp.apiCall.api

class NetworkLayer{

    private val webService: DepaniniWorkerService = DepaniniWorkerService.retrofit
            .create(DepaniniWorkerService::class.java)

    suspend fun getCalList(params :HashMap<String, String>) =
            webService.categoryList(params).await()


    suspend fun getMovieData(params :HashMap<String, String>) =
            webService.getMovies(params).await()

    suspend fun getMoviesVideo(params :HashMap<String, String>) =
            webService.getMoviesVideo(params).await()

    suspend fun getVideos(params :HashMap<String, String>) =
            webService.getVideos(params).await()

}