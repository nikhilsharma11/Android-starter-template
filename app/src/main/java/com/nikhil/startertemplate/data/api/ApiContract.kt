package com.nikhil.startertemplate.data.api

interface ApiContract {
    suspend fun getSkins(authToken: String): Any
}