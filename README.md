# Fineract API Client Library ![Maven Central](https://img.shields.io/maven-central/v/io.github.niyajali/fineract-client-kmp) [![License: MPL 2.0](https://img.shields.io/badge/License-MPL%202.0-brightgreen.svg)](https://opensource.org/licenses/MPL-2.0)

## Overview

The `FineractClient` is a Kotlin client library for interacting with Apache Fineract's REST APIs. It provides a
comprehensive wrapper around Ktorfit for making HTTP requests to Fineract servers and accessing various financial
services endpoints.

## Add Dependency

### Gradle

To use the library in your Gradle project, follow the steps below:

```
implementation("io.github.niyajali:fineract-client-kmp:1.0.6")
```

## Architecture

The client follows a **Builder pattern** for configuration and provides type-safe access to Fineract's API endpoints
through generated interfaces.

## Core Components

### FineractClient Class

```kotlin
class FineractClient private constructor(
    private val ktorfit: Ktorfit,
) {
    // API endpoint accessors
    val accountingClosures = ktorfit.createAccountingClosureApi()
    val accountingRules = ktorfit.createAccountingRulesApi()
    val accountNumberFormats = ktorfit.createAccountNumberFormatApi()
    val accountTransfers = ktorfit.createAccountTransfersApi()

    // Utility methods
    fun baseURL(): String
    fun httpClient(): HttpClient
}
```

#### Available API Endpoints

- **`accountingClosures`** - Manage accounting period closures
- **`accountingRules`** - Handle accounting rules and configurations
- **`accountNumberFormats`** - Manage account number formatting rules
- **`accountTransfers`** - Process account-to-account transfers

#### Utility Methods

- **`baseURL()`** - Returns the configured base URL for the Fineract server
- **`httpClient()`** - Provides access to the underlying HttpClient for advanced customizations

### Builder Class

The Builder class provides a fluent API for configuring the FineractClient:

```kotlin
class Builder internal constructor() {
    fun baseURL(baseURL: String): Builder
    fun tenant(tenant: String?): Builder
    fun httpClient(httpClient: HttpClient): Builder
    @Deprecated
    fun inSecure(insecure: Boolean): Builder
    fun build(): FineractClient
}
```

## Usage Examples

### Basic Client Setup

```kotlin
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

// Create HTTP client with authentication
val httpClient = HttpClient(CIO) {
    install(ContentNegotiation) {
        json()
    }
    install(Auth) {
        basic {
            credentials {
                BasicAuthCredentials(username = "admin", password = "password")
            }
        }
    }
}

// Build the Fineract client
val fineractClient = FineractClient.builder()
    .baseURL("https://your-fineract-server.com/fineract-provider/api/v1")
    .tenant("default")
    .httpClient(httpClient)
    .build()
```

### Advanced HTTP Client Configuration

```kotlin
val customHttpClient = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            isLenient = true
        })
    }

    install(Auth) {
        bearer {
            loadTokens {
                BearerTokens("your-access-token", "your-refresh-token")
            }
        }
    }

    install(Logging) {
        level = LogLevel.INFO
    }

    // Custom timeout settings
    install(HttpTimeout) {
        requestTimeoutMillis = 30000
        connectTimeoutMillis = 10000
    }
}

val client = FineractClient.builder()
    .baseURL("https://your-fineract-server.com/fineract-provider/api/v1")
    .tenant("production")
    .httpClient(customHttpClient)
    .build()
```

### Using API Endpoints

