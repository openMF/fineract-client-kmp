package org.openapitools.client.apis

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import org.openapitools.client.models.GetRunReportResponse

interface SelfRunReportApi {
    /**
     * Running A Report
     * Example Requests:   self/runreports/Client%20Details?R_officeId&#x3D;1   self/runreports/Client%20Details?R_officeId&#x3D;1&amp;exportCSV&#x3D;true
     * Responses:
     *  - 200: OK
     *
     * @param reportName reportName
     * @return [GetRunReportResponse]
     */
    @GET("v1/self/runreports/{reportName}")
    suspend fun runReport1(@Path("reportName") reportName: String): GetRunReportResponse

}
