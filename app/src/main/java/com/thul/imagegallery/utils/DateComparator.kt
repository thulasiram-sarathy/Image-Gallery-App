package com.thul.imagegallery.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.thul.imagegallery.model.ImageData
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DateComparator {

    companion object : Comparator<ImageData> {
        override fun compare(a: ImageData, b: ImageData): Int  {
                var firstImage = a.date!!.dateFormat()
                var secondImage = b.date!!.dateFormat()
            return when {
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

        }
    }
}