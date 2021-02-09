# Image-Gallery-App
"Image-Gallery-App" is an android application that display images and the information for that. This app has the potential to be a gallery application for viewing pictures.
Image Gallery app is developed using Kotlin and has multiple libraries and architecture such as MVVM, Hilt, Jetpack Navigation, Moshi and few other dependencies. <br><br>

<img src="https://github.com/thulasiram-sarathy/Image-Gallery-App/blob/main/Screenshots/1.png" width="250" style="max-width:100%;"> <img src="https://github.com/thulasiram-sarathy/Image-Gallery-App/blob/main/Screenshots/2.png" width="250" style="max-width:100%;"> 
<img src="https://github.com/thulasiram-sarathy/Image-Gallery-App/blob/main/Screenshots/3.png" width="250" style="max-width:100%;"> 
<img src="https://github.com/thulasiram-sarathy/Image-Gallery-App/blob/main/Screenshots/demo_animation.gif" width="250" style="max-width:100%;">  
</br></br>

## Download link

<a href="https://github.com/thulasiram-sarathy/Image-Gallery-App/blob/main/app/release/app-release.apk">Image Gallery App</a>

## Getting started

### Setting up the dependency

First step is to setup the project <b> build.gradle </b> file.
```
// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    ext.kotlin_version = "1.4.21"
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath "com.android.tools.build:gradle:4.1.1"
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
        classpath "com.google.dagger:hilt-android-gradle-plugin:2.28-alpha"
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}
ext {
    mobius_version = "1.5.3"
    dagger_hilt = "2.28-alpha"
    androidx_dagger_hilt = "1.0.0-alpha01"
}
```
</br>

Now the app <b> build.gradle file. </b> </br>

```
plugins {
    id 'com.android.application'
    id 'kotlin-android'
    id 'kotlin-kapt'
    id 'dagger.hilt.android.plugin'
    id 'kotlin-android-extensions'
}

android {
    compileSdkVersion 30
    buildToolsVersion "30.0.0"

    defaultConfig {
        applicationId "com.thul.imagegallery"
        minSdkVersion 26
        targetSdkVersion 30
        versionCode 1
        versionName "1.0"
        multiDexEnabled true
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = '1.8'
    }
    dataBinding {
        enabled = true
    }
    testOptions {
        unitTests {
            includeAndroidResources = true
        }
    }
}

dependencies {

    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    implementation 'androidx.core:core-ktx:1.3.2'
    implementation "androidx.fragment:fragment-ktx:1.2.2"
    implementation 'androidx.cardview:cardview:1.0.0-rc02'
    implementation 'androidx.recyclerview:recyclerview:1.0.0-rc02'
    implementation "androidx.activity:activity-ktx:1.1.0"
    implementation 'androidx.appcompat:appcompat:1.2.0'
    implementation 'com.google.android.material:material:1.2.1'
    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
    implementation "com.google.code.gson:gson:2.8.6"
    implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'
    implementation 'androidx.navigation:navigation-fragment-ktx:2.3.3'
    implementation 'androidx.navigation:navigation-ui-ktx:2.3.3'
    kapt 'com.squareup.moshi:moshi-kotlin-codegen:1.9.1'

    implementation 'com.sothree.slidinguppanel:library:3.4.0'

    implementation("com.squareup.moshi:moshi:1.11.0")

    // Dagger Hilt
    implementation "androidx.hilt:hilt-lifecycle-viewmodel:$androidx_dagger_hilt"
    kapt "androidx.hilt:hilt-compiler:$androidx_dagger_hilt"
    implementation "com.google.dagger:hilt-android:$dagger_hilt"
    kapt "com.google.dagger:hilt-android-compiler:$dagger_hilt"
    androidTestImplementation  "com.google.dagger:hilt-android-testing:$dagger_hilt"
    kaptAndroidTest "com.google.dagger:hilt-android-compiler:$dagger_hilt"
    testImplementation "com.google.dagger:hilt-android-testing:$dagger_hilt"
    kaptTest "com.google.dagger:hilt-android-compiler:$dagger_hilt"


    // ViewModel and LiveData
    def lifecycle_version = "2.2.0"
    def arch_version = "2.1.0"
    testImplementation "androidx.arch.core:core-testing:$arch_version"
    androidTestImplementation "androidx.arch.core:core-testing:$arch_version"

    implementation 'com.squareup.picasso:picasso:2.71828'
    annotationProcessor 'com.contentful.vault:compiler:3.2.1'
    annotationProcessor 'com.contentful.vault:core:3.2.1'

    implementation 'com.contentful.vault:core:3.2.1'

   
    testImplementation 'junit:junit:4.+'
    testImplementation "com.spotify.mobius:mobius-test:$mobius_version"
    androidTestImplementation 'androidx.test:runner:1.2.0'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'

}
```
 </br></br>
 
### Read Json from resource folder.

<b> BaseImageLocalService.kt </b>
</br>

```
open class BaseImageLocalService {

    fun  fromJsonRes(context: Context, @RawRes id: Int): BufferedReader {
        return getLocalJsonReader(context, id)
    }

    private fun getLocalJsonReader(context: Context, @RawRes id: Int): BufferedReader {
        return BufferedReader(InputStreamReader(context.resources.openRawResource(id)))
    }

}
```
<br>

### Sorting images by date.
<b>DateComparator.kt </b></br>
```
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
```
</br></br>

### Convert Json
<b>ImagesApi.kt </b></br>
```
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
```
</br></br>

### Bugs
```
1. Scrolling issue when Image detail is viewed while swiping up
```
</br></br>

### Todo
```
1. Pictures can be bookmarked or added to favorites
2. Pictures can be sectioned based on date or author
```

</br></br>

The Image Gallery App is still in its infancy and further implementations are pipelined for the future.
</br></br>