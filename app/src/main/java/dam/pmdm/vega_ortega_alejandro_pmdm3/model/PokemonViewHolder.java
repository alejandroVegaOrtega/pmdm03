package dam.pmdm.vega_ortega_alejandro_pmdm3.model;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import dam.pmdm.vega_ortega_alejandro_pmdm3.R;

public class PokemonViewHolder extends RecyclerView.ViewHolder {
    TextView pokemonName;

    public PokemonViewHolder(View itemView) {
        super(itemView);
        // Asegúrate de que el ID coincide con el del XML
        pokemonName = itemView.findViewById(R.id.textViewPokemonName);
    }

    public void bind(PokemonListResponse.Pokemon pokemon) {
        pokemonName.setText(pokemon.getName());
    }
}