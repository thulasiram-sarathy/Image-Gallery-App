package com.thul.imagegallery.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.thul.imagegallery.model.ImageData
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DateComparator {

    companion object : Comparator<ImageData> {

        @RequiresApi(Build.VERSION_CODES.O)
        override fun compare(a: ImageData, b: ImageData): Int  {
                var firstImage = a.date!!.dateFormat()
                var secondImage = b.date!!.dateFormat()
            return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                when {
                    firstImage.isBefore(secondImage) -> {
                        1
                    }
                    firstImage.isAfter(secondImage) -> {
                        -1
                    }
                    else -> {
                        0
                    }
                }
            } else {
                TODO("VERSION.SDK_INT < O")
            }

        }
    }
}