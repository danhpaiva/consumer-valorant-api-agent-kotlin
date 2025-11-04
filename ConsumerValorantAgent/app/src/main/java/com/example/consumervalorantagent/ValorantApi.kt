package com.example.consumervalorantagent

import com.example.consumervalorantagent.models.ValorantAgentResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ValorantApi {
    @GET("agents/{uuid}")
    suspend fun getPersonagem(@Path("uuid") uuid: String) : Response<ValorantAgentResponse>
}