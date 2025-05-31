/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mifos-mobile/LICENSE.md
 */
package org.mifos.fineract.client.infrastructure

import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BasicAuthCredentials
import io.ktor.client.plugins.auth.providers.basic
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.mifos.fineract.client.apis.createAccountNumberFormatApi
import org.mifos.fineract.client.apis.createAccountTransfersApi
import org.mifos.fineract.client.apis.createAccountingClosureApi
import org.mifos.fineract.client.apis.createAccountingRulesApi
import org.mifos.fineract.client.apis.createAdhocQueryApiApi
import org.mifos.fineract.client.apis.createAuditsApi
import org.mifos.fineract.client.apis.createAuthenticationHTTPBasicApi
import org.mifos.fineract.client.apis.createBatchAPIApi
import org.mifos.fineract.client.apis.createBulkImportApi
import org.mifos.fineract.client.apis.createBulkLoansApi
import org.mifos.fineract.client.apis.createBusinessDateManagementApi
import org.mifos.fineract.client.apis.createBusinessStepConfigurationApi
import org.mifos.fineract.client.apis.createCacheApi
import org.mifos.fineract.client.apis.createCalendarApi
import org.mifos.fineract.client.apis.createCashierJournalsApi
import org.mifos.fineract.client.apis.createCashiersApi
import org.mifos.fineract.client.apis.createCentersApi
import org.mifos.fineract.client.apis.createChargesApi
import org.mifos.fineract.client.apis.createClientApi
import org.mifos.fineract.client.apis.createClientChargesApi
import org.mifos.fineract.client.apis.createClientCollateralManagementApi
import org.mifos.fineract.client.apis.createClientFamilyMemberApi
import org.mifos.fineract.client.apis.createClientIdentifierApi
import org.mifos.fineract.client.apis.createClientSearchV2Api
import org.mifos.fineract.client.apis.createClientTransactionApi
import org.mifos.fineract.client.apis.createClientsAddressApi
import org.mifos.fineract.client.apis.createCodeValuesApi
import org.mifos.fineract.client.apis.createCodesApi
import org.mifos.fineract.client.apis.createCollateralManagementApi
import org.mifos.fineract.client.apis.createCollectionSheetApi
import org.mifos.fineract.client.apis.createCreditBureauConfigurationApi
import org.mifos.fineract.client.apis.createCurrencyApi
import org.mifos.fineract.client.apis.createDataTablesApi
import org.mifos.fineract.client.apis.createDefaultApi
import org.mifos.fineract.client.apis.createDelinquencyRangeAndBucketsManagementApi
import org.mifos.fineract.client.apis.createDepositAccountOnHoldFundTransactionsApi
import org.mifos.fineract.client.apis.createDeviceRegistrationApi
import org.mifos.fineract.client.apis.createDocumentsApi
import org.mifos.fineract.client.apis.createEntityDataTableApi
import org.mifos.fineract.client.apis.createEntityFieldConfigurationApi
import org.mifos.fineract.client.apis.createExternalAssetOwnersApi
import org.mifos.fineract.client.apis.createExternalEventConfigurationApi
import org.mifos.fineract.client.apis.createExternalServicesApi
import org.mifos.fineract.client.apis.createFetchAuthenticatedUserDetailsApi
import org.mifos.fineract.client.apis.createFineractEntityApi
import org.mifos.fineract.client.apis.createFixedDepositAccountApi
import org.mifos.fineract.client.apis.createFixedDepositAccountTransactionsApi
import org.mifos.fineract.client.apis.createFixedDepositProductApi
import org.mifos.fineract.client.apis.createFloatingRatesApi
import org.mifos.fineract.client.apis.createFundsApi
import org.mifos.fineract.client.apis.createGeneralLedgerAccountApi
import org.mifos.fineract.client.apis.createGlobalConfigurationApi
import org.mifos.fineract.client.apis.createGroupsApi
import org.mifos.fineract.client.apis.createGroupsLevelApi
import org.mifos.fineract.client.apis.createGuarantorsApi
import org.mifos.fineract.client.apis.createHolidaysApi
import org.mifos.fineract.client.apis.createHooksApi
import org.mifos.fineract.client.apis.createInlineJobApi
import org.mifos.fineract.client.apis.createInstanceModeApi
import org.mifos.fineract.client.apis.createInterOperationApi
import org.mifos.fineract.client.apis.createInterestRateChartApi
import org.mifos.fineract.client.apis.createInterestRateSlabAKAInterestBandsApi
import org.mifos.fineract.client.apis.createJournalEntriesApi
import org.mifos.fineract.client.apis.createLikelihoodApi
import org.mifos.fineract.client.apis.createListReportMailingJobHistoryApi
import org.mifos.fineract.client.apis.createLoanAccountLockApi
import org.mifos.fineract.client.apis.createLoanCOBCatchUpApi
import org.mifos.fineract.client.apis.createLoanChargesApi
import org.mifos.fineract.client.apis.createLoanCollateralApi
import org.mifos.fineract.client.apis.createLoanCollateralManagementApi
import org.mifos.fineract.client.apis.createLoanDisbursementDetailsApi
import org.mifos.fineract.client.apis.createLoanProductsApi
import org.mifos.fineract.client.apis.createLoanReschedulingApi
import org.mifos.fineract.client.apis.createLoanTransactionsApi
import org.mifos.fineract.client.apis.createLoansApi
import org.mifos.fineract.client.apis.createMakerCheckerOr4EyeFunctionalityApi
import org.mifos.fineract.client.apis.createMappingFinancialActivitiesToAccountsApi
import org.mifos.fineract.client.apis.createMeetingsApi
import org.mifos.fineract.client.apis.createMixMappingApi
import org.mifos.fineract.client.apis.createMixReportApi
import org.mifos.fineract.client.apis.createMixTaxonomyApi
import org.mifos.fineract.client.apis.createNotesApi
import org.mifos.fineract.client.apis.createNotificationApi
import org.mifos.fineract.client.apis.createOfficesApi
import org.mifos.fineract.client.apis.createPasswordPreferencesApi
import org.mifos.fineract.client.apis.createPaymentTypeApi
import org.mifos.fineract.client.apis.createPeriodicAccrualAccountingApi
import org.mifos.fineract.client.apis.createPermissionsApi
import org.mifos.fineract.client.apis.createPocketApi
import org.mifos.fineract.client.apis.createPovertyLineApi
import org.mifos.fineract.client.apis.createProductMixApi
import org.mifos.fineract.client.apis.createProductsApi
import org.mifos.fineract.client.apis.createProvisioningCategoryApi
import org.mifos.fineract.client.apis.createProvisioningCriteriaApi
import org.mifos.fineract.client.apis.createProvisioningEntriesApi
import org.mifos.fineract.client.apis.createRateApi
import org.mifos.fineract.client.apis.createRecurringDepositAccountApi
import org.mifos.fineract.client.apis.createRecurringDepositAccountTransactionsApi
import org.mifos.fineract.client.apis.createRecurringDepositProductApi
import org.mifos.fineract.client.apis.createRepaymentWithPostDatedChecksApi
import org.mifos.fineract.client.apis.createReportMailingJobsApi
import org.mifos.fineract.client.apis.createReportsApi
import org.mifos.fineract.client.apis.createRescheduleLoansApi
import org.mifos.fineract.client.apis.createRolesApi
import org.mifos.fineract.client.apis.createRunReportsApi
import org.mifos.fineract.client.apis.createSCHEDULERJOBApi
import org.mifos.fineract.client.apis.createSMSApi
import org.mifos.fineract.client.apis.createSPMAPILookUpTableApi
import org.mifos.fineract.client.apis.createSavingsAccountApi
import org.mifos.fineract.client.apis.createSavingsAccountTransactionsApi
import org.mifos.fineract.client.apis.createSavingsChargesApi
import org.mifos.fineract.client.apis.createSavingsProductApi
import org.mifos.fineract.client.apis.createSchedulerApi
import org.mifos.fineract.client.apis.createScoreCardApi
import org.mifos.fineract.client.apis.createSearchAPIApi
import org.mifos.fineract.client.apis.createSelfAccountTransferApi
import org.mifos.fineract.client.apis.createSelfAuthenticationApi
import org.mifos.fineract.client.apis.createSelfClientApi
import org.mifos.fineract.client.apis.createSelfDividendApi
import org.mifos.fineract.client.apis.createSelfLoanProductsApi
import org.mifos.fineract.client.apis.createSelfLoansApi
import org.mifos.fineract.client.apis.createSelfRunReportApi
import org.mifos.fineract.client.apis.createSelfSavingsAccountApi
import org.mifos.fineract.client.apis.createSelfSavingsProductsApi
import org.mifos.fineract.client.apis.createSelfScoreCardApi
import org.mifos.fineract.client.apis.createSelfServiceRegistrationApi
import org.mifos.fineract.client.apis.createSelfShareAccountsApi
import org.mifos.fineract.client.apis.createSelfSpmApi
import org.mifos.fineract.client.apis.createSelfThirdPartyTransferApi
import org.mifos.fineract.client.apis.createSelfUserApi
import org.mifos.fineract.client.apis.createSelfUserDetailsApi
import org.mifos.fineract.client.apis.createShareAccountApi
import org.mifos.fineract.client.apis.createSpmSurveysApi
import org.mifos.fineract.client.apis.createStaffApi
import org.mifos.fineract.client.apis.createStandingInstructionsApi
import org.mifos.fineract.client.apis.createStandingInstructionsHistoryApi
import org.mifos.fineract.client.apis.createSurveyApi
import org.mifos.fineract.client.apis.createTaxComponentsApi
import org.mifos.fineract.client.apis.createTaxGroupApi
import org.mifos.fineract.client.apis.createTellerCashManagementApi
import org.mifos.fineract.client.apis.createTwoFactorApi
import org.mifos.fineract.client.apis.createUserGeneratedDocumentsApi
import org.mifos.fineract.client.apis.createUsersApi
import org.mifos.fineract.client.apis.createWorkingDaysApi

