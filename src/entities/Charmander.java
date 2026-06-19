package entities;
import java.util.Random;

public class Charmander extends Pokemon {
    public Charmander(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft,  int level, int exp) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp);
    }

    public int applySpecialAttack(Pokemon enemy) {
        int damage = this.getSpecialAttack() / 2;
        Random random = new Random();
        if (random.nextInt(3) == 0) { // 33% de chance de queimar
            enemy.setStatusEffect(StatusEffect.BURNED);
        }
        System.out.println(this.getName() + " usou Ember!");
        return damage;
    }

//    @Override
//    public boolean pokemonBattle(Pokemon opponent) {
//        //metodo de ataque do charmander, é o mais ofensivo, mas tem menos maxHp
//        return false;
//    }
}
