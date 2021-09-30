package com.nikhil.startertemplate.common.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.HttpException

@JsonClass(generateAdapter = true)
data class ProjectError(
    @Json(name = "errors")
    val errors: ErrorProps?,

    @Json(name = "type")
    val type: String,

    @Json(name = "title")
    val title: String,

    @Json(name = "status")
    val status: Int,

    @Json(name = "detail")
    val detail: String?,

    @Json(name = "instance")
    val instance: String?
) {

    data class ErrorProps(
        @Json(name = "additionalProp1")
        val additionalProp1: List<String>,

        @Json(name = "additionalProp2")
        val additionalProp2: List<String>,

        @Json(name = "additionalProp3")
        val additionalProp3: List<String>
    )

    companion object {
        @JvmStatic
        fun fromError(projectErrorJsonAdapter: ProjectErrorJsonAdapter, e: Throwable): ProjectError? {
            return if (e is HttpException) {
                try {
                    projectErrorJsonAdapter.fromJson(e.response()?.errorBody()?.string() ?: "")
                } catch (e: Throwable) {
                    null
                }
            } else {
                null
            }
        }
    }

}