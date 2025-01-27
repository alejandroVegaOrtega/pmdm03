package dam.pmdm.vega_ortega_alejandro_pmdm3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dam.pmdm.vega_ortega_alejandro_pmdm3.model.PokemonListResponse;



public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

    private List<PokemonListResponse.Pokemon> pokemonList;
    private Map<String, Boolean> capturedPokemonMap = new HashMap<>(); // Mapa de Pokémon capturados
    private List<PokemonListResponse.Pokemon> capturedPokemonList = new ArrayList<>(); // Lista de Pokémon capturados
    private int viewType; // Tipo de vista: Pokédex o Capturados

    // Constantes para los tipos de vista
    public static final int VIEW_TYPE_POKEDEX = 0;
    public static final int VIEW_TYPE_CAPTURADOS = 1;

    private OnPokemonCapturedListener listener; // Listener para capturar Pokémon
    private OnPokemonClickedListener onPokemonClickedListener; // Listener para clic en capturados

    // Constructor del adaptador
    public PokemonAdapter(int viewType) {
        this.viewType = viewType;
    }

    @Override
    public PokemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PokemonViewHolder holder, int position) {
        if (pokemonList != null && position < pokemonList.size()) {
            PokemonListResponse.Pokemon pokemon = pokemonList.get(position);
            holder.nameTextView.setText(pokemon.getName());

            // Manejo según el tipo de vista
            if (viewType == VIEW_TYPE_POKEDEX) {
                // Cambia el fondo según el estado del mapa de capturados
                if (capturedPokemonMap.getOrDefault(pokemon.getName(), false)) {
                    holder.itemView.setBackgroundColor(holder.itemView.getResources().getColor(android.R.color.holo_red_light));
                } else {
                    holder.itemView.setBackgroundColor(holder.itemView.getResources().getColor(android.R.color.transparent));
                }

                // Clic para capturar
                holder.itemView.setOnClickListener(v -> {
                    if (!capturedPokemonMap.getOrDefault(pokemon.getName(), false)) {
                        capturedPokemonMap.put(pokemon.getName(), true);
                        if (listener != null) {
                            listener.onPokemonCaptured(pokemon);
                        }
                        notifyDataSetChanged();
                    }
                });

            } else if (viewType == VIEW_TYPE_CAPTURADOS) {
                // En la vista de capturados, los clics abren detalles
                holder.itemView.setBackgroundColor(holder.itemView.getResources().getColor(android.R.color.transparent));
                holder.itemView.setOnClickListener(v -> {
                    if (onPokemonClickedListener != null) {
                        onPokemonClickedListener.onPokemonClicked(pokemon);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return pokemonList != null ? pokemonList.size() : 0;
    }

    //public void setPokemonList(List<PokemonListResponse.Pokemon> pokemonList) {
      //  this.pokemonList = pokemonList;
        //notifyDataSetChanged();
    //}
    public void setPokemonList(List<PokemonListResponse.Pokemon> pokemonList) {
        this.pokemonList = pokemonList;

        // Reiniciamos el mapa de capturados si es necesario
        for (PokemonListResponse.Pokemon pokemon : pokemonList) {
            if (!capturedPokemonMap.containsKey(pokemon.getName())) {
                capturedPokemonMap.put(pokemon.getName(), false); // Asumimos no capturado por defecto
            }
        }

        notifyDataSetChanged();
    }

    public void setOnPokemonCapturedListener(OnPokemonCapturedListener listener) {
        this.listener = listener;
    }

    public void setOnPokemonClickedListener(OnPokemonClickedListener listener) {
        this.onPokemonClickedListener = listener;
    }

    public static class PokemonViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;

        public PokemonViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textViewPokemonName);
        }
    }

    public interface OnPokemonCapturedListener {
        void onPokemonCaptured(PokemonListResponse.Pokemon pokemon);
    }

    public interface OnPokemonClickedListener {
        void onPokemonClicked(PokemonListResponse.Pokemon pokemon);
    }

    public List<PokemonListResponse.Pokemon> getPokemonList() {
        return pokemonList;
    }

    public void setCapturedPokemonMap(Map<String, Boolean> capturedMap) {
        this.capturedPokemonMap = capturedMap;
        notifyDataSetChanged(); // Actualizar la vista
    }

}
