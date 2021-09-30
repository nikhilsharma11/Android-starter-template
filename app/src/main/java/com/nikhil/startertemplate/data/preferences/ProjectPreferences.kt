package com.nikhil.startertemplate.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class ProjectPreferences (
    appContext: Context
) : PreferencesContract {

    private val prefs: SharedPreferences = EncryptedSharedPreferences.create(
        PREFS_NAME,
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        appContext,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )



    companion object {
        private const val PREFS_NAME = "ProjectPreferences"
    }

    override fun saveB2CPolicy(policyName: String) {
        TODO("Not yet implemented")
    }

    override fun getB2CPolicy(): String? {
        TODO("Not yet implemented")
    }

    override fun clearPreferences() {
        TODO("Not yet implemented")
    }

}