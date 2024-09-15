package org.openapitools.client.apis

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.PUT
import de.jensklingenberg.ktorfit.http.Path

interface LikelihoodApi {
    /**
     *
     *
     * Responses:
     *  - 0: default response
     *
     * @param likelihoodId
     * @param ppiName
     * @return [kotlin.String]
     */
    @GET("v1/likelihood/{ppiName}/{likelihoodId}")
    suspend fun retrieve(
        @Path("likelihoodId") likelihoodId: Long,
        @Path("ppiName") ppiName: String
    ): String

    /**
     *
     *
     * Responses:
     *  - 0: default response
     *
     * @param ppiName
     * @return [kotlin.String]
     */
    @GET("v1/likelihood/{ppiName}")
    suspend fun retrieveAll11(@Path("ppiName") ppiName: String): String

    /**
     *
     *
     * Responses:
     *  - 0: default response
     *
     * @param likelihoodId
     * @param ppiName
     * @param body  (optional)
     * @return [kotlin.String]
     */
    @PUT("v1/likelihood/{ppiName}/{likelihoodId}")
    suspend fun update4(
        @Path("likelihoodId") likelihoodId: Long,
        @Path("ppiName") ppiName: String,
        @Body body: String? = null
    ): String

}
