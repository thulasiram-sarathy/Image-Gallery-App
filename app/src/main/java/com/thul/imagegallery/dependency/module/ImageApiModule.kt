package com.thul.obvious.injection


import com.thul.imagegallery.rest.services.api.ImageRawApi
import com.thul.imagegallery.rest.services.local.ImagesApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class ImageApiModule {
    @Binds
    abstract fun bindImageApi(imagesApi: ImagesApi): ImageRawApi
}
