package org.openapitools.client.apis

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.DELETE
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.PUT
import de.jensklingenberg.ktorfit.http.Path
import org.openapitools.client.models.DeleteProvisioningCriteriaResponse
import org.openapitools.client.models.GetProvisioningCriteriaCriteriaIdResponse
import org.openapitools.client.models.GetProvisioningCriteriaResponse
import org.openapitools.client.models.PostProvisioningCriteriaRequest
import org.openapitools.client.models.PostProvisioningCriteriaResponse
import org.openapitools.client.models.PutProvisioningCriteriaRequest
import org.openapitools.client.models.PutProvisioningCriteriaResponse

interface ProvisioningCriteriaApi {
    /**
     * Create a new Provisioning Criteria
     * Creates a new Provisioning Criteria  Mandatory Fields:  criteriaName provisioningcriteria  Optional Fields:  loanProducts
     * Responses:
     *  - 200: OK
     *
     * @param postProvisioningCriteriaRequest
     * @return [PostProvisioningCriteriaResponse]
     */
    @POST("v1/provisioningcriteria")
    suspend fun createProvisioningCriteria(@Body postProvisioningCriteriaRequest: PostProvisioningCriteriaRequest): PostProvisioningCriteriaResponse

    /**
     * Deletes Provisioning Criteria
     * Deletes Provisioning Criteria
     * Responses:
     *  - 200: OK
     *
     * @param criteriaId criteriaId
     * @return [DeleteProvisioningCriteriaResponse]
     */
    @DELETE("v1/provisioningcriteria/{criteriaId}")
    suspend fun deleteProvisioningCriteria(@Path("criteriaId") criteriaId: Long): DeleteProvisioningCriteriaResponse

    /**
     * Retrieves all created Provisioning Criterias
     * Retrieves all created Provisioning Criterias
     * Responses:
     *  - 200: OK
     *
     * @return [kotlin.collections.List<GetProvisioningCriteriaResponse]
     */
    @GET("v1/provisioningcriteria")
    suspend fun retrieveAllProvisioningCriterias(): List<GetProvisioningCriteriaResponse>

    /**
     * Retrieves a Provisioning Criteria
     * Retrieves a Provisioning Criteria
     * Responses:
     *  - 200: OK
     *
     * @param criteriaId criteriaId
     * @return [GetProvisioningCriteriaCriteriaIdResponse]
     */
    @GET("v1/provisioningcriteria/{criteriaId}")
    suspend fun retrieveProvisioningCriteria(@Path("criteriaId") criteriaId: Long): GetProvisioningCriteriaCriteriaIdResponse

    /**
     *
     *
     * Responses:
     *  - 0: default response
     *
     * @return [kotlin.String]
     */
    @GET("v1/provisioningcriteria/template")
    suspend fun retrieveTemplate3(): String

    /**
     * Updates a new Provisioning Criteria
     * Updates a new Provisioning Criteria  Optional Fields criteriaName, loanProducts, provisioningcriteria
     * Responses:
     *  - 200: OK
     *
     * @param criteriaId criteriaId
     * @param putProvisioningCriteriaRequest
     * @return [PutProvisioningCriteriaResponse]
     */
    @PUT("v1/provisioningcriteria/{criteriaId}")
    suspend fun updateProvisioningCriteria(
        @Path("criteriaId") criteriaId: Long,
        @Body putProvisioningCriteriaRequest: PutProvisioningCriteriaRequest
    ): PutProvisioningCriteriaResponse

}
