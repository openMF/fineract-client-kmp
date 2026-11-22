/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package org.mifos.fineract.client

import kotlinx.datetime.LocalDate
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import org.mifos.fineract.client.models.RetrieveOneResponse
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

class StaffDateTest {
    @Test
    fun readsJoiningDateArray() {
        val staff = Json.decodeFromString<List<RetrieveOneResponse>>("""[{"id":5,"joiningDate":[2025,4,8]}]""")
        assertEquals(LocalDate(2025, 4, 8), staff.single().joiningDate)
    }

    @Test
    fun supportsIsoStringsNullAndMissingDates() {
        assertEquals(LocalDate(2025, 4, 8), Json.decodeFromString<RetrieveOneResponse>("""{"joiningDate":"2025-04-08"}""").joiningDate)
        assertNull(Json.decodeFromString<RetrieveOneResponse>("""{"joiningDate":null}""").joiningDate)
        assertNull(Json.decodeFromString<RetrieveOneResponse>("{}").joiningDate)
        assertEquals("""{"joiningDate":"2025-04-08"}""", Json.encodeToString(RetrieveOneResponse(joiningDate = LocalDate(2025, 4, 8))))
    }

    @Test
    fun preservesCallerSuppliedContextualSerializer() {
        val json = Json { serializersModule = SerializersModule { contextual(LocalDate::class, CustomDateSerializer) } }
        assertEquals(LocalDate(2025, 4, 8), json.decodeFromString<RetrieveOneResponse>("""{"joiningDate":"custom"}""").joiningDate)
        assertEquals("""{"joiningDate":"custom"}""", json.encodeToString(RetrieveOneResponse(joiningDate = LocalDate(2025, 4, 8))))
        assertEquals(LocalDate(2025, 4, 8), json.decodeFromString<RetrieveOneResponse>("""{"joiningDate":[2025,4,8]}""").joiningDate)
    }

    @Test
    fun rejectsMalformedArrays() {
        for (value in listOf("[2025,4]", "[2025,4,8,1]", "[2025,\"4\",8]", "[2025,4.5,8]", "[2025,2,30]", "[true,4,8]")) {
            assertFailsWith<SerializationException>(value) {
                Json.decodeFromString<RetrieveOneResponse>("""{"joiningDate":$value}""")
            }
        }
    }

    private object CustomDateSerializer : KSerializer<LocalDate> {
        override val descriptor = PrimitiveSerialDescriptor("CustomDate", PrimitiveKind.STRING)
        override fun deserialize(decoder: Decoder): LocalDate {
            require(decoder.decodeString() == "custom")
            return LocalDate(2025, 4, 8)
        }
        override fun serialize(encoder: Encoder, value: LocalDate) = encoder.encodeString("custom")
    }
}
