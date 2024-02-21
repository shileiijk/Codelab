plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.shilei.ijk.common"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation(Deps.core_ktx)
    implementation(Deps.appcompat)
    implementation(Deps.material)
    implementation("androidx.databinding:viewbinding:8.2.1")
    implementation("androidx.databinding:databinding-runtime:8.4.2")
    testImplementation(Deps.junit)
    androidTestImplementation(Deps.ext_junit)
    androidTestImplementation(Deps.espresso)

    implementation(Deps.timber)
}