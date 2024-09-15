package org.openapitools.client.apis

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.PUT
import org.openapitools.client.models.CommandProcessingResult
import org.openapitools.client.models.GetExternalEventConfigurationsResponse
import org.openapitools.client.models.PutExternalEventConfigurationsRequest

interface ExternalEventConfigurationApi {
    /**
     * List all external event configurations
     *
     * Responses:
     *  - 200: List of all external event configurations
     *
     * @return [GetExternalEventConfigurationsResponse]
     */
    @GET("v1/externalevents/configuration")
    suspend fun retrieveExternalEventConfiguration(): GetExternalEventConfigurationsResponse

    /**
     * Enable/Disable external events posting
     *
     * Responses:
     *  - 200: OK
     *
     * @param putExternalEventConfigurationsRequest
     * @return [CommandProcessingResult]
     */
    @PUT("v1/externalevents/configuration")
    suspend fun updateExternalEventConfigurationsDetails(@Body putExternalEventConfigurationsRequest: PutExternalEventConfigurationsRequest): CommandProcessingResult

}
