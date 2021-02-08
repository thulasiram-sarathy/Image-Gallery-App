package com.thul.imagegallery.utils

import com.thul.imagegallery.model.ImageData

interface OnImageClickListener {
    fun OnImageClick(imageData: ImageData, position :Int)
}