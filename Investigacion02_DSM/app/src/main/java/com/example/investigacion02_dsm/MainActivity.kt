package com.example.investigacion02_dsm

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.investigacion02_dsm.data.PokemonResponse
import com.example.investigacion02_dsm.data.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Ajustar padding para soportar edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Enlazar las vistas con el código
        val etPokemonName = findViewById<EditText>(R.id.etPokemonName)
        val btnSearchPokemon = findViewById<Button>(R.id.btnSearchPokemon)
        val tvPokemonName = findViewById<TextView>(R.id.tvPokemonName)
        val tvPokemonHeight = findViewById<TextView>(R.id.tvPokemonHeight)
        val tvPokemonWeight = findViewById<TextView>(R.id.tvPokemonWeight)

        // Asignar el evento al botón de búsqueda
        btnSearchPokemon.setOnClickListener {
            val pokemonName = etPokemonName.text.toString().trim()
            if (pokemonName.isNotEmpty()) {
                fetchPokemonDetails(pokemonName, tvPokemonName, tvPokemonHeight, tvPokemonWeight)
            } else {
                // Si el campo está vacío, mostramos un mensaje de error
                tvPokemonName.text = "Por favor, ingrese un nombre válido."
            }
        }
    }

    // Función para hacer la llamada a la API usando corutinas
    private fun fetchPokemonDetails(
        pokemonName: String,
        tvName: TextView,
        tvHeight: TextView,
        tvWeight: TextView
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Llamada a la API usando corutinas
                val pokemon = RetrofitInstance.api.getPokemonDetails(pokemonName)

                // Actualizar la UI en el hilo principal
                withContext(Dispatchers.Main) {
                    if (pokemon != null) {
                        // Actualizar las vistas con los datos obtenidos
                        tvName.text = "Nombre: ${pokemon.name}"
                        tvHeight.text = "Altura: ${pokemon.height}"
                        tvWeight.text = "Peso: ${pokemon.weight}"
                    } else {
                        tvName.text = "No se encontró el Pokémon."
                    }
                }
            } catch (e: Exception) {
                // Manejo de errores
                withContext(Dispatchers.Main) {
                    tvName.text = "Error en la solicitud: ${e.message}"
                }
            }
        }
    }
}
