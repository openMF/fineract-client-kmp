package org.openapitools.client.apis

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.PUT
import org.openapitools.client.models.GetCachesResponse
import org.openapitools.client.models.PutCachesRequest
import org.openapitools.client.models.PutCachesResponse

interface CacheApi {
    /**
     * Retrieve Cache Types
     * Returns the list of caches.  Example Requests:  caches
     * Responses:
     *  - 200: OK
     *
     * @return [kotlin.collections.List<GetCachesResponse>]
     */
    @GET("v1/caches")
    suspend fun retrieveAll4(): List<GetCachesResponse>

    /**
     * Switch Cache
     * Switches the cache to chosen one.
     * Responses:
     *  - 200: OK
     *
     * @param putCachesRequest
     * @return [PutCachesResponse]
     */
    @PUT("v1/caches")
    suspend fun switchCache(@Body putCachesRequest: PutCachesRequest): PutCachesResponse

}
