package org.openapitools.client.apis

import de.jensklingenberg.ktorfit.http.GET


interface MixTaxonomyApi {
    /**
     *
     *
     * Responses:
     *  - 0: default response
     *
     * @return [kotlin.String]
     */
    @GET("v1/mixtaxonomy")
    suspend fun retrieveAll14(): String

}
