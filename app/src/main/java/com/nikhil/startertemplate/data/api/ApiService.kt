package com.nikhil.startertemplate.data.api

import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {

    @GET(ProjectEndpoints.getSkins)
    suspend fun getSkins(@Header("Authorization") authToken: String): Any
}