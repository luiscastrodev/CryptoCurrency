package br.com.example.cryptocurrency.di

import br.com.example.cryptocurrency.common.Constants
import br.com.example.cryptocurrency.data.remote.CoinPaprikaApi
import br.com.example.cryptocurrency.data.repository.CoinRepositoryImpl
import br.com.example.cryptocurrency.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePaprikaApi(): CoinPaprikaApi {

        val httpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(30,TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(httpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaApi): CoinRepository {
        return CoinRepositoryImpl(api)
    }
}