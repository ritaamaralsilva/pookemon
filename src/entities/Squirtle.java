package entities;

public class Squirtle extends Pokemon {
    public Squirtle(String name, int maxHp, int hp, int strength, int level, int exp) {
        super(name, maxHp, hp, strength, level, exp);
    }

    @Override
    public boolean pokemonBattle(Pokemon opponent) {
        //metodo de ataque do squirtle, esta entre o bulbasaur e o charmander
        return false;
    }
}
