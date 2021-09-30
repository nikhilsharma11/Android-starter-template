package com.nikhil.startertemplate.data.preferences

interface PreferencesContract {
    fun saveB2CPolicy(policyName: String)
    fun getB2CPolicy(): String?

    fun clearPreferences()
}