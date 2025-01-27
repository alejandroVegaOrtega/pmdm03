package dam.pmdm.vega_ortega_alejandro_pmdm3.model;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import dam.pmdm.vega_ortega_alejandro_pmdm3.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PokemonDetailsActivity extends AppCompatActivity {

    private TextView pokemonNameTextView;
    private TextView pokemonWeightTextView;
    private TextView pokemonHeightTextView;
    private TextView pokemonTypesTextView;
    private Button deleteButton;

    private PokemonListResponse.Pokemon selectedPokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_details);

        // Enlazar las vistas
        pokemonNameTextView = findViewById(R.id.textViewPokemonName);
        pokemonWeightTextView = findViewById(R.id.textViewPokemonWeight);
        pokemonHeightTextView = findViewById(R.id.textViewPokemonHeight);
        pokemonTypesTextView = findViewById(R.id.textViewPokemonTypes);
        deleteButton = findViewById(R.id.buttonDeletePokemon);

        // Obtener el Pokémon desde el Intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("selected_pokemon")) {
            selectedPokemon = (PokemonListResponse.Pokemon) intent.getSerializableExtra("selected_pokemon");

            if (selectedPokemon != null) {
                pokemonNameTextView.setText(selectedPokemon.getName());
                fetchPokemonDetails(selectedPokemon.getName());
                deleteButton.setVisibility(View.VISIBLE);
            }
        }

        // Configurar el botón de eliminación
        deleteButton.setOnClickListener(v -> {
            // Actualiza el ViewModel para eliminar el Pokémon
            PokemonViewModel pokemonViewModel = new ViewModelProvider(PokemonDetailsActivity.this).get(PokemonViewModel.class);
            pokemonViewModel.removeCapturedPokemon(selectedPokemon);  // Eliminar el Pokémon del ViewModel

            // Crear un Intent para enviar el Pokémon eliminado de vuelta a la actividad principal
            Intent resultIntent = new Intent();
            resultIntent.putExtra("deleted_pokemon", selectedPokemon);
            setResult(RESULT_OK, resultIntent);

            finish(); // Finaliza la actividad y regresa el resultado
        });
    }

    private void fetchPokemonDetails(String pokemonName) {
        // Crear instancia del servicio API
        PokemonApiService apiService = RetrofitClient.getPokemonApiService();

        // Llamar al endpoint para obtener detalles
        apiService.getPokemonDetails(pokemonName).enqueue(new Callback<PokemonDetailsResponse>() {
            @Override
            public void onResponse(Call<PokemonDetailsResponse> call, Response<PokemonDetailsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    PokemonDetailsResponse details = response.body();

                    // Actualizar las vistas con los datos recibidos
                    pokemonWeightTextView.setText("Peso: " + details.getWeight() + " kg");
                    pokemonHeightTextView.setText("Altura: " + details.getHeight() + " m");

                    // Obtener y mostrar los tipos
                    List<String> typeNames = new ArrayList<>();
                    for (PokemonDetailsResponse.Type type : details.getTypes()) {
                        typeNames.add(type.getType().getName());
                    }
                    pokemonTypesTextView.setText("Tipos: " + String.join(", ", typeNames));
                } else {
                    // Manejar la respuesta no exitosa
                    Toast.makeText(PokemonDetailsActivity.this, "Error al cargar los detalles del Pokémon", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PokemonDetailsResponse> call, Throwable t) {
                // Manejar errores de la red o del servidor
                Toast.makeText(PokemonDetailsActivity.this, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}