```kotlin
// Example: Working with accounting closures
suspend fun manageAccountingClosures(client: FineractClient) {
    try {
        // Get all accounting closures
        val closures = client.accountingClosures.getAllClosures()
        println("Found ${closures.size} accounting closures")

        // Create a new accounting closure
        val newClosure = CreateAccountingClosureRequest(
            officeId = 1,
            closingDate = LocalDate.now().format(DateTimeFormatter.ofPattern(FineractClient.DATE_FORMAT)),
            comments = "Monthly closure"
        )

        val createdClosure = client.accountingClosures.createClosure(newClosure)
        println("Created closure with ID: ${createdClosure.resourceId}")

    } catch (e: Exception) {
        println("Error managing accounting closures: ${e.message}")
    }
}

// Example: Working with account transfers
suspend fun processAccountTransfer(client: FineractClient) {
    try {
        val transferRequest = AccountTransferRequest(
            fromAccountId = 123,
            toAccountId = 456,
            transferAmount = 1000.00,
            transferDate = LocalDate.now().format(DateTimeFormatter.ofPattern(FineractClient.DATE_FORMAT)),
            transferDescription = "Monthly payment"
        )

        val transfer = client.accountTransfers.create(transferRequest)
        println("Transfer completed: ${transfer.resourceId}")

    } catch (e: Exception) {
        println("Transfer failed: ${e.message}")
    }
}
```

### Date Formatting

The client provides a constant for consistent date formatting across API calls:

```kotlin
import java.time.LocalDate
import java.time.format.DateTimeFormatter

val currentDate = LocalDate.now()
val formattedDate = currentDate.format(DateTimeFormatter.ofPattern(FineractClient.DATE_FORMAT))
// Result: "2025-05-31"

// Use in API requests
val request = SomeApiRequest(
    date = formattedDate,
    // other fields...
)
```

### Error Handling Best Practices

```kotlin
suspend fun robustApiCall(client: FineractClient) {
    try {
        val result = client.accountingRules.getAllRules()
        // Process successful result
        result.forEach { rule ->
            println("Rule: ${rule.name} - ${rule.description}")
        }

    } catch (e: ClientRequestException) {
        // Handle 4xx errors (client errors)
        println("Client error (${e.response.status}): ${e.message}")

    } catch (e: ServerResponseException) {
        // Handle 5xx errors (server errors)
        println("Server error (${e.response.status}): ${e.message}")

    } catch (e: Exception) {
        // Handle other network/parsing errors
        println("Unexpected error: ${e.message}")
    }
}
```

### Multi-tenant Configuration

```kotlin
class FineractClientManager {
    private val clients = mutableMapOf<String, FineractClient>()

    fun getClientForTenant(tenant: String): FineractClient {
        return clients.getOrPut(tenant) {
            FineractClient.builder()
                .baseURL("https://your-fineract-server.com/fineract-provider/api/v1")
                .tenant(tenant)
                .httpClient(createHttpClientForTenant(tenant))
                .build()
        }
    }

    private fun createHttpClientForTenant(tenant: String): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) { json() }
            install(Auth) {
                basic {
                    credentials {
                        // Load tenant-specific credentials
                        BasicAuthCredentials(
                            username = loadUsernameForTenant(tenant),
                            password = loadPasswordForTenant(tenant)
                        )
                    }
                }
            }
        }
    }
}

// Usage
val clientManager = FineractClientManager()
val productionClient = clientManager.getClientForTenant("production")
val stagingClient = clientManager.getClientForTenant("staging")
```

## Configuration Options

### Required Configuration

- **`baseURL`** - The base URL of your Fineract server
- **`httpClient`** - Configured HttpClient with authentication

### Optional Configuration

- **`tenant`** - Fineract tenant identifier (for multi-tenant setups)
- **`inSecure`** - ⚠️ **Deprecated** - Use proper SSL configuration in HttpClient instead

## Best Practices

1. **Reuse Client Instances** - Create one client per tenant and reuse it across your application
2. **Proper Authentication** - Always configure authentication in the HttpClient
3. **Error Handling** - Implement comprehensive error handling for network and API errors
4. **Date Formatting** - Use `FineractClient.DATE_FORMAT` constant for consistent date formatting
5. **Resource Management** - Properly close HttpClient resources when shutting down
6. **SSL Configuration** - Use proper SSL settings instead of the deprecated `inSecure` option

## Thread Safety

The FineractClient is thread-safe and can be safely used from multiple coroutines concurrently. The underlying Ktorfit
and HttpClient are designed for concurrent use.

## Dependencies

Ensure you have the following dependencies in your project:

```kotlin
// build.gradle.kts
dependencies {
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-client-auth:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("de.jensklingenberg.ktorfit:ktorfit-lib:$ktorfit_version")
    // Add other Fineract client dependencies
}
```