package br.com.example.cryptocurrency.data.remote

import br.com.example.cryptocurrency.data.remote.dto.CoinDetailsDto
import br.com.example.cryptocurrency.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coindId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailsDto
}