plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "dam.pmdm.vega_ortega_alejandro_pmdm3"
    compileSdk = 34

    defaultConfig {
        applicationId = "dam.pmdm.vega_ortega_alejandro_pmdm3"
        minSdk = 30
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
}

dependencies {
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.firestore)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.retrofit)
    implementation(libs.recyclerview)
    implementation(platform(libs.firebase.bom)) // Firebase BOM
    implementation(libs.firebase.auth)
    implementation(libs.firebase.database)

}