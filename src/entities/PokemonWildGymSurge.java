package entities;

import java.util.Random;

public class PokemonWildGymSurge extends PokemonWild{
    public PokemonWildGymSurge(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp, int coins) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp, coins);
    }
    public int applySpecialAttack(Pokemon enemy) {
        int damage = this.getSpecialAttack() / 2;
        if (new Random().nextInt(10) == 0) {
            enemy.setStatusEffect(StatusEffect.PARALYZED);
            System.out.println(this.getName() + " usou Thunderbolt! " + enemy.getName() + " ficou paralisado!");
        } else {
            System.out.println(this.getName() + " usou Thunderbolt!");
        }
        return damage;
    }
}
