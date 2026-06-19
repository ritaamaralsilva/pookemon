package entities;

import java.util.Random;

public class PokemonWild extends Pokemon {
    private int coins; // o pokemonWild da moedas se for derrotado
    private String specialMoveName; // exemplo: "Water Gun", "Ember", "Bubblebeam"

    public PokemonWild(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp, int coins) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp);
        this.coins = coins;
        this.specialMoveName = specialMoveName;
    }

    public int getCoins() {
        return coins;
    }
    public void setCoins(int coins) {
        this.coins = coins;
    }
}
