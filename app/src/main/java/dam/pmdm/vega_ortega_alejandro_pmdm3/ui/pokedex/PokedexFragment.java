package dam.pmdm.vega_ortega_alejandro_pmdm3.ui.pokedex;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dam.pmdm.vega_ortega_alejandro_pmdm3.PokemonAdapter;
import dam.pmdm.vega_ortega_alejandro_pmdm3.R;
import dam.pmdm.vega_ortega_alejandro_pmdm3.model.PokemonApiService;
import dam.pmdm.vega_ortega_alejandro_pmdm3.model.PokemonListResponse;
import dam.pmdm.vega_ortega_alejandro_pmdm3.model.PokemonViewModel;
import dam.pmdm.vega_ortega_alejandro_pmdm3.model.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokedexFragment extends Fragment implements PokemonAdapter.OnPokemonCapturedListener {

    private RecyclerView recyclerView;
    private PokemonAdapter pokemonAdapter;
    private List<PokemonListResponse.Pokemon> pokemonList;

    private PokemonViewModel pokemonViewModel;

    public PokedexFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokedex, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewPokemon);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        pokemonAdapter = new PokemonAdapter(PokemonAdapter.VIEW_TYPE_POKEDEX);
        pokemonAdapter.setOnPokemonCapturedListener(this);
        recyclerView.setAdapter(pokemonAdapter);

        // Obtener la instancia del ViewModel
        pokemonViewModel = new ViewModelProvider(requireActivity()).get(PokemonViewModel.class);

        // Obtenemos los Pokémon de la API
        loadPokemonList();

        // Observar cambios en el estado de capturados
        pokemonViewModel.getCapturedPokemonMap().observe(getViewLifecycleOwner(), new Observer<Map<String, Boolean>>() {
            @Override
            public void onChanged(Map<String, Boolean> capturedMap) {
                pokemonAdapter.setCapturedPokemonMap(capturedMap); // Sincronizar el mapa de capturados
            }
        });

        return view;
    }


    private void loadPokemonList() {
        PokemonApiService apiService = RetrofitClient.getPokemonApiService();
        apiService.getPokemonList(0, 150).enqueue(new Callback<PokemonListResponse>() {
            @Override
            public void onResponse(Call<PokemonListResponse> call, Response<PokemonListResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    pokemonList = response.body().getResults();
                    pokemonAdapter.setPokemonList(pokemonList);
                }
            }

            @Override
            public void onFailure(Call<PokemonListResponse> call, Throwable t) {
                // Manejar error
            }
        });
    }

    @Override
    public void onPokemonCaptured(PokemonListResponse.Pokemon pokemon) {
        // Añadir el Pokémon capturado al ViewModel
        pokemonViewModel.addCapturedPokemon(pokemon);
    }

    public PokemonAdapter getPokemonAdapter() {
        return pokemonAdapter;
    }
}