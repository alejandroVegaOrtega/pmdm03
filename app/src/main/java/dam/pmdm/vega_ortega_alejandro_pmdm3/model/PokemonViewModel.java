package dam.pmdm.vega_ortega_alejandro_pmdm3.model;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokemonViewModel extends ViewModel {

    private final MutableLiveData<List<PokemonListResponse.Pokemon>> capturedPokemonList = new MutableLiveData<>();
    private final MutableLiveData<Map<String, Boolean>> capturedPokemonMap = new MutableLiveData<>(); // Nueva propiedad para el estado

    public PokemonViewModel() {
        capturedPokemonList.setValue(new ArrayList<>());
        capturedPokemonMap.setValue(new HashMap<>()); // Inicializamos el mapa
    }

    // Getter para obtener la lista de Pokémon capturados
    public LiveData<List<PokemonListResponse.Pokemon>> getCapturedPokemonList() {
        return capturedPokemonList;
    }

    // Getter para el estado del mapa de capturados
    public LiveData<Map<String, Boolean>> getCapturedPokemonMap() {
        return capturedPokemonMap;
    }

    // Método para agregar un Pokémon a la lista de capturados
    public void addCapturedPokemon(PokemonListResponse.Pokemon pokemon) {
        List<PokemonListResponse.Pokemon> currentList = capturedPokemonList.getValue();
        Map<String, Boolean> currentMap = capturedPokemonMap.getValue();

        if (currentList != null && currentMap != null) {
            currentList.add(pokemon);
            currentMap.put(pokemon.getName(), true); // Lo marcamos como capturado

            capturedPokemonList.setValue(currentList); // Notificamos el cambio
            capturedPokemonMap.setValue(currentMap); // Notificamos el cambio
        }
    }

    // Método para eliminar un Pokémon de la lista de capturados
    public void removeCapturedPokemon(PokemonListResponse.Pokemon pokemon) {
        List<PokemonListResponse.Pokemon> currentList = capturedPokemonList.getValue();
        Map<String, Boolean> currentMap = capturedPokemonMap.getValue();

        if (currentList != null && currentMap != null) {
            currentList.remove(pokemon);  // Elimina el Pokémon de la lista
            currentMap.put(pokemon.getName(), false);  // Desmarcarlo como capturado

            capturedPokemonList.setValue(currentList);  // Notificar el cambio en la lista
            capturedPokemonMap.setValue(currentMap);    // Notificar el cambio en el mapa
            Log.d("PokemonViewModel", "Lista después de eliminar: " + currentList);
        }
    }
}