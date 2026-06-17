package entities;

public class PokemonLegendary extends Pokemon {
    public PokemonLegendary(String name, int maxHp, int hp, int strength, int level) {
        super(name, maxHp, hp, strength, level);
    }

    @Override
    public boolean pokemonBattle(Pokemon opponent) {
        return false;
    }
}
