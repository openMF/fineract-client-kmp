/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package org.mifos.fineract.client.models

import kotlinx.datetime.LocalDate
import kotlinx.serialization.ContextualSerializer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.intOrNull

@OptIn(ExperimentalSerializationApi::class)
internal object StaffJoiningDateSerializer : KSerializer<LocalDate> {
    private val delegate = ContextualSerializer(LocalDate::class, LocalDate.serializer(), emptyArray())
    override val descriptor: SerialDescriptor = delegate.descriptor

    override fun serialize(encoder: Encoder, value: LocalDate) = encoder.encodeSerializableValue(delegate, value)

    override fun deserialize(decoder: Decoder): LocalDate {
        if (decoder !is JsonDecoder) return decoder.decodeSerializableValue(delegate)
        val element = decoder.decodeJsonElement()
        if (element !is JsonArray) return decoder.json.decodeFromJsonElement(delegate, element)
        if (element.size != 3) throw SerializationException("Expected joiningDate as [year, month, day]")
        val parts = element.map {
            (it as? JsonPrimitive)?.takeUnless { primitive -> primitive.isString }?.intOrNull
                ?: throw SerializationException("Expected integer joiningDate components")
        }
        return try {
            LocalDate(parts[0], parts[1], parts[2])
        } catch (e: IllegalArgumentException) {
            throw SerializationException("Invalid joiningDate", e)
        }
    }
}
