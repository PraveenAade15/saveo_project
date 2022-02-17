package com.praveen.saveoproject.infra.di

import com.praveen.saveoproject.infra.api.APIClient
import com.praveen.saveoproject.utils.Constant.BASE_URL
import com.praveen.saveoproject.utils.Constant.TIMEOUT_TIME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object NetworkModule {
    @Singleton
    @Provides
    fun netWorkService(): APIClient {
        val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor()
                    ).apply {
                        HttpLoggingInterceptor.Level.BODY
                    }
                    .connectTimeout(TIMEOUT_TIME, TimeUnit.SECONDS)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())

            .build()
        return builder.create(APIClient::class.java)

    }

}

