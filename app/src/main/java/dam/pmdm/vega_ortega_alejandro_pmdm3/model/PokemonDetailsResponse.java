package dam.pmdm.vega_ortega_alejandro_pmdm3.model;

import java.util.List;

public class PokemonDetailsResponse {
    private int weight;
    private int height;
    private List<Type> types;

    // Getters y setters
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public static class Type {
        private TypeDetails type;

        public TypeDetails getType() {
            return type;
        }

        public void setType(TypeDetails type) {
            this.type = type;
        }
    }

    public static class TypeDetails {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
