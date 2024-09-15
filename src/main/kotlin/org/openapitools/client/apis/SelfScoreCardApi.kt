package org.openapitools.client.apis

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Path
import org.openapitools.client.models.ScorecardData

interface SelfScoreCardApi {
    /**
     *
     *
     * Responses:
     *  - 0: default response
     *
     * @param surveyId
     * @param scorecardData  (optional)
     * @return [Unit]
     */
    @POST("v1/self/surveys/scorecards/{surveyId}")
    suspend fun createScorecard(
        @Path("surveyId") surveyId: Long,
        @Body scorecardData: ScorecardData? = null
    ): Unit

    /**
     *
     *
     * Responses:
     *  - 0: default response
     *
     * @param clientId
     * @return [kotlin.collections.List<ScorecardData]
     */
    @GET("v1/self/surveys/scorecards/clients/{clientId}")
    suspend fun findByClient(@Path("clientId") clientId: Long): List<ScorecardData>

}
