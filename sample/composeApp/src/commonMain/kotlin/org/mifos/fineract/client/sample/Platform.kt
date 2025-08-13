package org.mifos.fineract.client.sample

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform