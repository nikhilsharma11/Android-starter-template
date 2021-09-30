package com.nikhil.startertemplate.data.api

class ProjectAPI(
    private val apiService: ApiService
) : ApiContract {
    private fun String.formatAuthToken(): String = "Bearer $this"

    override suspend fun getSkins(authToken: String): Any {
        return apiService.getSkins(authToken.formatAuthToken())
    }

}