package com.citypulse.app.data.remote.network

import com.citypulse.app.BuildConfig
import com.citypulse.app.data.remote.api.PlaceApiService
import com.citypulse.app.utils.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// singleton qui fournit le client réseau et le service Retrofit à toute l'application
object NetworkModule {

    private const val TIMEOUT = 30L

    // setLenient() pour éviter les crashs si l'API renvoie un JSON légèrement malformé
    private val gson by lazy {
        GsonBuilder().setLenient().create()
    }

    // on affiche les requêtes et réponses dans le Logcat seulement en mode debug
    private val logging by lazy {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
        }
    }

    val okHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
    }

    val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    // point d'entrée pour faire des appels API dans le reste de l'app
    val placeApiService: PlaceApiService by lazy {
        retrofit.create(PlaceApiService::class.java)
    }
}
