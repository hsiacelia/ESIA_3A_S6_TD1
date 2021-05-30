package com.iut.esia_3a_s6_td1.presentation.api

import com.iut.esia_3a_s6_td1.presentation.list.Pokemon

data class PokemonListResponse(
    val count: Int,
    val next: String,
    val results: List<Pokemon>
)