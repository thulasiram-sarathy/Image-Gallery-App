package com.thul.imagegallery

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.test.platform.app.InstrumentationRegistry
import com.squareup.moshi.JsonAdapter
import com.thul.imagegallery.model.ImageData
import com.thul.imagegallery.utils.DateComparator
import com.thul.imagegallery.utils.dateFormat
import com.thul.obvious.injection.moshiImageModule
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.InputStream
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ImagesListTest {

  fun String.dateFormatTest() : LocalDate {
    return LocalDate.parse(this, DateTimeFormatter.ofPattern("dd-MM-yyyy"))
  }

  private val imageOne = ImageData(
          copyright = "ESA/HubbleNASA",
          date = "2019-12-01",
          explanation = "Why does this galaxy have a ring of bright blue stars?",
          hdurl = "https://apod.nasa.gov/apod/image/1912/M94_Hubble_1002.jpg",
          media_type = "image",
          service_version = "v1",
          title = "Starburst Galaxy M94 from Hubble",
          url = "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg",
  )

  private val imageOneDup = ImageData(
          copyright = "ESA/HubbleNASA",
          date = "2019-12-01",
          explanation = "Why does this galaxy have a ring of bright blue stars?",
          hdurl = "https://apod.nasa.gov/apod/image/1912/M94_Hubble_1002.jpg",
          media_type = "image",
          service_version = "v1",
          title = "Starburst Galaxy M94 from Hubble",
          url = "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg",
  )

  private val imageTwo = ImageData(
          copyright = "Steve Mazlin",
          date = "2019-12-03",
          explanation = "Is this what will become of our Sun? Quite possibly",
          hdurl = "https://apod.nasa.gov/apod/image/1912/M27_Mazlin_2000.jpg",
          media_type = "image",
          service_version = "v1",
          title = "M27: The Dumbbell Nebula",
          url = "https://apod.nasa.gov/apod/image/1912/M27_Mazlin_960.jpg",
  )

  private val imageThree = ImageData(
          date = "03-12-2019",
          explanation = "It may appear, at first, like the Galaxy is producing the lightning",
          hdurl = "https://apod.nasa.gov/apod/image/1912/M27_Mazlin_2000.jpg",
          media_type = "image",
          service_version = "v1",
          title = "Electric Night",
          url = "https://apod.nasa.gov/apod/image/1912/M27_Mazlin_960.jpg",
  )

  @Test
  fun compareImageListToCheckDuplicate() {
    assertEquals(imageOne, imageOneDup)
  }

  @Test
  fun checkIfImageIsNotSame() {
    assertEquals(0, DateComparator.compare(imageOne, imageTwo))
  }

  @Test
  fun copyRightDuplicate() {
    assertEquals(imageOne.copyright,imageTwo.copyright)
  }

  @Test
  fun checkDateFormatIsSimilar() {
    assertEquals(imageOne.date!!.dateFormat(), imageThree.date!!.dateFormatTest())
  }

  @Test
  fun copyRightIsNull() {
    assertEquals(imageOne.copyright,imageThree.copyright)
  }

}
