plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    namespace = "com.onoffrice.exchange"

    defaultConfig {
        compileSdk = Config.compileSdkVersion
        minSdk = Config.minSdkVersion
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    packagingOptions {
        resources.excludes.add("META-INF/*")
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = ComposeVersions.compose_compiler_version
    }
}

dependencies {
    implementation(project(":core"))

    implementation(SupportDependencies.core)
    implementation(SupportDependencies.material)
    implementation(SupportDependencies.app_compat)
    implementation(SupportDependencies.navigation)

    implementation(ComposeDependencies.compose_activity)
    implementation(ComposeDependencies.compose_uitooling)
    implementation(ComposeDependencies.compose_toolingpreview)
    implementation(ComposeDependencies.compose_material)
    implementation(ComposeDependencies.compose_accompanist)

    implementation(DependencyInjectionDependencies.koin_compose)

    implementation(AsyncDependencies.coroutines)
    implementation(AsyncDependencies.coroutines_android)

    testImplementation(TestDependencies.junit)
    testImplementation(TestDependencies.mockk)
    testImplementation(TestDependencies.coroutines_test)
    testImplementation(TestDependencies.arch)
    testImplementation(TestDependencies.turbine)
    testImplementation(TestDependencies.mockito)
    testImplementation(TestDependencies.truth)
}
