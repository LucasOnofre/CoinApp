plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.onoffrice.core"

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
    api(project(":domain"))

    implementation(ComposeDependencies.compose)
    implementation(ComposeDependencies.compose_activity)
    implementation(ComposeDependencies.compose_material)
    implementation(ComposeDependencies.compose_foundation)
    implementation(ComposeDependencies.compose_uitooling)
    implementation(ComposeDependencies.compose_toolingpreview)
    implementation(ComposeDependencies.compose_accompanist)

    implementation(DependencyInjectionDependencies.koin_compose)

    implementation(SupportDependencies.navigation)
    implementation(NetworkDependencies.gson)

    // Test rules and transitive dependencies:
    androidTestImplementation(TestDependencies.jUnitCompose)
    // Needed for createComposeRule, but not createAndroidComposeRule:
    debugImplementation(TestDependencies.testManifestCompose)
}
