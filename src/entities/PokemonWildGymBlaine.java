package entities;

import java.util.Random;

public class PokemonWildGymBlaine extends PokemonWild{
    public PokemonWildGymBlaine(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp, int coins) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp, coins);
    }
    public int applySpecialAttack(Pokemon enemy) {
        int damage = this.getSpecialAttack() / 2 + 15;
        if (new Random().nextInt(3) == 0) {
            enemy.setStatusEffect(StatusEffect.BURNED);
            System.out.println(this.getName() + " usou Fire Blast! " + enemy.getName() + " ficou em chamas!");
        } else {
            System.out.println(this.getName() + " usou Fire Blast!");
        }
        return damage;
    }
}
