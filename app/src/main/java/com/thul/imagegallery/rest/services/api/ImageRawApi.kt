package com.thul.imagegallery.rest.services.api

import com.thul.imagegallery.model.ImageData


interface ImageRawApi {

    fun getGallery(): List<ImageData>

}
