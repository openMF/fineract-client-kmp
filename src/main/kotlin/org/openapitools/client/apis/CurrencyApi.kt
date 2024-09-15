package org.openapitools.client.apis

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.PUT
import org.openapitools.client.models.GetCurrenciesResponse
import org.openapitools.client.models.PutCurrenciesRequest
import org.openapitools.client.models.PutCurrenciesResponse

interface CurrencyApi {
    /**
     * Retrieve Currency Configuration
     * Returns the list of currencies permitted for use AND the list of currencies not selected (but available for selection).  Example Requests:  currencies   currencies?fields&#x3D;selectedCurrencyOptions
     * Responses:
     *  - 200: OK
     *
     * @return [GetCurrenciesResponse]
     */
    @GET("v1/currencies")
    suspend fun retrieveCurrencies(): GetCurrenciesResponse

    /**
     * Update Currency Configuration
     * Updates the list of currencies permitted for use.
     * Responses:
     *  - 200: OK
     *
     * @param putCurrenciesRequest
     * @return [PutCurrenciesResponse]
     */
    @PUT("v1/currencies")
    suspend fun updateCurrencies(@Body putCurrenciesRequest: PutCurrenciesRequest): PutCurrenciesResponse

}
