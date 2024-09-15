package org.openapitools.client.apis

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Path
import org.openapitools.client.models.InlineJobRequest
import org.openapitools.client.models.InlineJobResponse

interface InlineJobApi {
    /**
     * Starts an inline Job
     * Starts an inline Job
     * Responses:
     *  - 200: OK
     *  - 400: Request body item size validation error
     *
     * @param jobName jobName
     * @param inlineJobRequest  (optional)
     * @return [InlineJobResponse]
     */
    @POST("v1/jobs/{jobName}/inline")
    suspend fun executeInlineJob(
        @Path("jobName") jobName: String,
        @Body inlineJobRequest: InlineJobRequest? = null
    ): InlineJobResponse

}
