package org.openapitools.client.apis

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query
import org.openapitools.client.models.GetLoanAccountLockResponse

interface LoanAccountLockApi {
    /**
     * List locked loan accounts
     * Returns the locked loan IDs
     * Responses:
     *  - 200: OK
     *
     * @param page  (optional)
     * @param limit  (optional)
     * @return [GetLoanAccountLockResponse]
     */
    @GET("v1/loans/locked")
    suspend fun retrieveLockedAccounts(
        @Query("page") page: Int? = null,
        @Query("limit") limit: Int? = null
    ): GetLoanAccountLockResponse

}
