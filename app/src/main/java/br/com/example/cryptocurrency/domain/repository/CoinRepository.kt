package br.com.example.cryptocurrency.domain.repository

import br.com.example.cryptocurrency.data.remote.dto.CoinDetailsDto
import br.com.example.cryptocurrency.data.remote.dto.CoinDto

interface CoinRepository {
    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoinById(coinId: String): CoinDetailsDto
}