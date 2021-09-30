package com.nikhil.startertemplate.data

import com.nikhil.startertemplate.data.api.ApiContract
import com.nikhil.startertemplate.data.preferences.PreferencesContract

class ProjectDataRepository (
    private val api: ApiContract,
    private val prefs: PreferencesContract
) : ApiContract by api,
    PreferencesContract by prefs,
    DataRepositoryContract {

    override suspend fun getAccessToken(): String? {
        TODO("Not yet implemented")
    }

}