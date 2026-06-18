package entities;

public final class PokemonWild extends Pokemon {
    private int coins; // o pokemonWild da moedas se for derrotado

    public PokemonWild(String name, int maxHp, int hp, int strength, int level, int exp, int coins) {
        super(name, maxHp, hp, strength, level, exp);
        this.coins = coins;
    }

    public int getCoins() {
        return coins;
    }
    public void setCoins(int coins) {
        this.coins = coins;
    }
}
