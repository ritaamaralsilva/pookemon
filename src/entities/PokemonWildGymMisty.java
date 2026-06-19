package entities;

import java.util.Random;

public class PokemonWildGymMisty extends PokemonWild{
    public PokemonWildGymMisty(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp, int coins) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp, coins);
    }
    public int applySpecialAttack(Pokemon enemy) {
        int damage = this.getSpecialAttack() / 2;
        if (new Random().nextInt(3) == 0) {
            enemy.setStatusEffect(StatusEffect.PARALYZED);
            System.out.println(this.getName() + " usou Bubblebeam! " + enemy.getName() + " ficou lento!");
        } else {
            System.out.println(this.getName() + " usou Bubblebeam!");
        }
        return damage;
    }
}
