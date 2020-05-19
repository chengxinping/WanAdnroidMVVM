object Versions {
    const val kotlin = "1.3.72"
    const val gradle_tools = "3.6.3"

    const val appcompat = "1.1.0"

    const val lifecycle = "2.2.0"

    const val retrofit = "2.8.1"
    const val okHttp = "4.7.0"

    const val koin_version = "2.1.5"


}

object BuildVersions {
    const val compileSdkVersion = 29
    const val minSdkVersion = 21
    const val targetSdkVersion = 29
    const val versionCode = 1
    const val versionName = "1.0.0"
    const val build_tools = "29.0.3"
    const val application_id = "cn.xpcheng.wanadnroidmvvm"
}

object BuildPlugins {
    const val androidGradle = "com.android.tools.build:gradle:${Versions.gradle_tools}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val koinGradle = "org.koin:koin-gradle-plugin:${Versions.koin_version}"
}


object Dependencies {
    const val kotlinLibrary = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val ktxCore = "androidx.core:core-ktx:1.2.0"

    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"

    const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

    const val koin_scop = "org.koin:koin-androidx-scope:${Versions.koin_version}"
    const val koin_viewmodel = "org.koin:koin-androidx-viewmodel:${Versions.koin_version}"
    const val koin_ext="org.koin:koin-androidx-ext:${Versions.koin_version}"

    const val junit = "junit:junit:4.12"
    const val espresso = "androidx.test.espresso:espresso-core:3.2.0"
    const val android_junit = "androidx.test.ext:junit:1.1.1"

}