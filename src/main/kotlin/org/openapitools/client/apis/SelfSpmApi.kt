package org.openapitools.client.apis

import de.jensklingenberg.ktorfit.http.GET
import org.openapitools.client.models.SurveyData

interface SelfSpmApi {
    /**
     *
     *
     * Responses:
     *  - 0: default response
     *
     * @return [kotlin.collections.List<SurveyData>]
     */
    @GET("v1/self/surveys")
    suspend fun fetchAllSurveys(): List<SurveyData>

}
