package org.openapitools.client.apis

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query

interface DepositAccountOnHoldFundTransactionsApi {
    /**
     *
     *
     * Responses:
     *  - 0: default response
     *
     * @param savingsId
     * @param guarantorFundingId  (optional)
     * @param offset  (optional)
     * @param limit  (optional)
     * @param orderBy  (optional)
     * @param sortOrder  (optional)
     * @return [kotlin.String]
     */
    @GET("v1/savingsaccounts/{savingsId}/onholdtransactions")
    suspend fun retrieveAll28(
        @Path("savingsId") savingsId: Long,
        @Query("guarantorFundingId") guarantorFundingId: Long? = null,
        @Query("offset") offset: Int? = null,
        @Query("limit") limit: Int? = null,
        @Query("orderBy") orderBy: String? = null,
        @Query("sortOrder") sortOrder: String? = null
    ): String

}
