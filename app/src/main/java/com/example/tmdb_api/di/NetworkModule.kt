package com.example.tmdb_api.di

import com.example.tmdb_api.data.Remote.Tmdb_Api
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://streaming-availability.p.rapidapi.com/"
    private const val RAPID_API_KEY = "df4b62a662msh177a234b1a805afp1a865bjsneeadc3672ee3"
    private const val RAPID_API_HOST = "streaming-availability.p.rapidapi.com"

    @Provides
    fun providesOkHttpClient(): OkHttpClient {

            return OkHttpClient.Builder().addInterceptor { chain ->
                val originalRequest = chain.request()
                val newRequest =
                    originalRequest.newBuilder()
                        .addHeader("X-RapidAPI-Key", RAPID_API_KEY)
                        .addHeader("X-RapidAPI-Host", RAPID_API_HOST)

                        .build()
                chain.proceed(newRequest)
            }.build()
    }

    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder().client(okHttpClient).baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    }

    @Provides
    fun provideBantuApi(retrofit: Retrofit): Tmdb_Api {
        return retrofit.create(Tmdb_Api::class.java)
    }


}