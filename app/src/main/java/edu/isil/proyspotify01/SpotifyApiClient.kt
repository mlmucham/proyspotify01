package edu.isil.proyspotify01

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SpotifyApiClient {
    private const val BASE_URL = "https://api.spotify.com/"

    val instance: SpotifyApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(SpotifyApiService::class.java)
    }
}
