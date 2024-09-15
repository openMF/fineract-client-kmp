package org.openapitools.client.apis

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import org.openapitools.client.models.GetFieldConfigurationEntityResponse

interface EntityFieldConfigurationApi {
    /**
     * Retrieves the Entity Field Configuration
     * It retrieves all the Entity Field Configuration
     * Responses:
     *  - 200: OK
     *
     * @param entity entity
     * @return [kotlin.collections.List<GetFieldConfigurationEntityResponse>]
     */
    @GET("v1/fieldconfiguration/{entity}")
    suspend fun getAddresses(@Path("entity") entity: String): List<GetFieldConfigurationEntityResponse>

}
