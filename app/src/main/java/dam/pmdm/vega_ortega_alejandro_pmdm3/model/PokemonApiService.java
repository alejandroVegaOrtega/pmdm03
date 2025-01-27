package dam.pmdm.vega_ortega_alejandro_pmdm3.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokemonApiService {

    @GET("pokemon")
    Call<PokemonListResponse> getPokemonList(@Query("offset") int offset, @Query("limit") int limit);

    // Nuevo endpoint para obtener los detalles de un Pokémon
    @GET("pokemon/{pokemonName}")
    Call<PokemonDetailsResponse> getPokemonDetails(@Path("pokemonName") String pokemonName);
}