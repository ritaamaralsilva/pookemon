package entities;

public class Pikachu extends Pokemon{

    public Pikachu(String name, int maxHp, int hp, int strength, int level, int exp) {

        super(name, maxHp, hp, strength, level, exp);
    }

    @Override
    public boolean pokemonBattle(Pokemon opponent) {
        //metodo de ataque do pikachu, tem um boost de maxHp
        return false;
    }

}
