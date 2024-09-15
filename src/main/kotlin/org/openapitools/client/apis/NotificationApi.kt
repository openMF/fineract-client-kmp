package org.openapitools.client.apis

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.PUT
import de.jensklingenberg.ktorfit.http.Query
import org.openapitools.client.models.GetNotificationsResponse

interface NotificationApi {
    /**
     *
     *
     * Responses:
     *  - 200: OK
     *
     * @param orderBy orderBy (optional)
     * @param limit limit (optional)
     * @param offset offset (optional)
     * @param sortOrder sortOrder (optional)
     * @param isRead isRead (optional)
     * @return [GetNotificationsResponse]
     */
    @GET("v1/notifications")
    suspend fun getAllNotifications(
        @Query("orderBy") orderBy: String? = null,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null,
        @Query("sortOrder") sortOrder: String? = null,
        @Query("isRead") isRead: Boolean? = null
    ): GetNotificationsResponse

    /**
     *
     *
     * Responses:
     *  - 0: default response
     *
     * @return [Unit]
     */
    @PUT("v1/notifications")
    suspend fun update5(): Unit

}
