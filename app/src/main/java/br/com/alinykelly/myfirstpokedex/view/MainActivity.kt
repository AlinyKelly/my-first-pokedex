package br.com.alinykelly.myfirstpokedex.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alinykelly.myfirstpokedex.R
import br.com.alinykelly.myfirstpokedex.api.PokemonRepository
import br.com.alinykelly.myfirstpokedex.domain.Pokemon
import br.com.alinykelly.myfirstpokedex.domain.PokemonType

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvPokemons)

//        val charmander = Pokemon(
//            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/004.png",
//            4,
//            "Charmander",
//            listOf(
//                PokemonType("Fire")
//            )
//        )
//        val pokemons = listOf(
//            charmander, charmander, charmander, charmander, charmander
//        )

        //Criar a propria thread pra fazer a requisição
        Thread(Runnable {
            loadPokemons()
        }).start()

    }

    private fun loadPokemons() {
       val pokemonsApiResult = PokemonRepository.listPokemons()

        pokemonsApiResult?.results?.let {

            val pokemons: List<Pokemon> = it.map {
                pokemonResult ->
                Pokemon(
                    "",
                    1,
                    pokemonResult.name,
                    listOf(PokemonType("Fire"))
                )
            }

            val layoutManager = LinearLayoutManager(this)

            recyclerView.post {
                recyclerView.layoutManager = layoutManager
                recyclerView.adapter = PokemonAdapter(pokemons)
            }
        }
    }
}