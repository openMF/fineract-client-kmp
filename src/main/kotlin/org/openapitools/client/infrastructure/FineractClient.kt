package org.openapitools.client.infrastructure

import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
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
import org.openapitools.client.apis.createAccountNumberFormatApi
import org.openapitools.client.apis.createAccountTransfersApi
import org.openapitools.client.apis.createAccountingClosureApi
import org.openapitools.client.apis.createAccountingRulesApi
import org.openapitools.client.apis.createAdhocQueryApiApi
import org.openapitools.client.apis.createAuditsApi
import org.openapitools.client.apis.createAuthenticationHTTPBasicApi
import org.openapitools.client.apis.createBatchAPIApi
import org.openapitools.client.apis.createBulkImportApi
import org.openapitools.client.apis.createBulkLoansApi
import org.openapitools.client.apis.createBusinessDateManagementApi
import org.openapitools.client.apis.createBusinessStepConfigurationApi
import org.openapitools.client.apis.createCacheApi
import org.openapitools.client.apis.createCalendarApi
import org.openapitools.client.apis.createCashierJournalsApi
import org.openapitools.client.apis.createCashiersApi
import org.openapitools.client.apis.createCentersApi
import org.openapitools.client.apis.createChargesApi
import org.openapitools.client.apis.createClientApi
import org.openapitools.client.apis.createClientChargesApi
import org.openapitools.client.apis.createClientCollateralManagementApi
import org.openapitools.client.apis.createClientFamilyMemberApi
import org.openapitools.client.apis.createClientIdentifierApi
import org.openapitools.client.apis.createClientSearchV2Api
import org.openapitools.client.apis.createClientTransactionApi
import org.openapitools.client.apis.createClientsAddressApi
import org.openapitools.client.apis.createCodeValuesApi
import org.openapitools.client.apis.createCodesApi
import org.openapitools.client.apis.createCollateralManagementApi
import org.openapitools.client.apis.createCollectionSheetApi
import org.openapitools.client.apis.createCreditBureauConfigurationApi
import org.openapitools.client.apis.createCurrencyApi
import org.openapitools.client.apis.createDataTablesApi
import org.openapitools.client.apis.createDefaultApi
import org.openapitools.client.apis.createDelinquencyRangeAndBucketsManagementApi
import org.openapitools.client.apis.createDepositAccountOnHoldFundTransactionsApi
import org.openapitools.client.apis.createDeviceRegistrationApi
import org.openapitools.client.apis.createDocumentsApi
import org.openapitools.client.apis.createEntityDataTableApi
import org.openapitools.client.apis.createEntityFieldConfigurationApi
import org.openapitools.client.apis.createExternalAssetOwnersApi
import org.openapitools.client.apis.createExternalEventConfigurationApi
import org.openapitools.client.apis.createExternalServicesApi
import org.openapitools.client.apis.createFetchAuthenticatedUserDetailsApi
import org.openapitools.client.apis.createFineractEntityApi
import org.openapitools.client.apis.createFixedDepositAccountApi
import org.openapitools.client.apis.createFixedDepositAccountTransactionsApi
import org.openapitools.client.apis.createFixedDepositProductApi
import org.openapitools.client.apis.createFloatingRatesApi
import org.openapitools.client.apis.createFundsApi
import org.openapitools.client.apis.createGeneralLedgerAccountApi
import org.openapitools.client.apis.createGlobalConfigurationApi
import org.openapitools.client.apis.createGroupsApi
import org.openapitools.client.apis.createGroupsLevelApi
import org.openapitools.client.apis.createGuarantorsApi
import org.openapitools.client.apis.createHolidaysApi
import org.openapitools.client.apis.createHooksApi
import org.openapitools.client.apis.createInlineJobApi
import org.openapitools.client.apis.createInstanceModeApi
import org.openapitools.client.apis.createInterOperationApi
import org.openapitools.client.apis.createInterestRateChartApi
import org.openapitools.client.apis.createInterestRateSlabAKAInterestBandsApi
import org.openapitools.client.apis.createJournalEntriesApi
import org.openapitools.client.apis.createLikelihoodApi
import org.openapitools.client.apis.createListReportMailingJobHistoryApi
import org.openapitools.client.apis.createLoanAccountLockApi
import org.openapitools.client.apis.createLoanCOBCatchUpApi
import org.openapitools.client.apis.createLoanChargesApi
import org.openapitools.client.apis.createLoanCollateralApi
import org.openapitools.client.apis.createLoanCollateralManagementApi
import org.openapitools.client.apis.createLoanDisbursementDetailsApi
import org.openapitools.client.apis.createLoanProductsApi
import org.openapitools.client.apis.createLoanReschedulingApi
import org.openapitools.client.apis.createLoanTransactionsApi
import org.openapitools.client.apis.createLoansApi
import org.openapitools.client.apis.createMakerCheckerOr4EyeFunctionalityApi
import org.openapitools.client.apis.createMappingFinancialActivitiesToAccountsApi
import org.openapitools.client.apis.createMeetingsApi
import org.openapitools.client.apis.createMixMappingApi
import org.openapitools.client.apis.createMixReportApi
import org.openapitools.client.apis.createMixTaxonomyApi
import org.openapitools.client.apis.createNotesApi
import org.openapitools.client.apis.createNotificationApi
import org.openapitools.client.apis.createOfficesApi
import org.openapitools.client.apis.createPasswordPreferencesApi
import org.openapitools.client.apis.createPaymentTypeApi
import org.openapitools.client.apis.createPeriodicAccrualAccountingApi
import org.openapitools.client.apis.createPermissionsApi
import org.openapitools.client.apis.createPocketApi
import org.openapitools.client.apis.createPovertyLineApi
import org.openapitools.client.apis.createProductMixApi
import org.openapitools.client.apis.createProductsApi
import org.openapitools.client.apis.createProvisioningCategoryApi
import org.openapitools.client.apis.createProvisioningCriteriaApi
import org.openapitools.client.apis.createProvisioningEntriesApi
import org.openapitools.client.apis.createRateApi
import org.openapitools.client.apis.createRecurringDepositAccountApi
import org.openapitools.client.apis.createRecurringDepositAccountTransactionsApi
import org.openapitools.client.apis.createRecurringDepositProductApi
import org.openapitools.client.apis.createRepaymentWithPostDatedChecksApi
import org.openapitools.client.apis.createReportMailingJobsApi
import org.openapitools.client.apis.createReportsApi
import org.openapitools.client.apis.createRescheduleLoansApi
import org.openapitools.client.apis.createRolesApi
import org.openapitools.client.apis.createRunReportsApi
import org.openapitools.client.apis.createSCHEDULERJOBApi
import org.openapitools.client.apis.createSMSApi
import org.openapitools.client.apis.createSPMAPILookUpTableApi
import org.openapitools.client.apis.createSavingsAccountApi
import org.openapitools.client.apis.createSavingsAccountTransactionsApi
import org.openapitools.client.apis.createSavingsChargesApi
import org.openapitools.client.apis.createSavingsProductApi
import org.openapitools.client.apis.createSchedulerApi
import org.openapitools.client.apis.createScoreCardApi
import org.openapitools.client.apis.createSearchAPIApi
import org.openapitools.client.apis.createSelfAccountTransferApi
import org.openapitools.client.apis.createSelfAuthenticationApi
import org.openapitools.client.apis.createSelfClientApi
import org.openapitools.client.apis.createSelfDividendApi
import org.openapitools.client.apis.createSelfLoanProductsApi
import org.openapitools.client.apis.createSelfLoansApi
import org.openapitools.client.apis.createSelfRunReportApi
import org.openapitools.client.apis.createSelfSavingsAccountApi
import org.openapitools.client.apis.createSelfSavingsProductsApi
import org.openapitools.client.apis.createSelfScoreCardApi
import org.openapitools.client.apis.createSelfServiceRegistrationApi
import org.openapitools.client.apis.createSelfShareAccountsApi
import org.openapitools.client.apis.createSelfSpmApi
import org.openapitools.client.apis.createSelfThirdPartyTransferApi
import org.openapitools.client.apis.createSelfUserApi
import org.openapitools.client.apis.createSelfUserDetailsApi
import org.openapitools.client.apis.createShareAccountApi
import org.openapitools.client.apis.createSpmSurveysApi
import org.openapitools.client.apis.createStaffApi
import org.openapitools.client.apis.createStandingInstructionsApi
import org.openapitools.client.apis.createStandingInstructionsHistoryApi
import org.openapitools.client.apis.createSurveyApi
import org.openapitools.client.apis.createTaxComponentsApi
import org.openapitools.client.apis.createTaxGroupApi
import org.openapitools.client.apis.createTellerCashManagementApi
import org.openapitools.client.apis.createTwoFactorApi
import org.openapitools.client.apis.createUserGeneratedDocumentsApi
import org.openapitools.client.apis.createUsersApi
import org.openapitools.client.apis.createWorkingDaysApi
import retrofit2.Retrofit
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 * Fineract Client Kotlin SDK API entry point. Use this instead of the {@link ApiClient}.
 *
 * @author Aditya Gupta
 */

