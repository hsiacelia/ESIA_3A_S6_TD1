package com.iut.esia_3a_s6_td1.presentation

import com.iut.esia_3a_s6_td1.presentation.PokemonApplication.Companion.context
import com.iut.esia_3a_s6_td1.presentation.api.PokeApi
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class Singletons{
    companion object{ //static function

        var cache : Cache = Cache(File(context?.cacheDir, "responses"), 10*1024*1024) // => 10mib

        val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
            .cache(cache)
            .build()

        val pokeApi: PokeApi = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(PokeApi::class.java)
    }
}