/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mifos-mobile/LICENSE.md
 */
@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport",
)

package org.mifos.fineract.client.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 *
 * @param quoteCode
 * @param state
 * @param transactionCode
 * @param changes
 * @param clientId
 * @param commandId
 * @param creditBureauReportData
 * @param expiration
 * @param extensionList
 * @param fspCommission
 * @param fspFee
 * @param glimId
 * @param groupId
 * @param gsimId
 * @param loanId
 * @param officeId
 * @param productId
 * @param resourceExternalId
 * @param resourceId
 * @param resourceIdentifier
 * @param rollbackTransaction
 * @param savingsId
 * @param subResourceExternalId
 * @param subResourceId
 * @param transactionId
 */

@Serializable
data class InteropQuoteResponseData(

    val quoteCode: kotlin.String,

    val state: InteropQuoteResponseData.State,

    val transactionCode: kotlin.String,

    val changes: kotlin.collections.Map<kotlin.String, @Contextual kotlin.Any>? = null,

    val clientId: kotlin.Long? = null,

    val commandId: kotlin.Long? = null,

    val creditBureauReportData: kotlin.collections.Map<kotlin.String, @Contextual kotlin.Any>? = null,

    val expiration: kotlin.String? = null,

    val extensionList: kotlin.collections.List<ExtensionData>? = null,

    val fspCommission: MoneyData? = null,

    val fspFee: MoneyData? = null,

    val glimId: kotlin.Long? = null,

    val groupId: kotlin.Long? = null,

    val gsimId: kotlin.Long? = null,

    val loanId: kotlin.Long? = null,

    val officeId: kotlin.Long? = null,

    val productId: kotlin.Long? = null,

    val resourceExternalId: ExternalId? = null,

    val resourceId: kotlin.Long? = null,

    val resourceIdentifier: kotlin.String? = null,

    val rollbackTransaction: kotlin.Boolean? = null,

    val savingsId: kotlin.Long? = null,

    val subResourceExternalId: ExternalId? = null,

    val subResourceId: kotlin.Long? = null,

    val transactionId: kotlin.String? = null,

) {

    /**
     *
     *
     * Values: ACCEPTED,REJECTED
     */
    @Serializable
    enum class State(val value: kotlin.String) {
        @SerialName("ACCEPTED")
        ACCEPTED("ACCEPTED"),

        @SerialName("REJECTED")
        REJECTED("REJECTED"),
    }
}
