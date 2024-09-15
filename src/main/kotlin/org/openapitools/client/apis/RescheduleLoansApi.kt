package org.openapitools.client.apis

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query
import org.openapitools.client.models.GetLoanRescheduleRequestResponse
import org.openapitools.client.models.GetRescheduleReasonsTemplateResponse
import org.openapitools.client.models.PostCreateRescheduleLoansRequest
import org.openapitools.client.models.PostCreateRescheduleLoansResponse
import org.openapitools.client.models.PostUpdateRescheduleLoansRequest
import org.openapitools.client.models.PostUpdateRescheduleLoansResponse

interface RescheduleLoansApi {
    /**
     * Create loan reschedule request
     * Create a loan reschedule request.
     * Responses:
     *  - 200: OK
     *
     * @param postCreateRescheduleLoansRequest
     * @return [PostCreateRescheduleLoansResponse]
     */
    @POST("v1/rescheduleloans")
    suspend fun createLoanRescheduleRequest(@Body postCreateRescheduleLoansRequest: PostCreateRescheduleLoansRequest): PostCreateRescheduleLoansResponse

    /**
     * Retrieve loan reschedule request by schedule id
     * Retrieve loan reschedule request by schedule id
     * Responses:
     *  - 200: OK
     *
     * @param scheduleId
     * @param command  (optional)
     * @return [GetLoanRescheduleRequestResponse]
     */
    @GET("v1/rescheduleloans/{scheduleId}")
    suspend fun readLoanRescheduleRequest(
        @Path("scheduleId") scheduleId: Long,
        @Query("command") command: String? = null
    ): GetLoanRescheduleRequestResponse

    /**
     * Retrieve all reschedule requests
     * Retrieve all reschedule requests.
     * Responses:
     *  - 200: OK
     *
     * @param command  (optional)
     * @param loanId  (optional)
     * @return [kotlin.collections.List<GetLoanRescheduleRequestResponse]
     */
    @GET("v1/rescheduleloans")
    suspend fun retrieveAllRescheduleRequest(
        @Query("command") command: String? = null,
        @Query("loanId") loanId: Long? = null
    ): List<GetLoanRescheduleRequestResponse>

    /**
     * Retrieve all reschedule loan reasons
     * Retrieve all reschedule loan reasons as a template
     * Responses:
     *  - 200: OK
     *
     * @return [GetRescheduleReasonsTemplateResponse]
     */
    @GET("v1/rescheduleloans/template")
    suspend fun retrieveTemplate10(): GetRescheduleReasonsTemplateResponse

    /**
     * Update loan reschedule request
     * Update a loan reschedule request by either approving/rejecting it.
     * Responses:
     *  - 200: OK
     *
     * @param scheduleId
     * @param postUpdateRescheduleLoansRequest
     * @param command  (optional)
     * @return [PostUpdateRescheduleLoansResponse]
     */
    @POST("v1/rescheduleloans/{scheduleId}")
    suspend fun updateLoanRescheduleRequest(
        @Path("scheduleId") scheduleId: Long,
        @Body postUpdateRescheduleLoansRequest: PostUpdateRescheduleLoansRequest,
        @Query("command") command: String? = null
    ): PostUpdateRescheduleLoansResponse

}
