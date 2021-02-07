package com.framework.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.thul.imagegallery.model.ImageData
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable

open class BaseViewModel : ViewModel()

fun Observable<ImageData>.toLiveData(strategy: BackpressureStrategy = BackpressureStrategy.BUFFER): LiveData<ImageData> {
  return LiveDataReactiveStreams.fromPublisher(this.toFlowable(strategy))
}