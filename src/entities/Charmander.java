package entities;

public class Charmander extends Pokemon {
    public Charmander(String name, int maxHp, int hp, int strength, int level) {
        super(name, maxHp, hp, strength, level);
    }

    @Override
    public boolean pokemonBattle(Pokemon opponent) {
        //metodo de ataque do charmander, é o mais ofensivo, mas tem menos maxHp
        return false;
    }
}
