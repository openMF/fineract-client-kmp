package org.openapitools.client.apis

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query


interface CashiersApi {
    /**
     *
     *
     * Responses:
     *  - 0: default response
     *
     * @param officeId  (optional)
     * @param tellerId  (optional)
     * @param staffId  (optional)
     * @param date  (optional)
     * @return [kotlin.String]
     */
    @GET("v1/cashiers")
    suspend fun getCashierData(
        @Query("officeId") officeId: Long? = null,
        @Query("tellerId") tellerId: Long? = null,
        @Query("staffId") staffId: Long? = null,
        @Query("date") date: String? = null
    ): String

}
