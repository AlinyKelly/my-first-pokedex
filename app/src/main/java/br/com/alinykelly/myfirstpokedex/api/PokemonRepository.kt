package br.com.alinykelly.myfirstpokedex.api


import br.com.alinykelly.myfirstpokedex.api.model.PokemonApiResult
import br.com.alinykelly.myfirstpokedex.api.model.PokemonService
import br.com.alinykelly.myfirstpokedex.api.model.PokemonsApiResult
import br.com.alinykelly.myfirstpokedex.domain.Pokemon
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonRepository {
    private val service: PokemonService

    //https://pokeapi.co/api/v2/pokemon/?limit=151
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    service = retrofit.create(PokemonService::class.java)
    }

    fun listPokemons(limit: Int = 151): PokemonsApiResult? {
        val call = service.listPokemons(limit)

        return call.execute().body()

//        call.enqueue(object : Callback<PokemonsApiResult> {
//            override fun onResponse(
//                call: Call<PokemonsApiResult>,
//                response: Response<PokemonsApiResult>
//            ) {
//                if (response.isSuccessful) {
//                    val body = response.body()
//
//                    body?.results?.let {
//                        Log.d("POKEMON_API", it[0].name)
//                    }
//                }
//
//                Log.d("POKEMON_API", "Pokemons list loaded")
//            }
//
//            override fun onFailure(call: Call<PokemonsApiResult>, t: Throwable) {
//                Log.e("POKEMON_API", "Error loading pokemons list.", t)
//            }
//
//        })
    }

    fun getPokemon(number: Int): PokemonApiResult? {
        val call = service.getPokemon(number)

        return call.execute().body()
    }

}