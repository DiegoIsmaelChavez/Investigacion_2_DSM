package com.example.investigacion02_dsm.data

import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {
    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(@Path("name") pokemonName: String): PokemonResponse
}

