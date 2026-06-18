package entities;

public class Bulbasaur extends Pokemon {
    public Bulbasaur(String name, int maxHp, int hp, int strength, int level, int exp) {
        super(name, maxHp, hp, strength, level, exp);
    }

    @Override
    public boolean pokemonBattle(Pokemon opponent) {
        // metodo de ataque do bulbasaur, tem mais defesa, age como um tanque
        return false;
    }
}
