plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.kese"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.kese"
        minSdk = 24
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
    buildFeatures{
        viewBinding = true
    }
    compileOptions {

        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("io.github.h07000223:flycoTabLayout:3.0.0")
    implementation("com.squareup.okhttp3:okhttp:3.10.0")
    implementation("androidx.recyclerview:recyclerview-selection:1.1.0")
    implementation(kotlin("script-runtime"))
    implementation("com.belerweb:pinyin4j:2.5.1")

}