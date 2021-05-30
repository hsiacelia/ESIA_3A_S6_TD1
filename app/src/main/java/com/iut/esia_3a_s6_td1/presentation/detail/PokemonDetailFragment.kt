package com.iut.esia_3a_s6_td1.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.iut.esia_3a_s6_td1.R
import com.iut.esia_3a_s6_td1.presentation.Singletons
import com.iut.esia_3a_s6_td1.presentation.api.PokemonDetailResponse
import com.iut.esia_3a_s6_td1.presentation.api.PokemonListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonDetailFragment : Fragment() {

    private lateinit var textViewName: TextView
    private lateinit var textViewHeight: TextView
    private lateinit var textViewWeight: TextView
//    private lateinit var textViewType: TextView



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        view.findViewById<Button>(R.id.button_second).setOnClickListener {
//            findNavController().navigate(R.id.navigateToPokemonListFragment)
//        }

        textViewName = view.findViewById(R.id.pokemon_detail_name)
        textViewHeight = view.findViewById(R.id.pokemon_detail_height)
        textViewWeight = view.findViewById(R.id.pokemon_detail_weight)
//        textViewType = view.findViewById(R.id.pokemon_detail_type)

        callApi()

    }

    private fun callApi(){
        val id: Int = arguments?.getInt("pokemonId") ?: -1
        Singletons.pokeApi.getPokemonDetail(id).enqueue(object : Callback<PokemonDetailResponse>{
            override fun onFailure(call: Call<PokemonDetailResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse( call: Call<PokemonDetailResponse>, response: Response<PokemonDetailResponse>) {
                if(response.isSuccessful && response.body() != null){
                    textViewName.text = "Name : " + response.body()!!.name
                    textViewHeight.text = "Height : " +  response.body()!!.height.toString()
                    textViewWeight.text = "Weight : " +  response.body()!!.weight.toString()
//                    textViewType.text = "Type : " +  response.body()!!.type.elementAt(1).type.name

                }
            }
        })
    }

}
