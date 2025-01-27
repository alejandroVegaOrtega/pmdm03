package dam.pmdm.vega_ortega_alejandro_pmdm3.model;

import java.io.Serializable;
import java.util.List;

public class PokemonListResponse {
    private List<Pokemon> results;

    public List<Pokemon> getResults() {
        return results;
    }

    public void setResults(List<Pokemon> results) {
        this.results = results;
    }

    public static class Pokemon implements Serializable {
        private String name;
        private String url;
        private double weight; // Peso del Pokémon
        private double height; // Altura del Pokémon
        private List<String> types; // Tipos del Pokémon

        // Getters y Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double height) {
            this.height = height;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }
    }

}