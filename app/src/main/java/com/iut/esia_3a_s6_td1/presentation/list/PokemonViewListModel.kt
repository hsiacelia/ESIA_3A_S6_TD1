package com.iut.esia_3a_s6_td1.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iut.esia_3a_s6_td1.presentation.Singletons
import com.iut.esia_3a_s6_td1.presentation.api.PokemonListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonViewListModel : ViewModel(){
    val pokemonList : MutableLiveData<List<Pokemon>> = MutableLiveData()

    init{
        callApi()
    }

    private fun callApi(){

        Singletons.pokeApi.getPokemonList("20").enqueue(object : Callback<PokemonListResponse> {

            override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<PokemonListResponse>,
                response: Response<PokemonListResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val pokemonResponse: PokemonListResponse = response.body()!!
                    pokemonList.value = pokemonResponse.results
//                    adapter.updateList(pokemonResponse.results)
                }
            }
        })
    }

}