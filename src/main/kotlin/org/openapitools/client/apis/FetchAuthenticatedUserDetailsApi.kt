package org.openapitools.client.apis

import de.jensklingenberg.ktorfit.http.GET
import org.openapitools.client.models.GetUserDetailsResponse

interface FetchAuthenticatedUserDetailsApi {
    /**
     * Fetch authenticated user details
     * checks the Authentication and returns the set roles and permissions allowed.
     * Responses:
     *  - 200: OK
     *
     * @return [GetUserDetailsResponse]
     */
    @GET("v1/userdetails")
    suspend fun fetchAuthenticatedUserData(): GetUserDetailsResponse

}
