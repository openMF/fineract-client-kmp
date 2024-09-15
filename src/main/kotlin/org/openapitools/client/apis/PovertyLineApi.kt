package org.openapitools.client.apis

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path


interface PovertyLineApi {
    /**
     *
     *
     * Responses:
     *  - 0: default response
     *
     * @param ppiName
     * @return [kotlin.String]
     */
    @GET("v1/povertyLine/{ppiName}")
    suspend fun retrieveAll12(@Path("ppiName") ppiName: String): String

    /**
     *
     *
     * Responses:
     *  - 0: default response
     *
     * @param ppiName
     * @param likelihoodId
     * @return [kotlin.String]
     */
    @GET("v1/povertyLine/{ppiName}/{likelihoodId}")
    suspend fun retrieveAll13(
        @Path("ppiName") ppiName: String,
        @Path("likelihoodId") likelihoodId: Long
    ): String

}
