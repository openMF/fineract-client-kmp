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

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

/**
 * GetSelfSavingsAccountsAccountIdTransactionsTransactionIdResponse
 *
 * @param accountId
 * @param accountNo
 * @param amount
 * @param currency
 * @param date
 * @param id
 * @param paymentDetailData
 * @param reversed
 * @param runningBalance
 * @param transactionType
 */

@Serializable
data class GetSelfSavingsAccountsAccountIdTransactionsTransactionIdResponse(

    val accountId: kotlin.Int? = null,

    val accountNo: kotlin.Long? = null,

    val amount: kotlin.Int? = null,

    val currency: GetSelfSavingsTransactionCurrency? = null,

    @Contextual
    val date: LocalDate? = null,

    val id: kotlin.Int? = null,

    val paymentDetailData: GetSelfSavingsPaymentDetailData? = null,

    val reversed: kotlin.Boolean? = null,

    val runningBalance: kotlin.Int? = null,

    val transactionType: GetSelfSavingsTransactionType? = null,

)
