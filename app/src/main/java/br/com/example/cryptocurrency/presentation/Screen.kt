package br.com.example.cryptocurrency.presentation

sealed class Screen(val route : String){
    object CoinListSreen : Screen("coin_list_screen")
    object CoinDetailSreen : Screen("coin_detail_screen")
}
