package org.openapitools.client.apis

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query


interface SelfSavingsProductsApi {
    /**
     *
     *
     * Responses:
     *  - 0: default response
     *
     * @param clientId  (optional)
     * @return [kotlin.String]
     */
    @GET("v1/self/savingsproducts")
    suspend fun retrieveAll38(@Query("clientId") clientId: Long? = null): String

    /**
     *
     *
     * Responses:
     *  - 0: default response
     *
     * @param productId
     * @param clientId  (optional)
     * @return [kotlin.String]
     */
    @GET("v1/self/savingsproducts/{productId}")
    suspend fun retrieveOne29(
        @Path("productId") productId: Long,
        @Query("clientId") clientId: Long? = null
    ): String

}
