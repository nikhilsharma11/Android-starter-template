package com.nikhil.startertemplate.data.api

import com.nikhil.startertemplate.BuildConfig

object ProjectEndpoints {
    const val PREFIX = BuildConfig.API_PREFIX

    const val getSkins = "$PREFIX/api/premiumtour/me/skins"
}