package org.openapitools.client.apis

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.PUT
import org.openapitools.client.models.ChangeInstanceModeRequest

interface InstanceModeApi {
    /**
     * Changes the Fineract instance mode
     *
     * Responses:
     *  - 200: OK
     *
     * @param changeInstanceModeRequest
     * @return [Unit]
     */
    @PUT("v1/instance-mode")
    suspend fun changeMode(@Body changeInstanceModeRequest: ChangeInstanceModeRequest)

}
