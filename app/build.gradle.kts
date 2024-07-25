plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    // hilt
    alias(libs.plugins.dagger.hilt.android)

    //Para las anotaciones KSP
    alias(libs.plugins.ksp)

    // Para las anotaciones kotlin-kapt
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.example.imagegalleryapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.imagegalleryapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.fragment)

    //LiveData, ViewModel, navegacion
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)

    //room
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    //Dagger - Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    //Libreria
    implementation(libs.libreria.pcs)
}