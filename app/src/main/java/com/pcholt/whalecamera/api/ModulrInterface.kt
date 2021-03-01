package com.pcholt.whalecamera.api

import retrofit2.Call
import retrofit2.http.*
import java.math.BigDecimal

interface ModulrInterface {

    @POST("payment-initiations")
    fun get(
        @HeaderMap headers: Map<String, String>,
        @Body body: PispRequest,
    ): Call<PaymentInitiationResponse>

    @GET("payment-initiations/{paymentInitiationId}")
    fun getPaymentInitiations(
        @HeaderMap headers: Map<String, String>,
        @Path("paymentInitiationId") paymentInitiationId: String
    ): Call<PispDetails>

}

data class PispDetails(
    val aspspId: String,
    val aspspPaymentStatus: String,
    val destination: PispDestination,
    val id: String,
    val paymentAmount: PispAmount,
    val paymentReference: String,
    val status: String,
)

data class PispRequest(
    val aspspId: String,
    val destination: PispDestination,
    val paymentAmount: PispAmount,
    val paymentContext: PispContext,
    val paymentReference: String
)

data class PispContext(
    val deliveryAddress: Address,
    val merchantCategoryCode: String,
    val merchantCustomerIdentification: String,
    val paymentContextCode: String
)

data class Address(
    val addressLine1: String,
    val addressLine2: String,
    val country: String,
    val postCode: String,
    val postTown: String
)

data class PispAmount(
    val currency: String,
    val value: BigDecimal
)

data class PispDestination(
    val accountNumber: String,
    val id: String,
    val name: String,
    val sortCode: String,
    val type: String
)

data class PaymentInitiationResponse(
    val paymentInitiationId: String,
    val redirectUrl: String
)
