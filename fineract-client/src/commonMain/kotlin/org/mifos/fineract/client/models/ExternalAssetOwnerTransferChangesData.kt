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
 * ExternalAssetOwnerTransferChangesData
 *
 * @param ownerExternalId
 * @param purchasePriceRatio
 * @param settlementDate
 * @param transferExternalId
 */

@Serializable
data class ExternalAssetOwnerTransferChangesData(

    val ownerExternalId: kotlin.String? = null,

    val purchasePriceRatio: kotlin.String? = null,

    @Contextual
    val settlementDate: LocalDate? = null,

    val transferExternalId: kotlin.String? = null,

)
