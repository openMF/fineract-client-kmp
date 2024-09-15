package org.openapitools.client.apis

import de.jensklingenberg.ktorfit.http.GET


interface GroupsLevelApi {
    /**
     *
     *
     * Responses:
     *  - 0: default response
     *
     * @return [kotlin.String]
     */
    @GET("v1/grouplevels")
    suspend fun retrieveAllGroups(): String

}
