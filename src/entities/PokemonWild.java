package entities;

public final class PokemonWild extends Pokemon {
    private int coins; // o pokemonWild da moedas se for derrotado

    public PokemonWild(String name, int maxHp, int hp, int strength, int level, int coins) {
        super(name, maxHp, hp, strength, level);
        this.coins = coins;
    }

    @Override
    public boolean pokemonBattle(Pokemon opponent) {
        return false;
    }

    public int getCoins() {
        return coins;
    }
    public void setCoins(int coins) {}
}