/**
 * A client for interacting with Apache Fineract's REST APIs.
 *
 * This class provides access to various Fineract API endpoints through a type-safe interface.
 * It uses Ktorfit under the hood for HTTP communication and supports multi-tenant configurations.
 *
 * The client is designed to be thread-safe and can be safely used from multiple coroutines
 * concurrently. It's recommended to create one instance per tenant and reuse it throughout
 * your application.
 *
 * Example usage:
 * ```kotlin
 * val client = FineractClient.builder()
 *     .baseURL("https://your-fineract-server.com/fineract-provider/api/v1")
 *     .tenant("default")
 *     .httpClient(httpClient)
 *     .build()
 *
 * // Access API endpoints
 * val closures = client.accountingClosures.getAllClosures()
 * ```
 *
 * @constructor Creates a new FineractClient instance. Use [FineractClient.builder] instead of calling this directly.
 * @param ktorfit The configured Ktorfit instance for making HTTP requests
 *
 * @see Builder
 * @since 1.0.0
 */
class FineractClient private constructor(
    private val ktorfit: Ktorfit,
) {
    val accountingClosures = ktorfit.createAccountingClosureApi()
    val accountingRules = ktorfit.createAccountingRulesApi()
    val accountNumberFormats = ktorfit.createAccountNumberFormatApi()
    val accountTransfers = ktorfit.createAccountTransfersApi()
    val adhocQuery = ktorfit.createAdhocQueryApiApi()
    val audits = ktorfit.createAuditsApi()
    val authentication = ktorfit.createAuthenticationHTTPBasicApi()
    val batches = ktorfit.createBatchAPIApi()
    val bulkImport = ktorfit.createBulkImportApi()
    val bulkLoans = ktorfit.createBulkLoansApi()
    val businessDateManagement = ktorfit.createBusinessDateManagementApi()
    val businessStepConfiguration = ktorfit.createBusinessStepConfigurationApi()
    val caches = ktorfit.createCacheApi()
    val calender = ktorfit.createCalendarApi()
    val cashiersJournal = ktorfit.createCashierJournalsApi()
    val cashiers = ktorfit.createCashiersApi()
    val centers = ktorfit.createCentersApi()
    val charges = ktorfit.createChargesApi()
    val clients = ktorfit.createClientApi()
    val clientCharges = ktorfit.createClientChargesApi()
    val clientCollateralManagement = ktorfit.createClientCollateralManagementApi()
    val clientFamilyMember = ktorfit.createClientFamilyMemberApi()
    val clientIdentifiers = ktorfit.createClientIdentifierApi()
    val clientAddresses = ktorfit.createClientsAddressApi()
    val clientSearch = ktorfit.createClientSearchV2Api()
    val clientTransactions = ktorfit.createClientTransactionApi()
    val codes = ktorfit.createCodesApi()
    val codeValues = ktorfit.createCodeValuesApi()
    val collateralManagement = ktorfit.createCollateralManagementApi()
    val collectionSheet = ktorfit.createCollectionSheetApi()
    val creditBureauConfiguration = ktorfit.createCreditBureauConfigurationApi()
    val currencies = ktorfit.createCurrencyApi()
    val dataTables = ktorfit.createDataTablesApi()
    val legacy = ktorfit.createDefaultApi() // TODO FINERACT-1222
    val delinquencyRangeAndBucketsManagement =
        ktorfit.createDelinquencyRangeAndBucketsManagementApi()
    val depositAccountOnHoldFundTransactions =
        ktorfit.createDepositAccountOnHoldFundTransactionsApi()
    val deviceRegistrationApi = ktorfit.createDeviceRegistrationApi()
    val documents = ktorfit.createDocumentsApi()
    val entityDatatableChecks = ktorfit.createEntityDataTableApi()
    val entityFieldConfigurations = ktorfit.createEntityFieldConfigurationApi()
    val externalAssetOwners = ktorfit.createExternalAssetOwnersApi()
    val externalEventConfiguration = ktorfit.createExternalEventConfigurationApi()
    val externalServices = ktorfit.createExternalServicesApi()
    val userDetails = ktorfit.createFetchAuthenticatedUserDetailsApi()
    val fineractEntity = ktorfit.createFineractEntityApi()
    val fixedDepositAccounts = ktorfit.createFixedDepositAccountApi()
    val fixedDepositAccountTransactions = ktorfit.createFixedDepositAccountTransactionsApi()
    val fixedDepositProducts = ktorfit.createFixedDepositProductApi()
    val floatingRates = ktorfit.createFloatingRatesApi()
    val funds = ktorfit.createFundsApi()
    val glAccounts = ktorfit.createGeneralLedgerAccountApi()
    val globalConfigurations = ktorfit.createGlobalConfigurationApi()
    val groups = ktorfit.createGroupsApi()
    val groupsLoans = ktorfit.createGroupsLevelApi()
    val guarantors = ktorfit.createGuarantorsApi()
    val holidays = ktorfit.createHolidaysApi()
    val hooks = ktorfit.createHooksApi()
    val inlineJob = ktorfit.createInlineJobApi()
    val instanceMode = ktorfit.createInstanceModeApi()
    val interestRateCharts = ktorfit.createInterestRateChartApi()
    val interestRateChartLabs = ktorfit.createInterestRateSlabAKAInterestBandsApi()
    val interOperation = ktorfit.createInterOperationApi()
    val journalEntries = ktorfit.createJournalEntriesApi()
    val likelihood = ktorfit.createLikelihoodApi()
    val reportMailings = ktorfit.createListReportMailingJobHistoryApi()
    val loanAccountLock = ktorfit.createLoanAccountLockApi()
    val loanCharges = ktorfit.createLoanChargesApi()
    val loanCOBCatchUp = ktorfit.createLoanCOBCatchUpApi()
    val loanCollaterals = ktorfit.createLoanCollateralApi()
    val loanCollateralManagement = ktorfit.createLoanCollateralManagementApi()
    val loanDisbursementDetails = ktorfit.createLoanDisbursementDetailsApi()
    val loanProducts = ktorfit.createLoanProductsApi()
    val loanSchedules = ktorfit.createLoanReschedulingApi()
    val loans = ktorfit.createLoansApi()
    val loanTransactions = ktorfit.createLoanTransactionsApi()
    val makerCheckers = ktorfit.createMakerCheckerOr4EyeFunctionalityApi()
    val financialActivityAccountMappings = ktorfit.createMappingFinancialActivitiesToAccountsApi()
    val meetings = ktorfit.createMeetingsApi()
    val mixMappings = ktorfit.createMixMappingApi()
    val mixReports = ktorfit.createMixReportApi()
    val mixTaxonomies = ktorfit.createMixTaxonomyApi()
    val notes = ktorfit.createNotesApi()
    val notifications = ktorfit.createNotificationApi()
    val offices = ktorfit.createOfficesApi()
    val passwordPreferences = ktorfit.createPasswordPreferencesApi()
    val paymentTypes = ktorfit.createPaymentTypeApi()
    val periodicAccrualAccounting = ktorfit.createPeriodicAccrualAccountingApi()
    val permissions = ktorfit.createPermissionsApi()
    val selfPockets = ktorfit.createPocketApi()
    val povertyLine = ktorfit.createPovertyLineApi()
    val productMix = ktorfit.createProductMixApi()
    val provisioningCategories = ktorfit.createProvisioningCategoryApi()
    val products = ktorfit.createProductsApi()
    val provisioningCriterias = ktorfit.createProvisioningCriteriaApi()
    val provisioningEntries = ktorfit.createProvisioningEntriesApi()
    val rate = ktorfit.createRateApi()
    val recurringDepositAccounts = ktorfit.createRecurringDepositAccountApi()
    val recurringDepositAccountTransactions = ktorfit.createRecurringDepositAccountTransactionsApi()
    val recurringDepositProducts = ktorfit.createRecurringDepositProductApi()
    val repaymentWithPostDatedChecks = ktorfit.createRepaymentWithPostDatedChecksApi()
    val reportMailingJobs = ktorfit.createReportMailingJobsApi()
    val reports = ktorfit.createReportsApi()
    val rescheduling = ktorfit.createRescheduleLoansApi()
    val roles = ktorfit.createRolesApi()
    val reportsRun = ktorfit.createRunReportsApi()
    val savingsAccounts = ktorfit.createSavingsAccountApi()
    val savingsTransactions = ktorfit.createSavingsAccountTransactionsApi()
    val savingsAccountCharges = ktorfit.createSavingsChargesApi()
    val savingsProducts = ktorfit.createSavingsProductApi()
    val jobsScheduler = ktorfit.createSchedulerApi()
    val jobs = ktorfit.createSCHEDULERJOBApi()
    val surveyScorecards = ktorfit.createScoreCardApi()
    val search = ktorfit.createSearchAPIApi()
    val selfAccountTransfers = ktorfit.createSelfAccountTransferApi()
    val selfAuthentication = ktorfit.createSelfAuthenticationApi()
    val selfClients = ktorfit.createSelfClientApi()
    val selfDividend = ktorfit.createSelfDividendApi()
    val selfLoanProducts = ktorfit.createSelfLoanProductsApi()
    val selfLoans = ktorfit.createSelfLoansApi()
    val selfReportsRun = ktorfit.createSelfRunReportApi()
    val selfSavingsAccounts = ktorfit.createSelfSavingsAccountApi()
    val selfSavingsProducts = ktorfit.createSelfSavingsProductsApi()
    val selfSurveyScorecards = ktorfit.createSelfScoreCardApi()
    val selfRegistration = ktorfit.createSelfServiceRegistrationApi()
    val selfShareAccounts = ktorfit.createSelfShareAccountsApi()
    val selfSurveys = ktorfit.createSelfSpmApi()
    val selfThirdPartyBeneficiaries = ktorfit.createSelfThirdPartyTransferApi()
    val selfUser = ktorfit.createSelfUserApi()
    val selfUserDetails = ktorfit.createSelfUserDetailsApi()
    val shareAccounts = ktorfit.createShareAccountApi()
    val sms = ktorfit.createSMSApi()
    val surveyLookupTables = ktorfit.createSPMAPILookUpTableApi()
    val spmSurveys = ktorfit.createSpmSurveysApi()
    val staff = ktorfit.createStaffApi()
    val standingInstructions = ktorfit.createStandingInstructionsApi()
    val standingInstructionsHistory = ktorfit.createStandingInstructionsHistoryApi()
    val surveys = ktorfit.createSurveyApi()
    val taxComponents = ktorfit.createTaxComponentsApi()
    val taxGroups = ktorfit.createTaxGroupApi()
    val tellers = ktorfit.createTellerCashManagementApi()
    val twoFactor = ktorfit.createTwoFactorApi()
    val templates = ktorfit.createUserGeneratedDocumentsApi()
    val users = ktorfit.createUsersApi()
    val workingDays = ktorfit.createWorkingDaysApi()

    fun baseURL(): String {
        return ktorfit.baseUrl
    }

    fun httpClient(): HttpClient {
        return ktorfit.httpClient
    }

    /**
     * Builder class for constructing [FineractClient] instances.
     *
     * This class follows the Builder pattern to provide a fluent API for configuring
     * various aspects of the FineractClient. All configuration methods return the
     * builder instance to allow method chaining.
     *
     * Example usage:
     * ```kotlin
     * val client = FineractClient.builder()
     *     .baseURL("https://example.com/fineract-provider/api/v1")
     *     .tenant("production")
     *     .httpClient(customHttpClient)
     *     .build()
     * ```
     *
     * @constructor Creates a new Builder instance. Use [FineractClient.builder] instead of calling this directly.
     * @since 1.0.0
     */
    class Builder internal constructor() {

        private lateinit var baseURL: String
        private var tenant: String? = null
        private var loginUsername: String? = null
        private var loginPassword: String? = null
        private var insecure: Boolean = false
        private var httpClient: HttpClient? = null

        /**
         * Sets the base URL for the Fineract server.
         *
         * This should be the full URL to your Fineract API endpoint, typically ending with
         * "/fineract-provider/api/v1" for standard Fineract installations.
         *
         * @param baseURL The base URL string for the Fineract server (required)
         * @return This builder instance for method chaining
         * @throws IllegalArgumentException if baseURL is empty or malformed
         */
        fun baseURL(baseURL: String): Builder {
            this.baseURL = baseURL
            return this
        }

        /**
         * Sets the tenant identifier for multi-tenant Fineract installations.
         *
         * In multi-tenant setups, this identifies which tenant's data to access.
         * If not set or set to null, the client will work with single-tenant installations
         * or use the default tenant configuration.
         *
         * @param tenant The tenant identifier string, or null for single-tenant setups
         * @return This builder instance for method chaining
         */
        fun tenant(tenant: String?): Builder {
            this.tenant = tenant
            return this
        }

        /**
         * Sets the HttpClient instance to use for network communication.
         *
         * This HttpClient should be properly configured with authentication, timeouts,
         * and any other network settings required for your environment. The client
         * should include content negotiation for JSON and appropriate authentication
         * mechanisms (Basic Auth, Bearer tokens, etc.).
         *
         * @param httpClient The configured HttpClient instance (required)
         * @return This builder instance for method chaining
         * @see io.ktor.client.HttpClient
         */
        fun httpClient(httpClient: HttpClient): Builder {
            this.httpClient = httpClient
            return this
        }

        /**
         * Configures whether to allow insecure connections.
         *
         * @deprecated This method is deprecated and will be removed in a future release.
         * Instead, configure SSL settings directly in your HttpClient configuration.
         * Using insecure connections in production environments is strongly discouraged.
         *
         * @param insecure Whether to allow insecure connections
         * @return This builder instance for method chaining
         */
        @Deprecated("This method is deprecated and will be removed in a future release.")
        fun inSecure(insecure: Boolean): Builder {
            this.insecure = insecure
            return this
        }

        /**
         * Builds and returns a configured [FineractClient] instance.
         *
         * This method validates that all required configuration has been provided
         * and constructs the final client instance. The resulting client is thread-safe
         * and can be reused throughout your application.
         *
         * @return A new [FineractClient] instance configured with the specified settings
         * @throws IllegalStateException if required configuration (baseURL, httpClient) is missing
         * @throws IllegalArgumentException if any configuration values are invalid
         */
        fun build(): FineractClient {
            val ktorfitBuilder = Ktorfit.Builder()
                .httpClient(requireNotNull(httpClient) { "httpClient must be set" })
                .baseUrl(baseURL)
                .build()

            return FineractClient(ktorfitBuilder)
        }
    }

    companion object {
        /**
         * Constant to be used in requests where Fineract's API requires a dateFormat to be given. This matches the format
         * in which LocalDate instances are serialized. (BTW: In a Java client API, it seems weird to have strong LocalDate
         * (not String) instances, and then have to specify its format, see
         * https://issues.apache.org/jira/browse/FINERACT-1233.)
         */
        // Matching org.apache.fineract.client.util.JSON.LocalDateTypeAdapter.formatter
        const val DATE_FORMAT: String = "yyyy-MM-dd"

        fun builder(): Builder {
            return Builder()
        }
    }
}
