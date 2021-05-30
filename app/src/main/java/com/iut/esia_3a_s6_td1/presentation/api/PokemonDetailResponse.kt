package com.iut.esia_3a_s6_td1.presentation.api

data class PokemonDetailResponse(
    var type: List<PokemonSlot>,
    var weight: Int,
    var height: Int,
    var base_experience: Int,
    var order: Int,
    var name: String
)

data class PokemonSlot(
    var slot: Int,
    val type: PokemonType
)

data class PokemonType(
    val name: String,
    val url: String
)