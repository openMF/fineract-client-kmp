package org.openapitools.client.apis

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.DELETE
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.PUT
import de.jensklingenberg.ktorfit.http.Path


interface AdhocQueryApiApi {
    /**
     *
     *
     * Responses:
     *  - 0: default response
     *
     * @param body  (optional)
     * @return [kotlin.String]
     */
    @POST("v1/adhocquery")
    suspend fun createAdHocQuery(@Body body: String? = null): String

    /**
     *
     *
     * Responses:
     *  - 0: default response
     *
     * @param adHocId adHocId
     * @return [kotlin.String]
     */
    @DELETE("v1/adhocquery/{adHocId}")
    suspend fun deleteAdHocQuery(@Path("adHocId") adHocId: Long): String

    /**
     *
     *
     * Responses:
     *  - 0: default response
     *
     * @param adHocId adHocId
     * @return [kotlin.String]
     */
    @GET("v1/adhocquery/{adHocId}")
    suspend fun retrieveAdHocQuery(@Path("adHocId") adHocId: Long): String

    /**
     *
     *
     * Responses:
     *  - 0: default response
     *
     * @return [kotlin.String]
     */
    @GET("v1/adhocquery")
    suspend fun retrieveAll2(): String

    /**
     *
     *
     * Responses:
     *  - 0: default response
     *
     * @return [kotlin.String]
     */
    @GET("v1/adhocquery/template")
    suspend fun template(): String

    /**
     *
     *
     * Responses:
     *  - 0: default response
     *
     * @param adHocId adHocId
     * @param body  (optional)
     * @return [kotlin.String]
     */
    @PUT("v1/adhocquery/{adHocId}")
    suspend fun update(
        @Path("adHocId") adHocId: Long,
        @Body body: String? = null
    ): String

}
