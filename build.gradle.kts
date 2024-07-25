// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false

    // hilt
    alias(libs.plugins.dagger.hilt.android) apply false

    //Para las anotaciones KSP
    alias(libs.plugins.ksp) apply false

    // Para las anotaciones kotlin-kapt
    alias(libs.plugins.kotlin.kapt) apply false
}