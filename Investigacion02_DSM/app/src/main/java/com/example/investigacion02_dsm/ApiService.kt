import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

// Definición de la interfaz ApiService para la PokeAPI
interface ApiService {

    // Método para obtener detalles de un Pokémon específico por nombre
    @GET("pokemon/{name}")
    fun getPokemonDetails(
        @Path("name") name: String
    ): Call<PokemonResponse>

}
