package org.openapitools.client.apis

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.PUT
import de.jensklingenberg.ktorfit.http.Path


interface LoanDisbursementDetailsApi {
    /**
     *
     *
     * Responses:
     *  - 0: default response
     *
     * @param loanId
     * @param body  (optional)
     * @return [kotlin.String]
     */
    @PUT("v1/loans/{loanId}/disbursements/editDisbursements")
    suspend fun addAndDeleteDisbursementDetail(
        @Path("loanId") loanId: Long,
        @Body body: String? = null
    ): String

    /**
     *
     *
     * Responses:
     *  - 0: default response
     *
     * @param loanId
     * @param disbursementId
     * @return [kotlin.String]
     */
    @GET("v1/loans/{loanId}/disbursements/{disbursementId}")
    suspend fun retriveDetail(
        @Path("loanId") loanId: Long,
        @Path("disbursementId") disbursementId: Long
    ): String

    /**
     *
     *
     * Responses:
     *  - 0: default response
     *
     * @param loanId
     * @param disbursementId
     * @param body  (optional)
     * @return [kotlin.String]
     */
    @PUT("v1/loans/{loanId}/disbursements/{disbursementId}")
    suspend fun updateDisbursementDate(
        @Path("loanId") loanId: Long,
        @Path("disbursementId") disbursementId: Long,
        @Body body: String? = null
    ): String

}
