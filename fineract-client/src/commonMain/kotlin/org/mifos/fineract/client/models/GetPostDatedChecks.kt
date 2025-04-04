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
 * GetPostDatedChecks
 *
 * @param accountNo
 * @param amount
 * @param date
 * @param id
 * @param installmentId
 * @param name
 */

@Serializable
data class GetPostDatedChecks(

    val accountNo: kotlin.Long? = null,

    @Contextual
    val amount: Double? = null,

    @Contextual
    val date: LocalDate? = null,

    val id: kotlin.Long? = null,

    val installmentId: kotlin.Int? = null,

    val name: kotlin.String? = null,

)
