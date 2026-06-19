package entities;

public class PokemonWildGymErika extends PokemonWild{
    public PokemonWildGymErika(String name, int maxHp, int hp, int strength, int specialAttack, int specialAttackUses, int specialAttackUsesLeft, int level, int exp, int coins) {
        super(name, maxHp, hp, strength, specialAttack, specialAttackUses, specialAttackUsesLeft, level, exp, coins);
    }
    public int applySpecialAttack(Pokemon enemy) {
        enemy.setStatusEffect(StatusEffect.ASLEEP);
        System.out.println(this.getName() + " usou Sleep Powder! " + enemy.getName() + " adormeceu!");
        return 0;
    }
}
