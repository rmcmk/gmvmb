plugins {
    `kotlin-dsl`
    alias(libs.plugins.gradle.publish)
    alias(libs.plugins.kotlinter)
}

group = "dev.rmcmk.gradle.gmvmb"
version = "1.0.0-RC1"

@Suppress("UnstableApiUsage") gradlePlugin {
    website = "https://rmcmk.dev/gmvmb"
    vcsUrl = "https://github.com/rmcmk/gmvmb.git"

    plugins {
        create("gmvmb") {
            displayName = "GmvMB - GradleModuleVersion Model Builder"
            description = """
				GmvMB is a Gradle plugin that includes a ModelBuilder of the GradleModuleVersion
                for extracting the Maven coordinates of a Gradle project and its children.
			""".trimIndent()
            id = "gmvmb"
            tags = setOf("tooling", "model builder", "gspm")
            implementationClass = "dev.rmcmk.gradle.gmvmb.GmvmbPlugin"
        }
    }
}
