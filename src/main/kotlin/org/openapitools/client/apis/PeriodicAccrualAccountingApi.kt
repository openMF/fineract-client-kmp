package org.openapitools.client.apis

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST
import org.openapitools.client.models.PostRunaccrualsRequest

interface PeriodicAccrualAccountingApi {
    /**
     * Executes Periodic Accrual Accounting
     * Mandatory Fields  tillDate
     * Responses:
     *  - 200: OK
     *
     * @param postRunaccrualsRequest
     * @return [Unit]
     */
    @POST("v1/runaccruals")
    suspend fun executePeriodicAccrualAccounting(@Body postRunaccrualsRequest: PostRunaccrualsRequest)

}
