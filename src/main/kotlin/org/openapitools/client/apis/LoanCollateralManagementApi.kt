package org.openapitools.client.apis

import de.jensklingenberg.ktorfit.http.DELETE
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.HTTP
import de.jensklingenberg.ktorfit.http.Path


interface LoanCollateralManagementApi {
    /**
     * Delete Loan Collateral
     * Delete Loan Collateral
     * Responses:
     *  - 0: default response
     *
     * @param loanId loanId
     * @param id loan collateral id
     * @return [kotlin.String]
     */
    @HTTP(method = "DELETE", path = "v1/loan-collateral-management/{id}/{loanId}", hasBody = true)
    suspend fun deleteLoanCollateral(
        @Path("loanId") loanId: Long,
        @Path("id") id: Long
    ): String

    /**
     * Get Loan Collateral Details
     * Get Loan Collateral Details
     * Responses:
     *  - 0: default response
     *
     * @param collateralId collateralId
     * @return [kotlin.String]
     */
    @GET("v1/loan-collateral-management/{collateralId}")
    suspend fun getLoanCollateral(@Path("collateralId") collateralId: Long): String

}
