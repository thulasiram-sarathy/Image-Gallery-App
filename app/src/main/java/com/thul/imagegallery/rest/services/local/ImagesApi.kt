package com.thul.imagegallery.rest.services.local

import android.content.Context

import com.squareup.moshi.JsonAdapter
import com.thul.imagegallery.R
import com.thul.imagegallery.model.ImageData
import com.thul.imagegallery.rest.services.api.ImageRawApi
import com.thul.obvious.rest.services.local.ImageLocalDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ImagesApi @Inject constructor(@ApplicationContext private val context: Context,
    private val imageJsonDataAdapter: JsonAdapter<List<ImageData>>,
) : ImageRawApi {

    override fun getGallery(): List<ImageData> {

        val imageDataContent = ImageLocalDataSource.fromJsonRes(context, R.raw.data)
        val imageList = imageDataContent.readText()
        return imageJsonDataAdapter.fromJson(imageList) ?:
        error("error opening data")

    }
}
