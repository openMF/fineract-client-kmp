package org.openapitools.client.apis

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Query
import org.openapitools.client.models.PostAuthenticationRequest
import org.openapitools.client.models.PostAuthenticationResponse

interface AuthenticationHTTPBasicApi {
    /**
     * Verify authentication
     * Authenticates the credentials provided and returns the set roles and permissions allowed.
     * Responses:
     *  - 200: OK
     *  - 400: Unauthenticated. Please login
     *
     * @param postAuthenticationRequest
     * @param returnClientList  (optional, default to false)
     * @return [PostAuthenticationResponse]
     */
    @POST("v1/authentication")
    suspend fun authenticate(
        @Body postAuthenticationRequest: PostAuthenticationRequest,
        @Query("returnClientList") returnClientList: Boolean? = false
    ): PostAuthenticationResponse

}
