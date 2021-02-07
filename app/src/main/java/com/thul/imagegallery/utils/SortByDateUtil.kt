package com.thul.imagegallery.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun String.dateFormat() : LocalDate {
    return LocalDate.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
}