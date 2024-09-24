data class PokemonResponse(
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<TypeSlot>
)

data class TypeSlot(
    val slot: Int,
    val type: Type
)

data class Type(
    val name: String,
    val url: String
)
