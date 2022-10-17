package br.com.alinykelly.myfirstpokedex.api.model

import br.com.alinykelly.myfirstpokedex.domain.PokemonType

//Listagem de todos os pokemons
data class PokemonsApiResult (
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonResult>
        )

data class PokemonResult(
    val name: String,
    val url: String
)

//Resultado para apenas 1 pokemon
data class PokemonApiResult(
    val id: Int,
    val name: String,
    val types: PokemonTypeSlot
)

data class PokemonTypeSlot(
    val slot: Int,
    val type: PokemonType
)