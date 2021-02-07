package com.thul.imagegallery.model

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class ImageData(
    var copyright: String? = null,
    var date: String? = null,
    var explanation: String? = null,
    var hdurl: String? = null,
    var media_type: String? = null,
    var service_version: String? = null,
    var title: String? = null,
    var url: String? = null
) : Serializable {
}
