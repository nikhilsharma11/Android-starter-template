package com.nikhil.startertemplate.data

import com.nikhil.startertemplate.data.api.ApiContract
import com.nikhil.startertemplate.data.preferences.PreferencesContract

interface DataRepositoryContract: ApiContract, PreferencesContract {

    suspend fun getAccessToken(): String?
}