package org.openapitools.client.apis

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.PUT
import org.openapitools.client.models.GetPasswordPreferencesTemplateResponse
import org.openapitools.client.models.PutPasswordPreferencesTemplateRequest

interface PasswordPreferencesApi {
    /**
     *
     *
     * Responses:
     *  - 200: OK
     *
     * @return [GetPasswordPreferencesTemplateResponse]
     */
    @GET("v1/passwordpreferences")
    suspend fun retrieve1(): GetPasswordPreferencesTemplateResponse

    /**
     * List Application Password validation policies
     * ARGUMENTS Example Requests:  passwordpreferences
     * Responses:
     *  - 200: OK
     *
     * @return [kotlin.collections.List<GetPasswordPreferencesTemplateResponse]
     */
    @GET("v1/passwordpreferences/template")
    suspend fun template21(): List<GetPasswordPreferencesTemplateResponse>

    /**
     * Update password preferences
     *
     * Responses:
     *  - 200: OK
     *
     * @param putPasswordPreferencesTemplateRequest
     * @return [Unit]
     */
    @PUT("v1/passwordpreferences")
    suspend fun update25(@Body putPasswordPreferencesTemplateRequest: PutPasswordPreferencesTemplateRequest): Unit

}
