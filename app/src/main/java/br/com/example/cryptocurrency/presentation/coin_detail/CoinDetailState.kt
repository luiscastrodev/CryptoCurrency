package br.com.example.cryptocurrency.presentation.coin_detail

import br.com.example.cryptocurrency.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading : Boolean = false,
    val coin:CoinDetail? = null,
    val error: String = ""
)