class FineractClient private constructor(
    private val httpClient: HttpClient,
    private val ktorfit: Ktorfit
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

    fun httpClient(): HttpClient {
        return this.httpClient
    }

    fun baseURL(): String {
        return ktorfit.baseUrl
    }

    /**
     * Create an implementation of the API endpoints defined by the `service` interface, using
     * [Retrofit.create]. This method is typically not required to be invoked for standard API usage, but
     * can be a handy back door for non-trivial advanced customizations of the API client if you have extended Fineract
     * with your own REST APIs.
     */

    class Builder internal constructor() {
        /**
         * Obtain the internal OkHttp Builder. This method is typically not required to be invoked for simple API
         * usages, but can be a handy back door for non-trivial advanced customizations of the API client.
         *
         * @return the [ApiClient] which [.build] will use.
         */

        /**
         * Obtain the internal Retrofit Builder. This method is typically not required to be invoked for simple API
         * usages, but can be a handy back door for non-trivial advanced customizations of the API client.
         *
         * @return the [ApiClient] which [.build] will use.
         */

        private lateinit var baseURL: String
        private var tenant: String? = null
        private var loginUsername: String? = null
        private var loginPassword: String? = null
        private var insecure: Boolean = false

        fun baseURL(baseURL: String): Builder {
            this.baseURL = baseURL
            return this
        }

        fun tenant(tenant: String?): Builder {
            this.tenant = tenant
            return this
        }

        fun basicAuth(username: String?, password: String?): Builder {
            this.loginUsername = username
            this.loginPassword = password
            return this
        }

        fun inSecure(insecure: Boolean): Builder {
            this.insecure = insecure
            return this
        }

        fun build(): FineractClient {

            val ktorClient = HttpClient(CIO) {
                install(ContentNegotiation) {
                    json(Json {
                        isLenient = true
                        ignoreUnknownKeys = true
                    })
                }
                install(Logging) {
                    logger = Logger.DEFAULT
                    level = LogLevel.INFO
                }
                install(Auth) {
                    basic {
                        credentials {
                            BasicAuthCredentials(
                                username = loginUsername.toString(),
                                password = loginPassword.toString()
                            )
                        }
                    }
                }

                defaultRequest {
                    contentType(ContentType.Application.Json)
                    headers {
                        append("Accept", "application/json")
                        tenant?.let {
                            append("fineract-platform-tenantid", it)
                        }
                    }
                }

                if (insecure) {
                    engine {
                        https {
                            val insecureTrustManager = object : X509TrustManager {
                                override fun checkClientTrusted(
                                    chain: Array<X509Certificate>,
                                    authType: String
                                ) {
                                }

                                override fun checkServerTrusted(
                                    chain: Array<X509Certificate>,
                                    authType: String
                                ) {
                                }

                                override fun getAcceptedIssuers(): Array<X509Certificate> =
                                    arrayOf()
                            }

                            try {
                                val sslContext = SSLContext.getInstance("SSL").apply {
                                    init(
                                        null,
                                        arrayOf<TrustManager>(insecureTrustManager),
                                        SecureRandom()
                                    )
                                }
                                trustManager = insecureTrustManager
                            } catch (e: NoSuchAlgorithmException) {
                                throw IllegalStateException("SSL configuration failed", e)
                            } catch (e: KeyManagementException) {
                                throw IllegalStateException("SSL configuration failed", e)
                            }
                        }
                    }
                }
            }

            val ktorfitBuilder = Ktorfit.Builder()
                .httpClient(ktorClient)
                .baseUrl(baseURL)
                .build()

            return FineractClient(ktorClient, ktorfitBuilder)
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