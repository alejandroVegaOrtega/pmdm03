package dam.pmdm.vega_ortega_alejandro_pmdm3.ui.capturados;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dam.pmdm.vega_ortega_alejandro_pmdm3.PokemonAdapter;
import dam.pmdm.vega_ortega_alejandro_pmdm3.R;

import dam.pmdm.vega_ortega_alejandro_pmdm3.model.PokemonDetailsActivity;
import dam.pmdm.vega_ortega_alejandro_pmdm3.model.PokemonListResponse;
import dam.pmdm.vega_ortega_alejandro_pmdm3.model.PokemonViewModel;
import dam.pmdm.vega_ortega_alejandro_pmdm3.ui.pokedex.PokedexFragment;

public class capturadosFragment extends Fragment {

    private RecyclerView recyclerView;
    private PokemonAdapter pokemonAdapter;
    private PokemonViewModel pokemonViewModel;

    public capturadosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_capturados, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewCapturedPokemon);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Inicializamos el adaptador para la vista de capturados
        pokemonAdapter = new PokemonAdapter(PokemonAdapter.VIEW_TYPE_CAPTURADOS);
        recyclerView.setAdapter(pokemonAdapter);

        // Obtener la instancia del ViewModel
        pokemonViewModel = new ViewModelProvider(requireActivity()).get(PokemonViewModel.class);

        // Observar los cambios en la lista de Pokémon capturados
        pokemonViewModel.getCapturedPokemonList().observe(getViewLifecycleOwner(), new Observer<List<PokemonListResponse.Pokemon>>() {
            @Override
            public void onChanged(List<PokemonListResponse.Pokemon> capturedPokemonList) {
                // Actualizamos la lista en el adaptador
                pokemonAdapter.setPokemonList(capturedPokemonList);
            }
        });

        pokemonAdapter.setOnPokemonClickedListener(pokemon -> {
            Intent intent = new Intent(getContext(), PokemonDetailsActivity.class);
            intent.putExtra("selected_pokemon", pokemon); // Enviamos el Pokémon seleccionado
            pokemonDetailsLauncher.launch(intent);
        });

        // Configurar eliminación al deslizar un ítem
        setupItemTouchHelper();

        return view;
    }

    // Método para configurar la eliminación al deslizar
    private void setupItemTouchHelper() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false; // No permitimos mover ítems
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                PokemonListResponse.Pokemon removedPokemon = pokemonAdapter.getPokemonList().get(position);

                // Eliminamos el Pokémon desde el ViewModel
                pokemonViewModel.removeCapturedPokemon(removedPokemon);

                // Notificar al adaptador actual
                pokemonAdapter.notifyItemRemoved(position);
            }
        });

        // Asignar el ItemTouchHelper al RecyclerView
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    // Configuramos el launcher para manejar el resultado de eliminar Pokémon
    private final ActivityResultLauncher<Intent> pokemonDetailsLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null && data.hasExtra("deleted_pokemon")) {
                        PokemonListResponse.Pokemon deletedPokemon = (PokemonListResponse.Pokemon) data.getSerializableExtra("deleted_pokemon");

                        // Eliminamos el Pokémon desde el ViewModel
                        pokemonViewModel.removeCapturedPokemon(deletedPokemon);
                    }
                }
            }
    );
}