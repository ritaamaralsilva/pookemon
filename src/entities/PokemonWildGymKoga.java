package entities;

public class PokemonWildGymKoga extends PokemonWild{
    public PokemonWildGymKoga(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp, int coins) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp, coins);
    }
    public int applySpecialAttack(Pokemon enemy) {
        enemy.setStatusEffect(StatusEffect.POISONED);
        System.out.println(this.getName() + " usou Toxic! " + enemy.getName() + " ficou gravemente envenenado!");
        return 0;
    }
}
