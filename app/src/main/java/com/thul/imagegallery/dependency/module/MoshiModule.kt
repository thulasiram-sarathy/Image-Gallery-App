package com.thul.obvious.injection

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.thul.imagegallery.model.ImageData
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object moshiImageModule {
  @Provides
  fun providesMoshiBuild(): Moshi {
    return Moshi.Builder().build()
  }

  @Provides
  fun provideJsonAdapterImage(moshiObj: Lazy<Moshi>): JsonAdapter<List<ImageData>> {
    val images = Types.newParameterizedType(List::class.java, ImageData::class.java)
    return moshiObj.get().adapter<List<ImageData>>(images)
  }
}
