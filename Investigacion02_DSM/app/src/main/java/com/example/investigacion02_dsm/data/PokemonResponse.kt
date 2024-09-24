package com.example.investigacion02_dsm.data

data class PokemonResponse(
    val name: String,
    val height: Int,
    val weight: Int,
    val abilities: List<Ability>
)

data class Ability(
    val ability: AbilityDetail
)

data class AbilityDetail(
    val name: String
)
