# Fineract Client Kotlin Multiplatform

Fineract Client KMP is a Kotlin Multiplatform library built with Ktorfit that enables interaction with the Apache Fineract 1.x Platform. This library includes all necessary services and data payload models, along with a Ktorfit instance used by the [Fineract Client KMP SDK](https://github.com/openMF/fineract-client-kmp-sdk).

All services and payload models in this library are generated using the OpenAPI Generator. However, `FineractClient.kt` is not a generated file. it is responsible for creating the Ktorfit instance and serves as the entry point for accessing the services within the Fineract Client KMP SDK.

![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=flat-square&logo=kotlin&logoColor=white)
![Kotlin Multiplatform](https://img.shields.io/badge/Kotlin%20Multiplatform-4c8d3f?style=flat-square&logo=kotlin&logoColor=white)
![Open Source](https://img.shields.io/badge/Open%20Source-Yes-brightgreen)
![GSoC](https://img.shields.io/badge/GSoC-yellow)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com)
[![GitHub license](https://img.shields.io/badge/License-MPL_2.0-brightgreen.svg)](https://github.com/openMF/fineract-client-kmp/tree/main)
[![GitHub release](https://img.shields.io/badge/release-v1.0.2-blue)](https://github.com/openMF/fineract-client-kmp/releases/)
[![GitHub issues](https://img.shields.io/github/issues/Naereen/StrapDown.js.svg)](https://github.com/openMF/fineract-client-kmp/issues/)
[![Slack](https://img.shields.io/badge/Slack-4A154B?style=flat-square&logo=slack&logoColor=white)](https://join.slack.com/t/mifos/shared_invite/zt-2wvi9t82t-DuSBdqdQVOY9fsqsLjkKPA)

## Architecture

The library is organized into three main packages inside the fineract-client module:

- <b>models -</b> Contains all the payloads, including request and response models for various services.

- <b>apis -</b> Includes all service interfaces, such as ClientService, AuthService, etc.

- <b>infrastructure -</b> Contains the [FineractClient.kt](https://github.com/openMF/fineract-client-kmp/blob/main/fineract-client/src/commonMain/kotlin/org/mifos/fineract/client/infrastructure/FineractClient.kt) file, which initializes the Ktorfit instance and exposes all services to the Fineract Client KMP SDK.


## Code Generation
- Follow these steps to generate data models and services using OpenAPI Tools:

### Install OpenAPI Generator CLI
- Install NPM (Skip this step if you already have npm installed).
- Install OpenAPI Generator CLI using npm:
  ```cmd
    npm install @openapitools/openapi-generator-cli -g
  ```

### Download fineract.yml File
- Download the fineract.yml file from [Here](https://github.com/openMF/fineract-client-kmp/wiki/Download-fineract.yml-File) 

### Generation
- Once OpenAPI Generator CLI is installed, use the following command to generate the code:

    ```cmd
        openapi-generator-cli generate \
        -i "C:\Users\Aditya\Downloads\fineract.yaml" \
        -o "C:\Users\Aditya\Desktop\MIFOS\web-app\packages\fineract-client\src" \
        -g kotlin \
        --library=jvm-retrofit2 \
        --additional-properties=useCoroutines=true,returnPlainBody=true \
        --skip-validate-spec
    ```
- Replace `-i "C:\Users\Aditya\Downloads\fineract.yaml"` with the actual path to your fineract.yml file.

- Note: Back in 2024, when these data models and services were generated, Ktorfit support was not available. So, we generated them using Retrofit2 and later modified the services to use Ktorfit. In the future, before generating the code, check whether Ktorfit support is available.

## Quickstart Setup

### Installation

Add dependency in the `build.gradle`.

- Kts

  ```kts
    dependencies {
        implementation("com.github.openMF:fineract-client-kmp:$version")
    }
  ```

- Groovy
  ```groovy
    dependencies {
        implementation 'com.github.openMF:fineract-client-kmp:$version'
    }
  ```

Add this in your root `settings.gradle`.

- Kts

  ```Kotlin
    dependencyResolutionManagement {
        repositories {
            maven {
                setUrl("https://jitpack.io")
            }
        }
    }
  ```

- Groovy

  ```groovy
    allprojects {
        repositories {
            maven { url 'https://jitpack.io' }
        }
    }
  ```

## How to Contribute

Thank you for your interest in contributing to the Fineract Client KMP project by Mifos! We welcome all contributions and encourage you to follow these guidelines to ensure a smooth and efficient collaboration process.
To get started, please refer to the [Contribution Guide](https://github.com/openMF/fineract-client-kmp/wiki/How-to-Contribute) for detailed instructions on how to contribute to the project.

## Wiki

Please visit our [Wiki](https://github.com/openMF/fineract-client-kmp/wiki) page for a detailed overview of the project's architecture and guidelines. Explore further to gain a deeper understanding of our project.

## Contributors

Special thanks to the incredible code contributors who continue to drive this project forward.

<a href="https://github.com/openMF/fineract-client-kmp/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=openMF/fineract-client-kmp" />
</a>
