package com.thul.imagegallery.viewmodel.home

import androidx.hilt.lifecycle.ViewModelInject
import com.framework.models.BaseViewModel
import com.thul.imagegallery.rest.services.api.ImageRawApi
import com.thul.imagegallery.utils.DateComparator

class HomeViewModel @ViewModelInject constructor(rawImageApi: ImageRawApi)  : BaseViewModel() {
    val imageGalleryList = rawImageApi.getGallery().sortedWith(DateComparator)

}