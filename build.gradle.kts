import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.dokka.gradle.DokkaTaskPartial

plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.binary.compatibility.validator)
    alias(libs.plugins.kover) apply false
    alias(libs.plugins.maven) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.dokka)
}

// Maven publishing configuration
val mavenGroup: String by project
val defaultVersion: String by project
val currentVersion = System.getenv("PACKAGE_VERSION") ?: defaultVersion
val desc: String by project
val license: String by project
val creationYear: String by project
val githubRepo: String by project

val developerName: String by project
val developerId: String by project
val developerUrl: String by project

allprojects {
    group = mavenGroup
    version = currentVersion

    repositories {
        mavenCentral()
    }
}

apiValidation {
    @OptIn(kotlinx.validation.ExperimentalBCVApi::class)
    klib {
        enabled = true
    }
    nonPublicMarkers.add("kotlin.PublishedApi")
}

// dokka and version configuration.
afterEvaluate {
    tasks.withType<DokkaTaskPartial>().configureEach {
        dokkaSourceSets.configureEach {
            jdkVersion = 17
            failOnWarning = true
            skipDeprecated = true
            suppressInheritedMembers = true
        }
    }
}

allprojects {
    tasks.withType<org.jetbrains.dokka.gradle.DokkaTask>().configureEach {
        outputDirectory.set(file("$rootDir/docs/kdoc"))
    }
}

subprojects {
    plugins.withId("com.vanniktech.maven.publish.base") {
        configure<MavenPublishBaseExtension> {
            publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL, automaticRelease = true)
            signAllPublications()

            pom {
                name.set(project.name)
                description.set(desc)
                inceptionYear.set(creationYear)
                url.set("https://github.com/$githubRepo")
                licenses {
                    license {
                        name.set(license)
                        url.set("https://github.com/$githubRepo/blob/main/LICENSE")
                        distribution.set("https://github.com/$githubRepo/blob/main/LICENSE")
                    }
                }
                developers {
                    developer {
                        id.set(developerId)
                        name.set(developerName)
                        url.set(developerUrl)
                    }
                }
                scm {
                    url.set("https://github.com/$githubRepo.git")
                    connection.set("scm:git:git://github.com/$githubRepo.git")
                    developerConnection.set("scm:git:git://github.com/$githubRepo.git")
                }
            }
        }
    }
}