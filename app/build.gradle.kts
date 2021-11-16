plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}


android {
    compileSdkVersion(BuildVersions.compileSdkVersion)
    buildToolsVersion(BuildVersions.build_tools)

    defaultConfig {
        applicationId = BuildVersions.application_id
        minSdkVersion(BuildVersions.minSdkVersion)
        targetSdkVersion(BuildVersions.targetSdkVersion)
        versionCode = BuildVersions.versionCode
        versionName = BuildVersions.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
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
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    //android ui

    implementation(Dependencies.supportV4)
    implementation(Dependencies.constraintLayout)
    implementation(Dependencies.material)
    implementation(Dependencies.swipeRefresh)
    implementation(Dependencies.viewPager2)
    implementation(Dependencies.flexBoxLayoutManager)


    implementation(Dependencies.autoSize)

    implementation(Dependencies.uiStatus)
    implementation(Dependencies.dialogCore)
    implementation(Dependencies.dialogLifecycle)


    implementation(Dependencies.navigation)
    implementation(Dependencies.navigationFragment)

    //room
    implementation(Dependencies.room)
    kapt(Dependencies.roomCompiler)
    implementation(Dependencies.roomKtx)

    //koin
    implementation(Dependencies.koin_scop)
    implementation(Dependencies.koin_viewmodel)
    implementation(Dependencies.koin_ext)


    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.android_junit)
    androidTestImplementation(Dependencies.espresso)

    //第三方库
    implementation(Dependencies.brvah)
    implementation(Dependencies.banner)

    //图片加载
    implementation(Dependencies.coil)

    implementation(Dependencies.agentWeb)

    implementation(Dependencies.noDrawable)

    implementation(Dependencies.verticalTabLayout)

    implementation(Dependencies.mmkv)

//    implementation (Dependencies.moshiKtx)


    //module
    implementation(project(":mvvm-core"))

    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.6")
}
