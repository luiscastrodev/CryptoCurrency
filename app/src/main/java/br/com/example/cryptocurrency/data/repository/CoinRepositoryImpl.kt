package br.com.example.cryptocurrency.data.repository

import br.com.example.cryptocurrency.data.remote.CoinPaprikaApi
import br.com.example.cryptocurrency.data.remote.dto.CoinDetailsDto
import br.com.example.cryptocurrency.data.remote.dto.CoinDto
import br.com.example.cryptocurrency.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailsDto {
        return api.getCoinById(coinId)
    }
}