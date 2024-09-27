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

subprojects {
    plugins.withId("com.vanniktech.maven.publish.base") {
        configure<MavenPublishBaseExtension> {
            publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL, automaticRelease = true)
            signAllPublications()

            pom {
                name = project.name
                description = desc
                inceptionYear = creationYear
                url = "https://github.com/$githubRepo"
                licenses {
                    license {
                        name = license
                        url = "https://github.com/openMF/fineract-client-cmp/blob/main/LICENSE"
                    }
                }
                developers {
                    developer {
                        id = "openMF"
                        name = "Mifos Initiative"
                        url = "https://github.com/openMF/"
                    }
                }
                scm {
                    url = "https://github.com/$githubRepo.git"
                    connection = "scm:git:git://github.com/$githubRepo.git"
                    developerConnection = "scm:git:git://github.com/$githubRepo.git"
                }
                issueManagement {
                    url = "https://github.com/$githubRepo/issues"
                }
            }
        }
    }
}