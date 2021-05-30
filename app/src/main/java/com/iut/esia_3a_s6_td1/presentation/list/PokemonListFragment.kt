package com.iut.esia_3a_s6_td1.presentation.list

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iut.esia_3a_s6_td1.R
import com.iut.esia_3a_s6_td1.presentation.Singletons
import com.iut.esia_3a_s6_td1.presentation.api.PokeApi
import com.iut.esia_3a_s6_td1.presentation.api.PokemonListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = PokemonAdapter(listOf<Pokemon>(), ::onClickedPokemon)

    private val sharedPreference: SharedPreferences? = activity?.getSharedPreferences("app",Context.MODE_PRIVATE)
    private val viewModel: PokemonViewListModel by viewModels()

//    private val layoutManager = LinearLayoutManager(context)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.pokemon_recycleview)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@PokemonListFragment.adapter
        }

        viewModel.pokemonList.observe(viewLifecycleOwner, Observer {list ->
            adapter.updateList(list)
        })

        callApi()

//        val list: List<Pokemon> = getListFromCache()
//        if(list.isEmpty()){
//            callApi()
//        }else{
//            showList(list)
//        }


    }

    private fun callApi() {
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
//                    adapter.updateList(pokemonResponse.results)
//                    saveListIntoCache()
                    showList(pokemonResponse.results)
                }
            }
        })
    }
    private fun saveListIntoCache(){

    }

//    private fun getListFromCache(): List<Pokemon>{
//       //TODO
//        return sharedPreference
//    }

    private fun showList(pokemonList: List<Pokemon>){
        adapter.updateList(pokemonList)
    }

    private fun onClickedPokemon(id: Int) {
        findNavController().navigate(R.id.navigateToPokemonDetailFragment, bundleOf(
            "pokemonId" to (id + 1)
        ))
    }

}
