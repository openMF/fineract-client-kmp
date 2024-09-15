package org.openapitools.client.apis

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST
import org.openapitools.client.models.PageClientSearchData
import org.openapitools.client.models.PagedRequestClientTextSearch

interface ClientSearchV2Api {
    /**
     * Search Clients by text
     *
     * Responses:
     *  - 0: default response
     *
     * @param pagedRequestClientTextSearch  (optional)
     * @return [PageClientSearchData]
     */
    @POST("v2/clients/search")
    suspend fun searchByText(@Body pagedRequestClientTextSearch: PagedRequestClientTextSearch? = null): PageClientSearchData

}
