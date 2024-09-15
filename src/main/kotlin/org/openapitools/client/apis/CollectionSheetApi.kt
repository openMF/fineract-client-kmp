package org.openapitools.client.apis

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Query
import org.openapitools.client.models.PostCollectionSheetRequest
import org.openapitools.client.models.PostCollectionSheetResponse

interface CollectionSheetApi {
    /**
     * Generate Individual Collection Sheet | Save Collection Sheet
     * Generate Individual Collection Sheet:  This Api retrieves repayment details of all individual loans under a office as on a specified meeting date.  Save Collection Sheet:  This Api allows the loan officer to perform bulk repayments of individual loans and deposit of mandatory savings on a given meeting date.
     * Responses:
     *  - 200: OK
     *
     * @param postCollectionSheetRequest
     * @param command command (optional)
     * @return [PostCollectionSheetResponse]
     */
    @POST("v1/collectionsheet")
    suspend fun generateCollectionSheet(
        @Body postCollectionSheetRequest: PostCollectionSheetRequest,
        @Query("command") command: String? = null
    ): PostCollectionSheetResponse

}
