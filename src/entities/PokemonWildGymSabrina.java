package entities;

import java.util.Random;

public class PokemonWildGymSabrina extends PokemonWild{
    public PokemonWildGymSabrina(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp, int coins) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp, coins);
    }
    public int applySpecialAttack(Pokemon enemy) {
        int damage = this.getSpecialAttack() / 2 + 10;
        if (new Random().nextInt(3) == 0) {
            enemy.revertStrength(10);
            System.out.println(this.getName() + " usou Psychic! " + enemy.getName() + " perdeu 10 de Strength!");
        } else {
            System.out.println(this.getName() + " usou Psychic!");
        }
        return damage;
    }
}
